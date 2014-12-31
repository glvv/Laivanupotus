package laivanupotus.domain;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {
    
    private final List<Laiva> laivat;
    private final Pelilauta pelilauta;
    private final ArrayList<Ruutu> siirrot;
    
    public Pelaaja(ArrayList<Laiva> laivat) {
        this.laivat = laivat;
        this.pelilauta = new Pelilauta(laivat);
        this.siirrot = new ArrayList<>();
    }
    
    public void lisaaSiirto(Ruutu ruutu) {
        this.siirrot.add(ruutu);
    }
    
    public ArrayList haeSiirrot() {
        return this.siirrot;
    }
    
    public int haePisteet() {
        int pisteet = 0;
        for (Laiva laiva : laivat) {
            if (laiva.uppoaako()) {
                pisteet++;
            }
        }
        return pisteet;
    }
    
    public Laiva katsoSiirto(Ruutu siirto) {
        return pelilauta.haeLaiva(siirto);
    }
    
    public boolean voittaako() {
        return (haePisteet() == laivat.size());
    }
    
}
