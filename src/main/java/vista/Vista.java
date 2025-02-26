package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.Practica1;
import model.Notificacio;
import model.Notificar;

public class Vista extends JPanel implements Notificar {

    private Practica1 principal;
    private Grafica grafica;

    public Vista(Practica1 p) {
        principal = p;
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        JMenu procMenu = new JMenu("Processos");

        JMenuItem arrancarItem = new JMenuItem("Arrancar");
        JMenuItem aturarItem = new JMenuItem("Aturar");

        procMenu.add(arrancarItem);
        procMenu.addSeparator();
        procMenu.add(aturarItem);

        menuBar.add(procMenu);

        // Crear el panel principal con coordenadas absolutas
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Permite la colocación de elementos en posiciones absolutas
        mainPanel.setBounds(0, 0, 800, 600);
        grafica = new Grafica(800, 600, principal);
        mainPanel.add(grafica);

        // Agregar el menú en la parte superior
        add(menuBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Agregar listeners a los elementos del menú
        arrancarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principal.notificar(Notificacio.ARRANCAR);
            }
        });

        aturarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principal.notificar(Notificacio.ATURAR);
            }
        });
    }

    @Override
    public void notificar(Notificacio n) {
        if (n == Notificacio.PINTAR) {
            grafica.pintar();
        }
    }
}
