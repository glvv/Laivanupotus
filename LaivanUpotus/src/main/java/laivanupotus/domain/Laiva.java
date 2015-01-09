package laivanupotus.domain;

import java.util.HashMap;

/**
 * Laiva kuvaa Laivanupotus-pelin laivaa. Laiva-olio pitää kirjaa sen
 * muodostamista ruuduista sekä siihen kohdistuneista osumista. Laiva-oliolta
 * voi kysyä uppoaako se.
 */
public class Laiva {

    private final Ruutu[] ruudut;
    private final HashMap<Ruutu, Boolean> osumat;

    /**
     * Konstruktorissa luodaan laiva, jolla on parametrina annetut ruudut.
     * Alussa laivalle ei ole osumia.
     *
     * @param ruudut Laivan muodostavat ruudut
     */
    public Laiva(Ruutu... ruudut) {
        this.ruudut = ruudut;
        this.osumat = new HashMap<>();
        alustaOsumat();
    }

    private void alustaOsumat() {
        for (Ruutu ruutu : ruudut) {
            osumat.put(ruutu, false);
        }
    }

    /**
     * Metodi käy lapi laivan ruudut, jos yksikin on ilman osumaa palautetaan
     * false
     *
     * @return Onko Laiva uponnut
     */
    public boolean uppoaako() {
        for (Ruutu avain : osumat.keySet()) {
            if (osumat.get(avain) == false) {
                return false;
            }
        }
        return true;
    }

    public Ruutu[] haeRuudut() {
        return ruudut;
    }

    /**
     * Metodi merkkaa laivalle osuman parametrissa annettuun ruutuun, jos se
     * kuuluu laivan ruutuihin.
     *
     * @param osuma Ruutu, johon on osuttu.
     */
    public void lisaaOsuma(Ruutu osuma) {
        if (osumat.keySet().contains(osuma)) {
            osumat.put(osuma, true);
        }
    }

    public HashMap haeOsumat() {
        return this.osumat;
    }

}
