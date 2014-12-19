package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;

public class LaivojenArpoja {

    private final ArrayList<Laiva> laivat;
    private final Random arpoja;
    private final HashMap<Integer, Integer> laivojenKokoJaLukumaara;
    private Pelilauta pelilauta;

    public LaivojenArpoja(HashMap<Integer, Integer> laivojenKokoJaLukumaara) {
        this.laivojenKokoJaLukumaara = laivojenKokoJaLukumaara;
        this.laivat = new ArrayList<>();
        this.arpoja = new Random();
    }

    public void luoLaivat(HashMap<Integer, Integer> laivojenKokojaLukumaara) {
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
                if (ensimmaisenRuudunXKoordinaatti + koko > pelilauta.haeLeveys()) {
                    
                }
            }
        }
        return null;
    }
    
    private boolean onkoRuuduissaJoLaiva() {
        return true;
    }
}
