package main;

import controlador.ProducteMatrius;
import controlador.SumaMatrius;
import java.util.ArrayList;
import javax.swing.*;
import model.Model;
import model.Notificacio;
import model.Notificar;
import vista.Vista;

/**
 *
 * @author tonitorres
 */
public class Practica1 implements Notificar {

    private Vista vista;
    private ArrayList<Notificar> procesos = null;
    private Model model = null;

    public static void main(String[] args) {
        Practica1 practica1 = new Practica1();
        practica1.inicio();
    }

    private void inicio() {
        model = new Model();
        procesos = new ArrayList<>();
        preparar();

        JFrame frame = new JFrame("Practica 1 - Suma i Producte de Matrius");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista = new Vista(this);
        frame.setContentPane(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void preparar() {
        procesos = new ArrayList<>();
        model.prepararDades();
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
                    preparar();
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
                vista.notificar(Notificacio.PINTAR);
            }
            default -> {
            }
        }
    }

    public Model getModel() {
        return model;
    }
}
