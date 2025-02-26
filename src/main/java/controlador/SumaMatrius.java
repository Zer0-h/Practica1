package controlador;

import java.util.Random;
import main.Practica1;
import model.Model;
import model.Notificacio;
import model.Notificar;

public class SumaMatrius extends Thread implements Notificar {

    private boolean cancel;
    private Practica1 princ;
    private int[][] matriu1;
    private int[][] matriu2;
    private int[][] matriuSumar;

    public SumaMatrius(Practica1 p) {
        princ = p;
    }

    @Override
    public void run() {
        cancel = false;
        Model model = princ.getModel();

        for (int z = 0; z < model.getTamTamanyMatrius() && !cancel; z++) {
            long temps = System.nanoTime();

            initMatrius(model.getTamanyMatriu(z));
            calcula();

            // Calcul de temps
            temps = System.nanoTime() - temps;
            model.posarTempsSumar(temps);
            princ.notificar(Notificacio.PINTAR);
        }

    }

    private void calcula() {
        for (int i = 0; i < matriu1.length && !cancel; i++) {
            for (int j = 0; j < matriu1[i].length && !cancel; j++) {
                matriuSumar[i][j] = matriu1[i][j] + matriu2[i][j];
            }
        }
    }

    // Ficam per fer simplificar i no haver-hi de compartir diferents tamanys de matrius
    private void initMatrius(int n) {
        matriu1 = new int[n][n];
        matriu2 = new int[n][n];
        matriuSumar = new int[n][n];

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
