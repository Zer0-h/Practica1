package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import main.Practica1;
import model.Model;

public class Grafica extends JPanel {

    private final Practica1 prin;
    private final Color colorLinea = Color.BLACK;
    private final Color colorSumar = Color.GREEN;
    private final Color colorProducte = Color.RED;
    private final int tamanyPunt = 7;
    private final int divisionAxis = 10;
    private final Font font = new Font("Arial", Font.PLAIN, 12);

    public Grafica(int w, int h, Practica1 p) {
        prin = p;
        this.setBounds(0, 0, w, h);
    }

    public void pintar() {
        if (this.getGraphics() != null) {
            paintComponent(this.getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Model model = prin.getModel();

        int width = this.getWidth() - 1;
        int height = this.getHeight() - 24;

        // Neteja el fons
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Pinta els Axis
        g.setColor(colorLinea);
        g.drawLine(50, height - 50, 50, 30); // Y-axis
        g.drawLine(50, height - 50, width - 30, height - 50); // X-axis

        int maxElement = model.getMaxTamanyMatriu();
        long maxTime = model.getMaxTemps();

        if (maxElement == 0 || maxTime == 0) {
            return; // No pintam
        }

        // Label y en segons
        drawYAxisLabels(g, height, maxTime);

        // Label x tamany matriu
        drawXAxisLabels(g, width, height, maxElement);

        // Pintam els temps de la sumar
        drawSumar(g, model, width, height, maxElement, maxTime);

        // Pintam els temps del producte
        drawProducte(g, model, width, height, maxElement, maxTime);
    }

    private void drawYAxisLabels(Graphics g, int height, long maxTime) {
        g.setFont(font);
        for (int i = 0; i <= divisionAxis; i++) {
            int y = height - 50 - (i * (height - 80) / divisionAxis);
            double timeValue = (maxTime * i) / (double) divisionAxis / 1e9;
            g.drawString(String.format("%.2f s", timeValue), 10, y + 5);
            g.drawLine(45, y, 50, y); // Tick mark
        }
    }

    private void drawXAxisLabels(Graphics g, int width, int height, int maxElement) {
        g.setFont(font);
        for (int i = 0; i <= divisionAxis; i++) {
            int x = 50 + (i * (width - 80) / divisionAxis);
            int nValue = (maxElement * i) / divisionAxis;
            g.drawString(nValue + "", x - 10, height - 35);
            g.drawLine(x, height - 50, x, height - 45);
        }
    }

    private void drawSumar(Graphics g, Model model, int width, int height, int maxElement, long maxTime) {
        int prevX = 50;
        int prevY = height - 50;

        for (int i = 0; i < model.getTamTempsSumar(); i++) {
            int x = 50 + (model.getTamanyMatriu(i) * (width - 80) / maxElement);
            int y = height - 50 - ((int) (model.getTempsSumar(i) * (height - 80) / maxTime));

            g.setColor(colorSumar);
            g.fillOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);
            g.drawLine(prevX, prevY, x, y);
            g.setColor(colorLinea);
            g.drawOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);

            prevX = x;
            prevY = y;
        }
    }

    private void drawProducte(Graphics g, Model model, int width, int height, int maxElement, long maxTime) {
        int prevX = 50;
        int prevY = height - 50;

        for (int i = 0; i < model.getTamTempsProducte(); i++) {
            int x = 50 + (model.getTamanyMatriu(i) * (width - 80) / maxElement);
            int y = height - 50 - ((int) (model.getTempsProducte(i) * (height - 80) / maxTime));

            g.setColor(colorProducte);
            g.fillOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);
            g.drawLine(prevX, prevY, x, y);
            g.setColor(colorLinea);
            g.drawOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);

            prevX = x;
            prevY = y;
        }
    }
}
