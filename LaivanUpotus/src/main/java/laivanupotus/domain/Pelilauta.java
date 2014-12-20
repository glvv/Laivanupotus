package laivanupotus.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Pelilauta {
    private final int leveys;
    private final int pituus;
    private final Ruutu[][] ruudut;
    private final HashSet<Ruutu> varatutRuudut;

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
    
    public void lisaaVarattujaRuutuja(Ruutu[] ruudut1) {
        varatutRuudut.addAll(Arrays.asList(ruudut1));
    }
    
    
    
    
}
