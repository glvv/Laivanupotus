package laivanupotus.logiikka;

import laivanupotus.domain.Asetukset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import laivanupotus.domain.Ruutu;

/**
 * Luokka tarjoaa metodin, jolla saadaan siirtoja Laivanupotus-peliin.
 */

/*
 Tekoäly ei tue laivojen asettamista niin, että ne koskevat toisiinsa.
 */
public class Tekoaly {

    private final Asetukset asetukset;
    private final ArrayList<Ruutu> arvatut;
    private final ArrayList<Ruutu> osumat;
    private Random arvaaja;

    public Tekoaly(Asetukset asetukset) {
        this.asetukset = asetukset;
        this.arvatut = new ArrayList<>();
        this.arvaaja = new Random();
        this.osumat = new ArrayList<>();
    }

    /**
     * Metodi palauttaa tekoälyn tekemän siirron. Metodi palauttaa satunnaisen
     * ruudun jos se ei ole tehnyt osumia. Jos osumia on yksi metodi palauttaa
     * viereisen ruudun. Jos osumia on enemmän kuin yksi, metodi palauttaa
     * ruudun jommastakummasta päästä. Metodi tarkistaa, että ruudut ovat
     * pelilaudalla, eikä niitä ole jo arvattu. Metodin toimiminen vaatii
     * lisaaOsuma ja laivaUpotettu - metodien käyttämisen.
     *
     * @see Tekoaly#laivaUpotettu()
     * @see Tekoaly#lisaaOsuma(laivanupotus.domain.Ruutu)
     * @return Tekoalyn tekemä siirto
     */
    public Ruutu teeSiirto() {
        if (osumat.isEmpty()) {
            Ruutu arvaus = arvaaSatunnainen();
            arvatut.add(arvaus);
            return arvaus;
        } else {
            Ruutu arvaus = arvaaOsumienPerusteella();
            arvatut.add(arvaus);
            return arvaus;
        }
    }

    /**
     * Metodilla lisätään siirto, jolla on osuttu laivaan. teeSiirto - metodi
     * käyttää tietoa edellisistä osumista
     *
     * @param siirto Osuttu Ruutu, jossa on laiva
     * @see Tekoaly#teeSiirto()
     */
    public void lisaaOsuma(Ruutu siirto) {
        osumat.add(siirto);
    }

    /**
     * Metodia käytetään kertomaan tekoälylle, että osumien ympäriltä ei saa
     * enää arvata ruutuja. teeSiirto - metodi käyttää tietoa edellisistä
     * osumista
     *
     * @see Tekoaly#teeSiirto()
     */
    public void laivaUpotettu() {
        lisaaOsumiaYmparoivatRuudutArvatuiksi();
        osumat.clear();
    }

    public void asetaArpoja(Random arpoja) {
        this.arvaaja = arpoja;
    }

    private Ruutu arvaaSatunnainen() {
        int x = arvaaja.nextInt(asetukset.haePelilautaLeveys());
        int y = arvaaja.nextInt(asetukset.haePelilautaPituus());
        Ruutu arvaus = new Ruutu(x, y);
        if (!tarkistaArvaus(arvaus)) {
            return arvaaSatunnainen();
        }
        return arvaus;
    }

    private Ruutu arvaaVierekkainen(Ruutu ruutu) {
        int viereinen = arvaaja.nextInt(4);
        Ruutu arvaus;
        if (viereinen == 0) {
            arvaus = new Ruutu(ruutu.haeX() - 1, ruutu.haeY());
        } else if (viereinen == 1) {
            arvaus = new Ruutu(ruutu.haeX() + 1, ruutu.haeY());
        } else if (viereinen == 2) {
            arvaus = new Ruutu(ruutu.haeX(), ruutu.haeY() + 1);
        } else {
            arvaus = new Ruutu(ruutu.haeX(), ruutu.haeY() - 1);
        }
        if (!tarkistaArvaus(arvaus)) {
            return arvaaVierekkainen(ruutu);
        }
        return arvaus;
    }

    private boolean tarkistaArvaus(Ruutu arvaus) {
        if (arvaus.haeX() > asetukset.haePelilautaLeveys() - 1 || arvaus.haeX() < 0) {
            return false;
        }
        if (arvaus.haeY() > asetukset.haePelilautaPituus() - 1 || arvaus.haeY() < 0) {
            return false;
        }
        return !arvatut.contains(arvaus);
    }

    private Ruutu arvaaOsumienPerusteella() {
        if (osumat.size() < 2) {
            return arvaaVierekkainen(osumat.get(0));
        }
        Collections.sort(osumat);
        int arvaus = arvaaja.nextInt(2);
        Ruutu arvattuRuutu;
        if (arvaus == 0) {
            Ruutu ruutu = osumat.get(0);
            if (osumatVaakasuorassa()) {
                arvattuRuutu = new Ruutu(ruutu.haeX() - 1, ruutu.haeY());
            } else {
                arvattuRuutu = new Ruutu(ruutu.haeX(), ruutu.haeY() - 1);
            }
        } else {
            Ruutu ruutu = osumat.get(osumat.size() - 1);
            if (osumatVaakasuorassa()) {
                arvattuRuutu = new Ruutu(ruutu.haeX() + 1, ruutu.haeY());
            } else {
                arvattuRuutu = new Ruutu(ruutu.haeX(), ruutu.haeY() + 1);
            }
        }
        if (!tarkistaArvaus(arvattuRuutu)) {
            return arvaaOsumienPerusteella();
        }
        return arvattuRuutu;
    }

    private boolean osumatVaakasuorassa() {
        Ruutu ensimmainen = osumat.get(0);
        for (int i = 1; i < osumat.size(); i++) {
            if (osumat.get(i).haeY() != ensimmainen.haeY()) {
                return false;
            }
        }
        return true;
    }

    private void lisaaOsumiaYmparoivatRuudutArvatuiksi() {
        for (Ruutu ruutu : osumat) {
            arvatut.addAll(haeRuutuaYmparoivatRuudut(ruutu));
        }
    }

    private ArrayList<Ruutu> haeRuutuaYmparoivatRuudut(Ruutu ruutu) {
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        int x = ruutu.haeX();
        int y = ruutu.haeY();
        ruudut.add(new Ruutu(x + 1, y));
        ruudut.add(new Ruutu(x - 1, y));
        ruudut.add(new Ruutu(x, y + 1));
        ruudut.add(new Ruutu(x, y - 1));
        return ruudut;
    }

}
