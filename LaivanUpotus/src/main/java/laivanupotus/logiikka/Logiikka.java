package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;

/**
 * Luokka tarjoaa metodeja siirtojen vastaanottamiseen ja pelitilanteen
 * käsittelyyn.
 */
public class Logiikka {

    private int vuoro;
    private final int pistemaaraVoittoon;
    private HashMap<Integer, Pelilauta> pelilaudat;
    private HashMap<Integer, Integer> pisteet;

    /**
     * Konstruktori luo ja alustaa pelin komponentit
     *
     * @param asetukset Pelin asetukset sisältävä olio
     */
    public Logiikka(Asetukset asetukset) {
        this.pelilaudat = new HashMap<>();
        this.pisteet = new HashMap<>();
        arvoLaivatJaLuoPelaajat(asetukset);
        pisteet.put(1, 0);
        pisteet.put(2, 0);
        this.vuoro = 1;
        this.pistemaaraVoittoon = asetukset.haeLaivojenMaara();
    }

    private void arvoLaivatJaLuoPelaajat(Asetukset asetukset) {
        LaivojenAsettaja asettaja = new LaivojenAsettaja(asetukset);
        ArrayList<Laiva> pelaaja1Laivat = asettaja.luoLaivatAutomaattisesti();
        ArrayList<Laiva> pelaaja2Laivat = asettaja.luoLaivatAutomaattisesti();
        pelilaudat.put(1, new Pelilauta(pelaaja1Laivat));
        pelilaudat.put(2, new Pelilauta(pelaaja2Laivat));
    }

    public boolean katsoSiirtoPelilaudasta(Ruutu siirto, int pelilauta) {
        pelilaudat.get(pelilauta).lisaaSiirto(siirto);
        Laiva laiva = pelilaudat.get(pelilauta).katsoRuutu(siirto);
        if (kasitteleLaiva(laiva, siirto)) {
            if (laiva.uppoaako()) {
                lisaaPiste(pelilauta);
            }
            return true;
        }
        return false;
    }

    private void lisaaPiste(int pelilauta) {
        if (pelilauta == 1) {
            pisteet.put(2, pisteet.get(2) + 1);
        } else if (pelilauta == 2) {
            pisteet.put(1, pisteet.get(1) + 1);
        }
    }

    private boolean kasitteleLaiva(Laiva laiva, Ruutu siirto) {
        if (laiva != null) {
            laiva.lisaaOsuma(siirto);
            return true;
        }
        return false;
    }

    public void vuoroPelattu() {
        vuoro++;
    }

    public int haeVuoro() {
        return this.vuoro;
    }

    public boolean jatkuukoPeli() {
        return (voittaakoPelaaja(1) || voittaakoPelaaja(2));
    }

    private boolean voittaakoPelaaja(int pelaaja) {
        return pisteet.get(pelaaja) >= pistemaaraVoittoon;
    }

    public int haeVoittaja() {
        if (voittaakoPelaaja(1) && voittaakoPelaaja(2)) {
            return 0;
        } else if (voittaakoPelaaja(1)) {
            return 1;
        } else {
            return 2;
        }
    }

    public Pelilauta haePelilauta(int pelilauta) {
        return pelilaudat.get(pelilauta);
    }

}
