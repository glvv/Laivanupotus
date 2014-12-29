package laivanupotus.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Pelilauta kuvaa Laivanupotus-pelin pelilautaa.
 */

public class Pelilauta {
    
    private final int leveys;
    private final int pituus;
    private final Ruutu[][] ruudut;
    private final HashSet<Ruutu> varatutRuudut;
    /**
     * Konstruktori luo ruudukon ja luo siihen ruudut oikeilla koordinaateilla
     * @param leveys Pelilaudan leveys
     * @param pituus Pelilaudan pituus
     */
    public Pelilauta(int leveys, int pituus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.ruudut = new Ruutu[leveys][pituus];
        this.varatutRuudut = new HashSet<>();
        luoRuudut();
    }

    private void luoRuudut() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < pituus; j++) {
                ruudut[i][j] = new Ruutu(i, j);
            }
        }
    }

    public int haePituus() {
        return pituus;
    }

    public int haeLeveys() {
        return leveys;
    }

    public HashSet<Ruutu> haeVaratutRuudut() {
        return varatutRuudut;
    }

    public Ruutu haeRuutu(int x, int y) {
        return ruudut[x][y];
    }

    public Ruutu[][] haeRuudukko() {
        return ruudut;
    }
    /**
     * Tätä metodia käyttää LaivojenAsettaja-luokka, pitääkseen kirjaa ruuduista, joihin ei saa lisätä laivaa
     * @param ruudut1 varatut Ruutu-oliot 
     */
    public void lisaaVarattujaRuutuja(Ruutu[] ruudut1) {
        varatutRuudut.addAll(Arrays.asList(ruudut1));
    }
    
    /**
     * Metodi palauttaa pelilaudan ruudut, joihin on osuttu
     * @return Lista ruuduista, joihin on osuttu
     */
    public ArrayList<Ruutu> haeRuudutJoihinOnOsuttu() {
        ArrayList<Ruutu> osututRuudut = new ArrayList<>();
        for (Ruutu[] ruudukko : ruudut) {
            for (Ruutu ruutu : ruudukko) {
                if (ruutu.haeOsuttu()) {
                    osututRuudut.add(ruutu);
                }
            }
        }
        return osututRuudut;
    }

}
