package laivanupotus.logiikka;

import laivanupotus.domain.Asetukset;

/**
 * Luokka tarjoaa metodeja käyttäjän antamien syötteiden tarkistamiseen.
 */

/*
 Luokka ei ole käytössä, koska virheellisiä syötteitä ei ole mahdollista antaa.
 */
public class SyotteenKasittelija {

    private final Asetukset asetukset;

    /**
     * Konstruktori luo uuden Asetukset-olion, joka asetetaan oliomuuttujaksi.
     */
    public SyotteenKasittelija() {
        this.asetukset = new Asetukset();
    }

    private boolean tarkistaKokonaisluku(String syote) {
        try {
            int luku2 = Integer.parseInt(syote);
        } catch (Exception x) {
            return false;
        }
        return true;
    }

    private boolean tarkistaPelilaudanSivu(String syote) {
        if (tarkistaKokonaisluku(syote)) {
            int luku = Integer.parseInt(syote);
            return luku >= 10 && luku <= 50;
        }
        return false;
    }

    /**
     * Metodilla asetetaan leveys Asetukset-olioon Metodi tarkistaa syötteen ja
     * palauttaa false, jos se on virheellinen Jos syöte on kunnollinen, leveys
     * asetetaan ja metodi palauttaa true
     *
     * @param syote
     * @return Onnistuiko asettaminen
     */
    public boolean asetaLeveys(String syote) {
        if (tarkistaPelilaudanSivu(syote)) {
            asetukset.asetaLeveys(Integer.parseInt(syote));
            return true;
        }
        return false;
    }

    /**
     * Metodilla asetetaan pituus Asetukset-olioon Metodi tarkistaa syötteen ja
     * palauttaa false, jos se on virheellinen Jos syöte on kunnollinen, pituus
     * asetetaan ja metodi palauttaa true
     *
     * @param syote
     * @return Onnistuiko asettaminen
     */
    public boolean asetaPituus(String syote) {
        if (tarkistaPelilaudanSivu(syote)) {
            asetukset.asetaPituus(Integer.parseInt(syote));
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkistaa, että laivan koko ja maara ovat kokonaislukuja
     * annetuissa rajoissa. Jos laivan koko ja maara ovat annetuissa rajoissa,
     * ne lisätään Asetukset-olioon, ja palautetaan true. Jos eivät, niin
     * palautetaan false ja mitään ei aseteta.
     *
     * @param koko Laivan koko, eli kuinka monta ruutua laiva vie.
     * @param maaraSyote Laivojen maara, kuinka monta laivaa?
     * @return Onnistuiko asettaminen vai vai.
     */
    public boolean lisaaLaiva(int koko, String maaraSyote) {
        if (tarkistaKokonaisluku(maaraSyote)) {
            int maara = Integer.parseInt(maaraSyote);
            if (!asetukset.onkoLaivaLisatty(koko)) {
                if (tarkistaKokoJaMaara(koko, maara)) {
                    asetukset.lisaaLaiva(koko, maara);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tarkistaKokoJaMaara(int koko, int maara) {
        int kerroin = asetukset.haeLaivojenMaaranKerroin();
        if (koko == 1) {
            return (maara > 0 && maara < (3 * kerroin));
        } else if (koko == 2) {
            return (maara >= 0 && maara < (2 * kerroin));
        } else if (koko == 3) {
            return (maara >= 0 && maara < (2 * kerroin));
        } else if (koko == 4 || koko == 5) {
            return (maara >= 0 && maara <= (1 * kerroin));
        }
        return false;
    }

    public Asetukset haeAsetukset() {
        return this.asetukset;
    }

    /**
     * Metodi tarkistaa, että parametrina annettu syote on kunnollinen siirto
     *
     * @param syote Tarkistettava syöte
     * @param onkoX Tarkasteellaanko x-akselia vai y akselia
     * @return Onko siirto kunnollinen
     */
    public boolean tarkistaSiirto(String syote, boolean onkoX) {
        if (tarkistaKokonaisluku(syote)) {
            int siirto = Integer.parseInt(syote);
            if (onkoX) {
                if (siirto > 0 && siirto <= asetukset.haePelilautaLeveys()) {
                    return true;
                }
            } else {
                if (siirto > 0 && siirto <= asetukset.haePelilautaPituus()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi tarkistaa, että syöte on kokonaisluku annetulta väliltä.
     * Syötteiden pitää olla välillä 1 - valintojen määrä.
     *
     * @param syote Tarkistettava syöte
     * @param valintoja Valintojen määrä
     * @return Onko valinta kunnollinen
     */
    public boolean tarkistaValinta(String syote, int valintoja) {
        if (tarkistaKokonaisluku(syote)) {
            int valinta = Integer.parseInt(syote);
            for (int i = 1; i <= valintoja; i++) {
                if (valinta == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi palauttaa laivojen maaran kertoimen, joka riippuu pelilaudan
     * leveydestä ja pituudesta.
     *
     * @return Laivojen määrän kerroin.
     */
    public int haeLaivojenMaaranKerroin() {
        return asetukset.haeLaivojenMaaranKerroin();
    }

}
