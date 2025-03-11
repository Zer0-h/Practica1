package controlador;

import main.Practica1;
import model.Model;
import model.Notificacio;
import model.Notificar;

/**
 *
 * @author tonitorres
 */
public class SumaMatrius extends Thread implements Notificar {

    private boolean cancel;
    private final Practica1 princ;
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

        for (int i = 0; i < model.getTamTamanyMatrius() && !cancel; i++) {
            long temps = System.nanoTime();
            int tamanyMatriu = model.getTamanyMatriu(i);

            int[][][] matrius = model.generarMatrius(tamanyMatriu);
            matriuSumar = new int[tamanyMatriu][tamanyMatriu];
            matriu1 = matrius[0];
            matriu2 = matrius[1];

            calcula();

            temps = System.nanoTime() - temps;
            model.posarTempsSumar(temps);

            double constant = temps / (tamanyMatriu ^ 2);
            model.setConstantSuma(constant);

            System.out.println(
                    String.format(
                            "Finalitzat el càlcul per a la suma de matrius de %dx%d en %.2f segons i amb una constant multiplicativa de %.5f",
                            tamanyMatriu, tamanyMatriu, temps / 1e9, constant
                    )
            );

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
