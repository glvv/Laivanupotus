package laivanupotus.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Pelilauta {

    private final ArrayList<Laiva> laivat;    
    private final ArrayList<Ruutu> siirrot;
    private final HashMap<Ruutu, Laiva> laivaRuudukko;

    public Pelilauta(ArrayList<Laiva> laivat) {
        this.laivat = laivat;
        this.siirrot = new ArrayList<>();
        this.laivaRuudukko = new HashMap<>();
        liitaLaivat();
    }
    
    private void liitaLaivat() {
        for (Laiva laiva : laivat) {
            for (Ruutu ruutu : laiva.haeRuudut()) {
                laivaRuudukko.put(ruutu, laiva);
            }
        }
    }

    public void lisaaSiirto(Ruutu ruutu) {
        this.siirrot.add(ruutu);
    }

    public ArrayList haeSiirrot() {
        return this.siirrot;
    }

    public Laiva katsoRuutu(Ruutu siirto) {
        return laivaRuudukko.get(siirto);
    }

    public ArrayList haeLaivat() {
        return this.laivat;
    }
    
    public ArrayList<Laiva> haeUponneetLaivat() {
        ArrayList<Laiva> uponneet = new ArrayList<>();
        for (Laiva laiva : laivat) {
            if (laiva.uppoaako()) {
                uponneet.add(laiva);
            }
        }
        return uponneet;
    }

}
