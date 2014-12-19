package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;

public class Laivanupotus {
    
    //ditch this class

    private final Pelilauta pelilauta;
    private List<Laiva> laivat;
    private final Random arpoja;

    public Laivanupotus(int leveys, int pituus, HashMap<Integer, Integer> laivojenKokoJaLukumaara) {
        this.pelilauta = new Pelilauta(leveys, pituus);
        this.arpoja = new Random();
    }

    private void luoLaivat(HashMap<Integer, Integer> laivojenKokojaLukumaara) {
        for (Integer laivanKoko : laivojenKokojaLukumaara.keySet()) {
            for (int i = 0; i < laivojenKokojaLukumaara.get(laivanKoko); i++) {
                laivat.add(new Laiva(arvoRuudutLaivalle(laivanKoko)));
            }
        }
    }

    private Ruutu[] arvoRuudutLaivalle(int koko) {
        Ruutu ensimmainenRuutu;
        int ensimmaisenRuudunXKoordinaatti = arpoja.nextInt(pelilauta.haeLeveys());
        int ensimmaisenRuudunYKoordinaatti = arpoja.nextInt(pelilauta.haePituus());
        ensimmainenRuutu = new Ruutu(ensimmaisenRuudunXKoordinaatti, ensimmaisenRuudunYKoordinaatti);
        boolean laivaOnVaakaSuorassa = (arpoja.nextInt(2) == 1);
        for (int i = 0; i < koko; i++) {
            if (laivaOnVaakaSuorassa) {
            }
        }
        return null;
    }

}
