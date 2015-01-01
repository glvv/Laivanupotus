package laivanupotus.domain;

import java.util.ArrayList;

public class Pelaaja {

    private final ArrayList<Laiva> laivat;
    private final Pelilauta pelilauta;
    private final ArrayList<Ruutu> siirrot;
    private int pisteet;

    public Pelaaja(ArrayList<Laiva> laivat) {
        this.laivat = laivat;
        this.pelilauta = new Pelilauta(laivat);
        this.siirrot = new ArrayList<>();
        this.pisteet = 0;
    }

    public void lisaaSiirto(Ruutu ruutu) {
        this.siirrot.add(ruutu);
    }

    public ArrayList haeSiirrot() {
        return this.siirrot;
    }

    public void lisaaPiste() {
        pisteet++;
    }

    public int haePisteet() {
        return pisteet;
    }

    public Laiva katsoSiirto(Ruutu siirto) {
        return pelilauta.haeLaiva(siirto);
    }

    public boolean voittaako() {
        return (pisteet >= laivat.size());
    }

    public ArrayList haeLaivat() {
        return this.laivat;
    }

}
