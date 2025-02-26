package vista;

import java.awt.*;
import javax.swing.*;
import main.Practica1;
import model.*;

public class Vista extends JPanel implements Notificar {

    private Practica1 principal;
    private Grafica grafica;
    private JLabel labelConstants;

    public Vista(Practica1 p) {
        principal = p;
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // Crear el panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        grafica = new Grafica(800, 500, principal);
        mainPanel.add(grafica, BorderLayout.CENTER);

        // Afegir el text de les constants
        labelConstants = new JLabel();
        labelConstants.setHorizontalAlignment(JLabel.CENTER);
        labelConstants.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(labelConstants, BorderLayout.SOUTH);

        // Crear el panell per als botons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrar els botons

        // Crear els botons
        JButton arrancarButton = new JButton("Arrancar");
        JButton aturarButton = new JButton("Aturar");

        // Afegir listeners als botons
        arrancarButton.addActionListener(e -> principal.notificar(Notificacio.ARRANCAR));
        aturarButton.addActionListener(e -> principal.notificar(Notificacio.ATURAR));

        // Afegir els botons al panell
        buttonPanel.add(arrancarButton);
        buttonPanel.add(aturarButton);

        // Afegir el panell de botons a la part inferior de la finestra
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void notificar(Notificacio n) {
        if (n == Notificacio.PINTAR) {
            grafica.pintar();
            actualitzarTextConstants();
        }
    }

    private void actualitzarTextConstants() {
        Model model = principal.getModel();
        String text = String.format("Constant Suma: %.2e ns/n² | Constant Producte: %.2e ns/n³",
                model.getConstantSuma(), model.getConstantProducte());
        labelConstants.setText(text);
    }
}
