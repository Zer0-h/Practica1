package controlador;

import java.util.Random;
import main.Practica1;
import model.Model;
import model.Notificacio;
import model.Notificar;

public class ProducteMatrius extends Thread implements Notificar {

    private int[][] matriu1;
    private int[][] matriu2;
    private int[][] matriuProducte;

    private boolean cancel;
    private Practica1 princ;

    public ProducteMatrius(Practica1 p) {
        princ = p;
    }

    @Override
    public void run() {
        cancel = false;
        Model model = princ.getModel();

        for (int i = 0; i < model.getTamTamanyMatrius() && !cancel; i++) {
            long temps = System.nanoTime();
            int tamanyMatriu = model.getTamanyMatriu(i);

            initMatrius(tamanyMatriu);

            calcula();

            temps = System.nanoTime() - temps;
            model.posarTempsProducte(temps);

            double constant = temps / (tamanyMatriu ^ 3);
            model.setConstantProducte(constant);

            System.out.println(
                    String.format(
                            "Finalitzat el càlcul per al producte de matrius de %dx%d en %.2f segons i amb una constant multiplicativa de %.5f",
                            tamanyMatriu, tamanyMatriu, temps / 1e9, constant
                    )
            );

            princ.notificar(Notificacio.PINTAR);
        }
    }

    private void calcula() {
        for (int i = 0; i < matriu1.length && !cancel; i++) {
            for (int j = 0; j < matriu2[0].length && !cancel; j++) {
                for (int k = 0; k < matriu1[0].length && !cancel; k++) {
                    matriuProducte[i][j] += matriu1[i][k] * matriu2[k][j];
                }
            }
        }
    }

    // Ficam per fer simplificar i no haver-hi de compartir diferents tamanys de matrius
    private void initMatrius(int n) {
        matriu1 = new int[n][n];
        matriu2 = new int[n][n];
        matriuProducte = new int[n][n];

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriu1[i][j] = rand.nextInt(10) * 10;
                matriu2[i][j] = rand.nextInt(10) * 10;
            }
        }
    }

    private void aturar() {
        cancel = true;
    }

    @Override
    public void notificar(Notificacio notificacio) {
        if (notificacio == Notificacio.ATURAR) {
            aturar();
        }
    }
}
