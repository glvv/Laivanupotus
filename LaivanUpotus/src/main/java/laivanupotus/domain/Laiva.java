package laivanupotus.domain;

import java.util.HashMap;

public class Laiva {

    private final Ruutu[] ruudut;
    private final HashMap<Ruutu, Boolean> osumat; 

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
