package laivanupotus.logiikka;

import laivanupotus.domain.Asetukset;
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
     * Konstruktori luo ja alustaa pelin komponentit.
     * Konstruktori luo LaivojenAsettaja-olion, 
     * @param asetukset Pelin asetukset sisältävä olio
     */
    public Logiikka(Asetukset asetukset) {
        this.pelilaudat = new HashMap<>();
        arvoLaivatJaLuoPelilaudat(asetukset);
        this.vuoro = 1;
    }

    private void arvoLaivatJaLuoPelilaudat(Asetukset asetukset) {
        ArrayList<Laiva> pelaaja1Laivat = luolaivat(asetukset);
        ArrayList<Laiva> pelaaja2Laivat = luolaivat(asetukset);
        pelilaudat.put(1, new Pelilauta(pelaaja1Laivat));
        pelilaudat.put(2, new Pelilauta(pelaaja2Laivat));
    }
    
    private ArrayList<Laiva> luolaivat(Asetukset asetukset) {
        ArrayList<Laiva> laivat;
        LaivojenAsettaja asettaja = new LaivojenAsettaja(asetukset);
        while (true) {
            try {
                laivat = asettaja.luoLaivatAutomaattisesti();
                break;
            } catch (Exception x) {
            }
        }
        return laivat;
    }

    /**
     * Metodi palauttaa true tai false riippuen siitä, liittyykö kyseiseen
     * Ruutu-oliona annettuun siirtoon Laiva-oliota. Jos Laiva-olio löytyy,
     * siihen lisätään osuma.
     *
     * @param siirto Tarkastettava siirto.
     * @param pelilauta Kummalla pelilaudalla siirto on tehty (1 tai 2).
     * @return Osuiko siirto vai ei.
     */
    public boolean katsoSiirtoPelilaudasta(Ruutu siirto, int pelilauta) {
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

    /**
     * Metodi lisää vuoroa yhdellä.
     */
    public void vuoroPelattu() {
        vuoro++;
    }

    public int haeVuoro() {
        return this.vuoro;
    }

    /**
     * Metodi kertoo voittaako jompikumpi pelaajista.
     *
     * @return Voittaako jompukumpi pelaajista.
     */
    public boolean voittaakoJompikumpi() {
        return (voittaakoPelaaja(1) || voittaakoPelaaja(2));
    }

    /**
     * Metodi kertoo voittaako annettu pelaaja.
     *
     * @param pelaaja Käyttääkö pelaaja pelilautaa 1 vai 2?
     * @return Voittaako pelaaja.
     */
    public boolean voittaakoPelaaja(int pelaaja) {
        return pelilaudat.get(pelaaja).kaikkiLaivatUpotettu();
    }

    /**
     * Metodi kertoo kuka voittaa. Metodia tulee käyttää kun on varmaa, että
     * joku on voittanut.
     *
     * @return 0 jos tasapeli. 1 jos Pelaaja1 on voittanut. 2 jos pelaaja2 on
     * voittanut. 4 jos voittajaa ei ole.
     */
    public int haeVoittaja() {
        if (voittaakoPelaaja(1) && voittaakoPelaaja(2)) {
            return 0;
        } else if (voittaakoPelaaja(1)) {
            return 1;
        } else if (voittaakoPelaaja(2)) {
            return 2;
        }
        return 4;
    }

    /**
     * Metodi palauttaa numeroa vastaavan pelilaudan.
     *
     * @param pelilauta Kumpi pelilauta haetaan? (1 tai 2).
     * @return Numeroa vastaava pelilauta.
     */
    public Pelilauta haePelilauta(int pelilauta) {
        return pelilaudat.get(pelilauta);
    }

    /**
     * Metodi palauttaa numeroa vastaavan pelilaudan upotettujen laivojen
     * ruudut.
     *
     * @param pelilauta Kummalta pelilaudalta ruudut haetaan? (1 tai 2).
     * @return Parametria vastaavan pelilaudan upotettujen laivojen ruudut
     * listana.
     */
    public ArrayList<Ruutu> haeUpotettujenLaivojenRuudut(int pelilauta) {
        return pelilaudat.get(pelilauta).haeUponneidenLaivojenRuudut();
    }

    /**
     * Metodi palauttaa numeroa vastaavan pelilaudan kaikkien laivojen ruudut.
     *
     * @param pelilauta Kummalta pelilaudalta ruudut haetaan? (1 tai 2).
     * @return Parametria vastaavan pelilaudan kaikkien laivojen ruudut listana.
     */
    public ArrayList<Ruutu> haeLaivojenRuudut(int pelilauta) {
        return pelilaudat.get(pelilauta).haeLaivojenRuudut();
    }

    /**
     * Metodi kertoo onko siirtoon liittyvä laiva uponnut. Jos siirtoon ei liity
     * laivaa, palautetaan false.
     *
     * @param siirto Ruutu, johon liittyvä laiva tarkastetaan.
     * @param pelilauta Kumman pelilaudan laivoja tarkastellaan (1 tai 2).
     * @return Onko ruutuun liittyvä laiva uponnut vai ei.
     */
    public boolean upottikoSiirtoLaivan(Ruutu siirto, int pelilauta) {
        return pelilaudat.get(pelilauta).uppoaakoRuutuunLiittyvaLaiva(siirto);
    }

    /**
     * Metodi kertoo onko parametrina saadun pelilaudan vuoro vai ei.
     *
     * @param pelilauta Kumman pelilaudan vuoro tarkistetaan (1 tai 2).
     * @return Onko vuoro vai ei.
     */
    public boolean oikeaVuoro(int pelilauta) {
        return pelilauta % 2 == vuoro % 2;
    }

    /**
     * Metodi kertoo onko tasoittava vuoro pelattu. Koska Pelaaja 1 aloittaa
     * aina, metodi palauttaa true jos on pelaaja yhden vuoro.
     *
     * @return Onko pelaaja2 tai tekoaly juuri pelannut vuoron.
     */
    public boolean tasoittavaVuoroPelattu() {
        return vuoro % 2 == 1;
    }

}
