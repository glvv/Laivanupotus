package laivanupotus.logiikka;

import laivanupotus.domain.Ruutu;

public abstract class Pelaaja {
    final int pelilautaLeveys;
    final int pelilautaKorkeus;

    public Pelaaja(int pelilautaLeveys, int pelilautaKorkeus) {
        this.pelilautaLeveys = pelilautaLeveys;
        this.pelilautaKorkeus = pelilautaKorkeus;
    }
    
    public abstract Ruutu teeSiirto();
}
