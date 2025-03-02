package controlador;

import java.util.ArrayList;
import javax.swing.JFrame;
import model.Model;
import model.Notificacio;
import model.Notificar;
import vista.Vista;

public class Controlador implements Notificar {

    private final Model model;
    private final Vista vista;
    private ArrayList<Notificar> procesos;

    public Controlador() {
        model = new Model();
        vista = new Vista(this);
    }

    public void init() {
        procesos = new ArrayList<>();
        model.prepararDades();

        JFrame frame = new JFrame("Practica 1 - Suma i Producte de Matrius");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public synchronized void notificar(Notificacio n) {
        switch (n) {
            case Notificacio.ARRANCAR -> {
                int vius = 0;
                for (int i = 0; i < procesos.size(); i++) {
                    if (((Thread) procesos.get(i)).isAlive()) {
                        vius++;
                    }
                }
                if (vius == 0) {
                    procesos = new ArrayList<>();
                    model.prepararDades();
                    procesos.add(new SumaMatrius(this));
                    procesos.add(new ProducteMatrius(this));
                    for (int i = 0; i < procesos.size(); i++) {
                        ((Thread) procesos.get(i)).start();
                    }
                }
            }
            case Notificacio.ATURAR -> {
                for (int i = 0; i < procesos.size(); i++) {
                    procesos.get(i).notificar(Notificacio.ATURAR);
                }
            }
            case Notificacio.PINTAR -> {
                vista.actualizarVista(
                        model.getTamanyMatrius(),
                        model.getTempsSumar(),
                        model.getTempsProducte(),
                        model.getConstantSuma(),
                        model.getConstantProducte()
                );
            }
            default -> {
            }
        }
    }

    public Model getModel() {
        return model;
    }
}
