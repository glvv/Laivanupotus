package laivanupotus.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Pelilauta pitää kirjaa laivoista ja niiden sijainneista.
 */
public class Pelilauta {

    private final ArrayList<Laiva> laivat;
    private final HashMap<Ruutu, Laiva> laivaRuudukko;

    /**
     * Konstruktorissa parametrina saadut laivat talletetaan oliomuuttujaan,
     * sekä HashMap-olion arvoiksi, joiden avaimiksi tulee Laiva-olion ruudut.
     *
     * @param laivat
     */
    public Pelilauta(ArrayList<Laiva> laivat) {
        this.laivat = laivat;
        this.laivaRuudukko = new HashMap<>();
        liitaLaivat();
    }

    private void liitaLaivat() {
        for (Laiva laiva : laivat) {
            for (Ruutu ruutu : laiva.haeRuudut()) {
                laivaRuudukko.put(ruutu, laiva);
            }
        }
    }

    /**
     * Metodi palauttaa ruutuun liittyvän laivan. Jos ruutuun ei liity laivaa,
     * palautetaan null.
     *
     * @param siirto Ruutu, johon liityvä laiva haetaan.
     * @return Ruutuun liittyvä laiva.
     */
    public Laiva katsoRuutu(Ruutu siirto) {
        return laivaRuudukko.get(siirto);
    }

    public ArrayList haeLaivat() {
        return this.laivat;
    }

    private ArrayList<Laiva> haeUponneetLaivat() {
        ArrayList<Laiva> uponneet = new ArrayList<>();
        for (Laiva laiva : laivat) {
            if (laiva.uppoaako()) {
                uponneet.add(laiva);
            }
        }
        return uponneet;
    }

    /**
     * Metodi palauttaa uponneiden laivojen ruudut.
     *
     * @return Uponneiden laivojen ruudut
     */
    public ArrayList<Ruutu> haeUponneidenLaivojenRuudut() {
        ArrayList<Ruutu> upotetutLaivat = new ArrayList<>();
        for (Laiva laiva : haeUponneetLaivat()) {
            upotetutLaivat.addAll(Arrays.asList(laiva.haeRuudut()));
        }
        return upotetutLaivat;
    }

    /**
     * Metodi palauttaa pelilaudan kaikkien laivojen ruudut.
     *
     * @return Kaikkien laivojen ruudut.
     */
    public ArrayList<Ruutu> haeLaivojenRuudut() {
        ArrayList<Ruutu> laivojenRuudut = new ArrayList<>();
        for (Laiva laiva : laivat) {
            laivojenRuudut.addAll(Arrays.asList(laiva.haeRuudut()));
        }
        return laivojenRuudut;
    }

    /**
     * Metodi kertoo onko ruudussa oleva laiva uponnut.
     *
     * @param siirto Tarkistettava ruutu.
     * @return Onko ruutuun kuuluva laiva uponnut vai ei.
     */
    public boolean uppoaakoRuutuunLiittyvaLaiva(Ruutu siirto) {
        if (laivaRuudukko.get(siirto) != null) {
            return laivaRuudukko.get(siirto).uppoaako();
        }
        return false;
    }

    /**
     * Metodi kertoo ovatko kaikki pelilaudalla olevat laivat uponneita.
     *
     * @return Ovatko kaikki laivat uponneita.
     */
    public boolean kaikkiLaivatUpotettu() {
        return (haeUponneetLaivat().size() == laivat.size());
    }

}
