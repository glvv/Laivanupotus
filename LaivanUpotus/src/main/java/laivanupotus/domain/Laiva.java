package laivanupotus.domain;

import java.util.HashMap;

/**
 * Laiva kuvaa Laivanupotus-pelin laivaa.
 */
public class Laiva {

    private final Ruutu[] ruudut;
    private final HashMap<Ruutu, Boolean> osumat;

    /**
     * Konstruktorissa luodaan laiva, jolla 
     * on parametrina annetut ruudut
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
     * Metodi käy lapi laivan ruudut, jos yksikin on ilman osumaa palautetaan false
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

    public HashMap haeOsumat() {
        return osumat;
    }

    public void lisaaOsuma(Ruutu osuma) {
        osumat.put(osuma, true);
    }

}
