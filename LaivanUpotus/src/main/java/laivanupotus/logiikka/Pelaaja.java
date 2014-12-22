package laivanupotus.logiikka;

import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;

public abstract class Pelaaja {
    public Pelilauta pelilauta;

    public Pelaaja(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }
    
    public abstract Ruutu teeSiirto();
}
