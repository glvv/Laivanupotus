package laivanupotus.logiikka;

import java.util.ArrayList;
import laivanupotus.domain.Asetukset;
import laivanupotus.domain.Ruutu;

/**
 * TekoalyPelaaja pitää käyttää ja päivitäää TekoAly-oliota.
 */
public class TekoalyPelaaja {

    private final ArrayList<Ruutu> siirrot;
    private final ArrayList<Ruutu> osumat;
    private final Tekoaly tekoaly;
    private final Logiikka logiikka;

    /**
     * Konstruktorissa luodaan tekoäly, sekä listat siirroille ja osumille.
     *
     * @param asetukset Pelin asetukset.
     * @param logiikka Pelin logiikka.
     */
    public TekoalyPelaaja(Asetukset asetukset, Logiikka logiikka) {
        this.siirrot = new ArrayList<>();
        this.osumat = new ArrayList<>();
        this.tekoaly = new Tekoaly(asetukset);
        this.logiikka = logiikka;
    }

    /**
     * Metodi suorittaa tekoälyn siirrot ja mahdolliset uudet vuorot. Metodi
     * huolehtii osumien ja laivan uppoamisen ilmoittamisesta Tekoaly-oliolle.
     */
    public void teeSiirto() {
        Ruutu siirto = tekoaly.teeSiirto();
        siirrot.add(siirto);
        if (logiikka.katsoSiirtoPelilaudasta(siirto, 2)) {
            osumat.add(siirto);
            tekoaly.lisaaOsuma(siirto);
            if (logiikka.upottikoSiirtoLaivan(siirto, 2)) {
                tekoaly.laivaUpotettu();
            }
            teeSiirto();
        }
    }

    /**
     * Palauttaa vuorossa tehdyt siirrot listana.
     *
     * @return Vuorossa tehdyt siirrot.
     */
    public ArrayList<Ruutu> haeTehdytSiirrot() {
        return siirrot;
    }

    /**
     * Palauttaa vuorossa tehdyt osumat listana.
     *
     * @return Vuorossa tehdyt osumat.
     */
    public ArrayList<Ruutu> haeOsumat() {
        return osumat;
    }

    /**
     * Tyhjentää osumat ja siirrot. Metodia kuuluu käyttää, kun siirrot ja
     * osumat on haettu.
     */
    public void vuoroPelattu() {
        siirrot.clear();
        osumat.clear();
    }
    
    public Tekoaly haeTekoaly() {
        return this.tekoaly;
    }

}
