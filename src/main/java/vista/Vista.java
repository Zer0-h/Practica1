package vista;

import controlador.Controlador;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.*;

public class Vista extends JPanel {

    private final Grafica grafica;
    private final JLabel labelConstants;

    public Vista(Controlador controlador) {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // Crear el panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        grafica = new Grafica(800, 500);
        mainPanel.add(grafica, BorderLayout.CENTER);

        // Afegir el text de les constants
        labelConstants = new JLabel("", JLabel.CENTER);
        labelConstants.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(labelConstants, BorderLayout.SOUTH);

        // Crear el panell per als botons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton arrancarButton = new JButton("Arrancar");
        JButton aturarButton = new JButton("Aturar");

        // Afegir listeners als botons
        arrancarButton.addActionListener(e -> controlador.notificar(Notificacio.ARRANCAR));
        aturarButton.addActionListener(e -> controlador.notificar(Notificacio.ATURAR));

        // Els listeners dels botons ara seran gestionats des del Controlador
        buttonPanel.add(arrancarButton);
        buttonPanel.add(aturarButton);

        // Afegir el panell de botons a la part inferior de la finestra
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actualizarVista(List<Integer> tamanysMatrius, List<Long> tempsSumar, List<Long> tempsProducte, double constantSuma, double constantProducte) {
        // Actualitzar gràfica
        grafica.pintar(tamanysMatrius, tempsSumar, tempsProducte);

        // Actualitzar el text de les constants
        String text = String.format("Constant Suma: %.2e ns/n² | Constant Producte: %.2e ns/n³", constantSuma, constantProducte);
        labelConstants.setText(text);
    }

    public Grafica getGrafica() {
        return grafica;
    }
}
