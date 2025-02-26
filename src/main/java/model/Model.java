package model;

import java.util.ArrayList;

public class Model {

    private final ArrayList<Long> tempsSumar;
    private final ArrayList<Long> tempsProducte;
    private final ArrayList<Integer> tamanysMatrius;

    public Model() {
        tempsSumar = new ArrayList<>();
        tempsProducte = new ArrayList<>();
        tamanysMatrius = new ArrayList<>();
    }

    public void clear() {
        tempsSumar.clear();
        tempsProducte.clear();
        tamanysMatrius.clear();
    }

    public ArrayList<Long> getTempsSumar() {
        return tempsSumar;
    }

    public ArrayList<Long> getTempsProducte() {
        return tempsSumar;
    }

    public ArrayList<Integer> getTamanyMatrius() {
        return tamanysMatrius;
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
}
