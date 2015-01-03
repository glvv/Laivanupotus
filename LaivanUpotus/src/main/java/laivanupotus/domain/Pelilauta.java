package laivanupotus.domain;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    private ArrayList<Laiva> haeUponneetLaivat() {
        ArrayList<Laiva> uponneet = new ArrayList<>();
        for (Laiva laiva : laivat) {
            if (laiva.uppoaako()) {
                uponneet.add(laiva);
            }
        }
        return uponneet;
    }
    
    public ArrayList<Ruutu> haeUponneidenLaivojenRuudut() {
        ArrayList<Ruutu> upotetutLaivat = new ArrayList<>();
        for (Laiva laiva : haeUponneetLaivat()) {
            upotetutLaivat.addAll(Arrays.asList(laiva.haeRuudut()));
        }
        return upotetutLaivat;
    }
    
    public ArrayList<Ruutu> haeLaivojenRuudut() {
        ArrayList<Ruutu> laivojenRuudut = new ArrayList<>();
        for (Laiva laiva : laivat) {
            laivojenRuudut.addAll(Arrays.asList(laiva.haeRuudut()));
        }
        return laivojenRuudut;
    }
    
    public boolean uppoaakoRuutuunLiittyvaLaiva(Ruutu siirto) {
        return laivaRuudukko.get(siirto).uppoaako();
    }
    
    public boolean kaikkiLaivatUpotettu() {
        return (haeUponneetLaivat().size() == laivat.size());
    }
    
    
    
    

}
