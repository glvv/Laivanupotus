package laivanupotus.logiikka;

import java.util.HashMap;

public class SyotteenKasittelija {

    private final Asetukset asetukset;

    public SyotteenKasittelija() {
        this.asetukset = new Asetukset();
    }

    public boolean tarkistaKokonaisluku(String syote) {
        try {
            int luku2 = Integer.parseInt(syote);
        } catch (Exception x) {
            return false;
        }
        return true;
    }

    public boolean tarkistaPelilaudanSivu(String syote) {
        if (tarkistaKokonaisluku(syote)) {
            int luku = Integer.parseInt(syote);
            return luku >= 10 && luku <= 100;
        }
        return false;
    }

    public boolean asetaLeveys(String syote) {
        if (tarkistaPelilaudanSivu(syote)) {
            asetukset.asetaLeveys(Integer.parseInt(syote));
            return true;
        }
        return false;
    }

    public boolean asetaPituus(String syote) {
        if (tarkistaPelilaudanSivu(syote)) {
            asetukset.asetaPituus(Integer.parseInt(syote));
            return true;
        }
        return false;
    }

    public boolean lisaaLaiva(String kokoSyote, String maaraSyote) {
        if (tarkistaKokonaisluku(kokoSyote) && tarkistaKokonaisluku(maaraSyote)) {
            int koko = Integer.parseInt(kokoSyote);
            int maara = Integer.parseInt(maaraSyote);
            if (!asetukset.onkoLaivaLisatty(koko)) {

            }

        }

        return false;
    }

    private boolean tarkistaKoko(int koko) {
        return (koko > 0 && koko < 8);
    }

    private boolean tarkistaMaara(int koko, int maara) {
        if (koko > 0 && koko < 3) {
            return (maara > 0 && maara < 9);
        }
        return false;
    }

}
