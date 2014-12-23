package laivanupotus.logiikka;

import java.util.HashMap;
import java.util.List;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;
import laivanupotus.kayttoliittyma.Paivitettava;


public class Logiikka {
    
    private int vuoro;
    private final Pelilauta pelilautaPelaaja1;
    private final Pelilauta pelilautaPelaaja2;
    private final List<Laiva> laivatPelaaja1;
    private final List<Laiva> laivatPelaaja2;
    private final LaivojenAsettaja laivojenAsettaja;
    private Paivitettava paivitettava;
    private final Tekoaly tekoaly;
    
    public Logiikka(int leveys, int pituus, HashMap<Integer, Integer> laivat, boolean laivatSaavatKoskettaa) {
        this.pelilautaPelaaja1 = new Pelilauta(leveys, pituus);
        this.pelilautaPelaaja2 = new Pelilauta(leveys, pituus);
        this.laivojenAsettaja = new LaivojenAsettaja(false);
        this.laivatPelaaja1 = laivojenAsettaja.luoLaivatAutomaattisesti(laivat, pelilautaPelaaja1);
        this.laivatPelaaja2 = laivojenAsettaja.luoLaivatAutomaattisesti(laivat, pelilautaPelaaja2);
        this.vuoro = 1;
        this.tekoaly = new Tekoaly(leveys, pituus);
    }
    
    public void pelaaVuoro(int[] siirto) {
        Ruutu pelilaudanRuutu = pelilautaPelaaja2.haeRuutu(siirto[0], siirto[1]);
        pelilaudanRuutu.onAmmuttu();
        tekoalynVuoro();
        vuoro++;
        paivitettava.paivity();
    }
    
    public void tekoalynVuoro() {
        Ruutu siirto = tekoaly.teeSiirto();
        Ruutu pelilaudanRuutu = pelilautaPelaaja1.haeRuutu(siirto.haeX(), siirto.haeY());
        pelilaudanRuutu.onAmmuttu();
        if (pelilaudanRuutu.sisaltaaLaivan()) {
            tekoaly.lisaaOsuma(pelilaudanRuutu);
        }
    }
    
    public int haeVuoro() {
        return this.vuoro;
    }
    
    public Pelilauta haePelaaja1Pelilauta() {
        return this.pelilautaPelaaja1;
    }
    
    public Pelilauta haePelaaja2Pelilauta() {
        return this.pelilautaPelaaja2;
    }
    
    public List<Laiva> haePelaaja1Laivat() {
        return laivatPelaaja1;
    }
    
    public List<Laiva> haePelaaja2Laivat() {
        return laivatPelaaja2;
    }
    
    
    
    
    
}
