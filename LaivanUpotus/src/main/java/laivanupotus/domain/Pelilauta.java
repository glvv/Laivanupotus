package laivanupotus.domain;

public class Pelilauta {
    private final int leveys;
    private final int pituus;
    private final Ruutu[][] ruudut;

    public Pelilauta(int leveys, int pituus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.ruudut = new Ruutu[leveys][pituus];
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
    
    
    
    
    
    
}
