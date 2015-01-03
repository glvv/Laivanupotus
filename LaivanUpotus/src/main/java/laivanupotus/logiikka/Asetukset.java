package laivanupotus.logiikka;

import java.util.HashMap;

/**
 * Luokka pitää sisällään pelin asetukset.
 */
public class Asetukset {

    private boolean laivatSaaKoskea;
    private int pelilautaLeveys;
    private int pelilautaPituus;
    private final HashMap<Integer, Integer> laivat;
    private boolean kaksinpeli;

    /**
     * Konstruktorissa leveydeksi ja pituudeksi asetetaan alussa 10 Oliolle
     * luodaan tyhjä HashMap laivojen tallettamista varten.
     */
    public Asetukset() {
        laivatSaaKoskea = false;
        pelilautaLeveys = 10;
        pelilautaPituus = 10;
        laivat = new HashMap<>();
        kaksinpeli = false;
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

    /**
     * Metodilla lisätään laiva Asetukset-olion HashMap-olioon
     *
     * @param koko Laivan koko, eli ruutujen määrä
     * @param maara Laivojen määrä
     */
    public void lisaaLaiva(int koko, int maara) {
        laivat.put(koko, maara);
    }

    /**
     * Metodi asettaa Laivanupotuksen oletuslaivat eli 1 sukellusvene, 1
     * hävittäjä, 2 risteilijää, 1 taistelulaiva sekä 1 lentotukialus.
     */
    public void asetaOletusLaivat() {
        lisaaLaiva(1, 1);
        lisaaLaiva(2, 1);
        lisaaLaiva(3, 2);
        lisaaLaiva(4, 1);
        lisaaLaiva(5, 1);
    }

    public HashMap<Integer, Integer> haeLaivat() {
        return laivat;
    }

    /**
     * Metodi palauttaa true jos parametrinä annettu laivankoko on jo lisätty
     *
     * @param laivanKoko Tarkistettava laivan koko
     * @return totuusarvo Onko laiva jo lisätty
     */
    public boolean onkoLaivaLisatty(int laivanKoko) {
        return laivat.containsKey(laivanKoko);
    }

    public int haePelilautaLeveys() {
        return pelilautaLeveys;
    }

    public int haePelilautaPituus() {
        return pelilautaPituus;
    }

    public boolean laivatSaaKoskea() {
        return laivatSaaKoskea;
    }

    public int haeLaivojenMaaranKerroin() {
        return pelilautaLeveys * pelilautaPituus / 100;
    }
    
    public int haeLaivojenMaara() {
        int laivoja = 0;
        for (Integer luku : laivat.values()) {
            laivoja += luku;
        }
        return laivoja;
    }
    
    public void asetaKaksinpeli(boolean onKaksinpeli) {
        kaksinpeli = onKaksinpeli;
    }
    
    public boolean onkoKaksinpeli() {
        return kaksinpeli;
    }

}
