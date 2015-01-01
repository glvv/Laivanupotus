package laivanupotus.logiikka;

import java.util.ArrayList;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelaaja;
import laivanupotus.domain.Ruutu;

/**
 * Luokka tarjoaa metodeja siirtojen vastaanottamiseen ja pelitilanteen
 * käsittelyyn.
 */
public class Logiikka {

    private int vuoro;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;

    /**
     * Konstruktori luo ja alustaa pelin komponentit
     *
     * @param asetukset Pelin asetukset sisältävä olio
     */
    public Logiikka(Asetukset asetukset) {
        arvoLaivatJaLuoPelaajat(asetukset);
        this.vuoro = 1;
    }

    private void arvoLaivatJaLuoPelaajat(Asetukset asetukset) {
        LaivojenAsettaja asettaja = new LaivojenAsettaja(asetukset);
        ArrayList<Laiva> pelaaja1Laivat = asettaja.luoLaivatAutomaattisesti();
        ArrayList<Laiva> pelaaja2Laivat = asettaja.luoLaivatAutomaattisesti();
        this.pelaaja1 = new Pelaaja(pelaaja1Laivat);
        this.pelaaja2 = new Pelaaja(pelaaja2Laivat);
    }

    public boolean pelaaVuoroPelaaja1(Ruutu siirto) {
        pelaaja1.lisaaSiirto(siirto);
        Laiva laiva = pelaaja2.katsoSiirto(siirto);
        if (kasitteleLaiva(laiva, siirto)) {
            if (laiva.uppoaako()) {
                pelaaja1.lisaaPiste();
            }
            return true;
        }
        return false;
    }
    
    public boolean pelaaVuoroPelaaja2(Ruutu siirto) {
        pelaaja2.lisaaSiirto(siirto);
        Laiva laiva = pelaaja1.katsoSiirto(siirto);
        if (kasitteleLaiva(laiva, siirto)) {
            if (laiva.uppoaako()) {
                pelaaja2.lisaaPiste();
            }
            return true;
        }
        return false;
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
    
    public int haePisteetPelaaja1() {
        return pelaaja1.haePisteet();
    }
    
    public int haePisteetPelaaja2() {
        return pelaaja2.haePisteet();
    }
    
    public ArrayList haePelaaja1Siirrot() {
        return pelaaja1.haeSiirrot();
    }
    
    public ArrayList haePelaaja2Siirrot() {
        return pelaaja2.haeSiirrot();
    }
    
    public boolean jatkuukoPeli() {
        return (pelaaja1.voittaako() || pelaaja2.voittaako());
    }
    
    public int haeVoittaja() {
        if (pelaaja1.voittaako() && pelaaja2.voittaako()) {
            return 0;
        } else if (pelaaja1.voittaako()) {
            return 1;
        } else {
            return 2;
        }
    }

    public Pelaaja haePelaaja1() {
        return pelaaja1;
    }

    public Pelaaja haePelaaja2() {
        return pelaaja2;
    }
    
}
