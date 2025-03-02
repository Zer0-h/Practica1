package model;

import java.util.ArrayList;
import java.util.Random;

public class Model {

    private final ArrayList<Long> tempsSumar;
    private final ArrayList<Long> tempsProducte;
    private final ArrayList<Integer> tamanysMatrius;
    private double constantSuma;
    private double constantProducte;
    private final Random rand;

    public Model() {
        tempsSumar = new ArrayList<>();
        tempsProducte = new ArrayList<>();
        tamanysMatrius = new ArrayList<>();
        rand = new Random();
    }

    public void prepararDades() {
        tempsSumar.clear();
        tempsProducte.clear();
        tamanysMatrius.clear();
        for (int i = 100; i < 2000; i += 100) {
            tamanysMatrius.add(i);
        }
    }

    public int[][][] generarMatrius(int n) {
        int[][] matriu1 = new int[n][n];
        int[][] matriu2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriu1[i][j] = rand.nextInt(10) * 10;
                matriu2[i][j] = rand.nextInt(10) * 10;
            }
        }
        return new int[][][]{matriu1, matriu2};
    }

    public int getTamTempsSumar() {
        return tempsSumar.size();
    }

    public int getTamTempsProducte() {
        return tempsProducte.size();
    }

    public int getTamTamanyMatrius() {
        return tamanysMatrius.size();
    }

    public int getTamanyMatriu(int i) {
        return tamanysMatrius.get(i);
    }

    public long getTempsSumar(int i) {
        return (tempsSumar.get(i));
    }

    public long getTempsProducte(int i) {
        return (tempsProducte.get(i));
    }

    public void posarTempsSumar(long t) {
        tempsSumar.add(t);
    }

    public void posarTempsProducte(long t) {
        tempsProducte.add(t);
    }

    public void posarTamanyMatrius(int n) {
        tamanysMatrius.add(n);
    }

    public int getMaxTamanyMatriu() {
        return tamanysMatrius.get(tamanysMatrius.size() - 1);
    }

    public long getMaxTemps() {
        if (tempsSumar.isEmpty() || tempsProducte.isEmpty()) {
            return 0;
        }

        long maxTempsSumar = tempsSumar.get(tempsSumar.size() - 1);
        long maxTempsProducte = tempsProducte.get(tempsProducte.size() - 1);
        return Math.max(maxTempsSumar, maxTempsProducte);
    }

    public void setConstantSuma(double valor) {
        constantSuma = valor;
    }

    public double getConstantSuma() {
        return constantSuma;
    }

    public void setConstantProducte(double valor) {
        constantProducte = valor;
    }

    public double getConstantProducte() {
        return constantProducte;
    }
}
