package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class Grafica extends JPanel {

    private final Color colorLinea = Color.BLACK;
    private final Color colorSumar = Color.GREEN;
    private final Color colorProducte = Color.RED;
    private final int tamanyPunt = 7;
    private final int divisionAxis = 10;
    private final Font font = new Font("Arial", Font.PLAIN, 12);

    private List<Integer> tamanysMatrius;
    private List<Long> tempsSumar;
    private List<Long> tempsProducte;

    public Grafica(int w, int h) {
        this.setBounds(0, 0, w, h);
    }

    public void pintar(List<Integer> tamanysMatrius, List<Long> tempsSumar, List<Long> tempsProducte) {
        this.tamanysMatrius = tamanysMatrius;
        this.tempsSumar = tempsSumar;
        this.tempsProducte = tempsProducte;

        if (this.getGraphics() != null) {
            paintComponent(this.getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tamanysMatrius == null || tempsSumar == null || tempsProducte == null) {
            return; // No data to paint
        }

        int width = this.getWidth() - 1;
        int height = this.getHeight() - 24;

        // Neteja el fons
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Pinta els Axis
        g.setColor(colorLinea);
        g.drawLine(50, height - 50, 50, 30); // Y-axis
        g.drawLine(50, height - 50, width - 30, height - 50); // X-axis

        int maxElement = tamanysMatrius.isEmpty() ? 0 : tamanysMatrius.get(tamanysMatrius.size() - 1);
        long maxTime = getMaxTime();

        if (maxElement == 0 || maxTime == 0) {
            return;
        }

        // Labels
        drawYAxisLabels(g, height, maxTime);
        drawXAxisLabels(g, width, height, maxElement);

        // Plot data points
        drawSumar(g, width, height, maxElement, maxTime);
        drawProducte(g, width, height, maxElement, maxTime);
    }

    private void drawYAxisLabels(Graphics g, int height, long maxTime) {
        g.setFont(font);
        for (int i = 0; i <= divisionAxis; i++) {
            int y = height - 50 - (i * (height - 80) / divisionAxis);
            double timeValue = (maxTime * i) / (double) divisionAxis / 1e9;
            g.drawString(String.format("%.2f s", timeValue), 10, y + 5);
            g.drawLine(45, y, 50, y);
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

    private void drawSumar(Graphics g, int width, int height, int maxElement, long maxTime) {
        if (tempsSumar.isEmpty()) {
            return;
        }

        int prevX = 50;
        int prevY = height - 50;

        for (int i = 0; i < tempsSumar.size(); i++) {
            int x = 50 + (tamanysMatrius.get(i) * (width - 80) / maxElement);
            int y = height - 50 - ((int) (tempsSumar.get(i) * (height - 80) / maxTime));

            g.setColor(colorSumar);
            g.fillOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);
            g.drawLine(prevX, prevY, x, y);
            g.setColor(colorLinea);
            g.drawOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);

            prevX = x;
            prevY = y;
        }
    }

    private void drawProducte(Graphics g, int width, int height, int maxElement, long maxTime) {
        if (tempsProducte.isEmpty()) {
            return;
        }

        int prevX = 50;
        int prevY = height - 50;

        for (int i = 0; i < tempsProducte.size(); i++) {
            int x = 50 + (tamanysMatrius.get(i) * (width - 80) / maxElement);
            int y = height - 50 - ((int) (tempsProducte.get(i) * (height - 80) / maxTime));

            g.setColor(colorProducte);
            g.fillOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);
            g.drawLine(prevX, prevY, x, y);
            g.setColor(colorLinea);
            g.drawOval(x - tamanyPunt / 2, y - tamanyPunt / 2, tamanyPunt, tamanyPunt);

            prevX = x;
            prevY = y;
        }
    }

    private long getMaxTime() {
        if (tempsSumar.isEmpty() && tempsProducte.isEmpty()) {
            return 0;
        }

        long maxTempsSumar = tempsSumar.isEmpty() ? 0 : tempsSumar.get(tempsSumar.size() - 1);
        long maxTempsProducte = tempsProducte.isEmpty() ? 0 : tempsProducte.get(tempsProducte.size() - 1);

        return Math.max(maxTempsSumar, maxTempsProducte);
    }
}
