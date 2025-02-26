package vista;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import main.Practica1;
import model.Model;

public class Grafica extends JPanel {

    private Practica1 prin;
    private Color lineColor = Color.BLACK;
    private Color sumColor = Color.GREEN;
    private Color productColor = Color.RED;
    private int pointSize = 7;

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
        if (model == null) {
            return; // No model to draw
        }

        int width = this.getWidth() - 1;
        int height = this.getHeight() - 24;

        // Clear the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Draw axes
        g.setColor(lineColor);
        g.drawLine(10, 10, 10, height - 10);
        g.drawLine(10, height - 10, width - 10, height - 10);

        // Calculate maximum values
        int maxElement = calculateMaxElement(model);
        long maxTime = calculateMaxTime(model);

        if (maxElement == 0 || maxTime == 0) {
            return; // No data to draw
        }

        // Draw sum data
        drawSumData(g, model, width, height, maxElement, maxTime);

        // Draw product data
        drawProductData(g, model, width, height, maxElement, maxTime);
    }

    private int calculateMaxElement(Model model) {
        int maxElement = 0;
        for (int i = 0; i < model.getTamTamanyMatrius(); i++) {
            if (model.getTamanyMatriu(i) > maxElement) {
                maxElement = model.getTamanyMatriu(i);
            }
        }
        return maxElement;
    }

    private long calculateMaxTime(Model model) {
        long maxTime = 0;
        for (int i = 0; i < model.getTamTempsSumar(); i++) {
            if (model.getTempsSumar(i) > maxTime) {
                maxTime = model.getTempsSumar(i);
            }
        }
        for (int i = 0; i < model.getTamTempsProducte(); i++) {
            if (model.getTempsProducte(i) > maxTime) {
                maxTime = model.getTempsProducte(i);
            }
        }
        return maxTime;
    }

    private void drawSumData(Graphics g, Model model, int width, int height, int maxElement, long maxTime) {
        int prevX = 10;
        int prevY = height - 10;

        for (int i = 0; i < model.getTamTempsSumar(); i++) {
            int x = model.getTamanyMatriu(i) * (width - 20) / maxElement;
            int y = (height - 20) - ((int) (model.getTempsSumar(i) * (height - 40) / maxTime));

            g.setColor(sumColor);
            g.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
            g.drawLine(prevX, prevY, x, y);
            g.setColor(lineColor);
            g.drawOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);

            prevX = x;
            prevY = y;
        }
    }

    private void drawProductData(Graphics g, Model model, int width, int height, int maxElement, long maxTime) {
        int prevX = 10;
        int prevY = height - 10;

        for (int i = 0; i < model.getTamTempsProducte(); i++) {
            int x = model.getTamanyMatriu(i) * (width - 20) / maxElement;
            int y = (height - 20) - ((int) (model.getTempsProducte(i) * (height - 40) / maxTime));

            g.setColor(productColor);
            g.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);
            g.drawLine(prevX, prevY, x, y);
            g.setColor(lineColor);
            g.drawOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);

            prevX = x;
            prevY = y;
        }
    }

    // Setters for customization
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setSumColor(Color sumColor) {
        this.sumColor = sumColor;
    }

    public void setProductColor(Color productColor) {
        this.productColor = productColor;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }
}
