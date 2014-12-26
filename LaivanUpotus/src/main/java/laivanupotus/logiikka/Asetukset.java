package laivanupotus.logiikka;

import java.util.HashMap;

public class Asetukset {

    private boolean laivatSaaKoskea;
    private int pelilautaLeveys;
    private int pelilautaPituus;
    private final HashMap<Integer, Integer> laivat;
//    private List<Laiva> kayttajanLaivat;

    public Asetukset() {
        laivatSaaKoskea = false;
        pelilautaLeveys = 10;
        pelilautaPituus = 10;
        laivat = new HashMap<>();
    }

    public void asetaLeveys(int leveys) {
        this.pelilautaLeveys = leveys;
    }

    public void asetaPituus(int pituus) {
        this.pelilautaPituus = pituus;
    }

    public void laivatSaaKoskea(boolean laivatSaaKoskea) {
        this.laivatSaaKoskea = laivatSaaKoskea;
    }

    public void lisaaLaiva(int koko, int maara) {
        laivat.put(koko, maara);
    }

    public void asetaOletusLaivat() {
        lisaaLaiva(1, 1);
        lisaaLaiva(2, 1);
        lisaaLaiva(3, 2);
        lisaaLaiva(4, 1);
        lisaaLaiva(5, 1);
    }

//    public void lisaaKayttajanLaiva(int alkuX, int alkuY, int loppuX, int loppuY) {
//        
//    }
    public HashMap<Integer, Integer> haeLaivat() {
        return laivat;
    }

    public int haePelilautaLeveys() {
        return pelilautaLeveys;
    }

    public int haePelilautaPituus() {
        return pelilautaPituus;
    }

    public boolean LaivatSaaKoskea() {
        return laivatSaaKoskea;
    }

}
