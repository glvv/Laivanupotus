package laivanupotus.domain;

import java.util.HashMap;
import java.util.List;

/**
 * Pelilauta - oliosta voi katsoa osuuko siirto.
 */
public class Pelilauta {

    private final HashMap<Ruutu, Laiva> laivaRuudukko;

    /**
     * Konstruktori luo tyhjän HashMap - olion ja käy lapi parametrina annetut
     * Laiva-oliot
     *
     *
     * @param laivat Pelilaudalla olevat laivat
     */
    public Pelilauta(List<Laiva> laivat) {
        this.laivaRuudukko = new HashMap<>();
        liitaLaivat(laivat);
    }

    private void liitaLaivat(List<Laiva> laivat) {
        for (Laiva laiva : laivat) {
            for (Ruutu ruutu : laiva.haeRuudut()) {
                laivaRuudukko.put(ruutu, laiva);
            }
        }
    }

    /**
     * Metodi palauttaa ruutuun liittyvän laivan, jos ruutuun ei liity laivaa
     * metodi palauttaa null-viitteen
     *
     * @param siirto Ruutu - oliona annettu siirto, jota käytetään HashMap-olion avaimena
     * @return Ruutuun liittyvä laiva
     */
    public Laiva haeLaiva(Ruutu siirto) {
        return laivaRuudukko.get(siirto);
    }

}
