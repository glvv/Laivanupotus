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
    private final HashMap<Integer, Pelilauta> pelilaudat;

    /**
     * Konstruktori luo ja alustaa pelin komponentit
     *
     * @param asetukset Pelin asetukset sisältävä olio
     */
    public Logiikka(Asetukset asetukset) {
        this.pelilaudat = new HashMap<>();
        arvoLaivatJaLuoPelaajat(asetukset);
        this.vuoro = 1;
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
        return kasitteleLaiva(laiva, siirto);
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

    public boolean voittaakoJompikumpi() {
        return (voittaakoPelaaja(1) || voittaakoPelaaja(2));
    }

    public boolean voittaakoPelaaja(int pelaaja) {
        return pelilaudat.get(pelaaja).kaikkiLaivatUpotettu();
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
    
    public ArrayList<Ruutu> haeUpotettujenLaivojenRuudut(int pelilauta) {
        return pelilaudat.get(pelilauta).haeUponneidenLaivojenRuudut();
    }
    
    public ArrayList<Ruutu> haeLaivojenRuudut(int pelilauta) {
        return pelilaudat.get(pelilauta).haeLaivojenRuudut();
    }
    
    public boolean upottikoSiirtoLaivan(Ruutu siirto, int pelilauta) {
        return pelilaudat.get(pelilauta).uppoaakoRuutuunLiittyvaLaiva(siirto);
    }

}
