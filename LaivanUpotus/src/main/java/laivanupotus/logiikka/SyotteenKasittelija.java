package laivanupotus.logiikka;

import java.util.HashMap;

public class SyotteenKasittelija {
    
    private Asetukset asetukset;

    public SyotteenKasittelija() {
        this.asetukset = new Asetukset();
    }
    
    public boolean tarkistaKokonaisluku(String syote) {
        int luku2;
        try {
            luku2 = Integer.parseInt(syote);
        } catch (Exception x) {
            return false;
        }
        return true;
    }
    
    public boolean tarkistaPelilaudanSivu(String syote) {
        if (tarkistaKokonaisluku(syote)) {
            int luku = Integer.parseInt(syote);
            if (luku >= 10 && luku <= 100) {
                return true;
            }
        }
        return false;
    }
    
//    public boolean asetaLeveys(String syote) {
//        if (tarkistaPelilaudanSivu(syote)) {
//            asetukset.asetaLeveys(leveys);
//        }
//    }
    
    
    
    
    
    
    
}
