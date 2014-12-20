package laivanupotus.logiikka;

import java.util.HashMap;
import java.util.List;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;


public class Logiikka {
    
    private int vuoro;
    private Pelilauta pelilautaPelaaja1;
    private Pelilauta pelilautaPelaaja2;
    private List<Laiva> laivatPelaaja1;
    private List<Laiva> laivatPelaaja2;
    private LaivojenAsettaja laivojenAsettaja;
    
    public void alustaLaivanupotus(int pituus, int leveys, HashMap<Integer, Integer> laivat, boolean laivatSaavatKoskettaa) {
        this.pelilautaPelaaja1 = new Pelilauta(pituus, leveys);
        
    }
    
}
