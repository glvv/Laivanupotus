package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;
/**
 * Luokka tarjoaa toiminnallisuuden automaattiseen laivojen asettamiseen ruudukkoon.
 */
public class LaivojenAsettaja {

    private final Random arpoja;
    private Pelilauta pelilauta;
    private final boolean laivatSaaKoskea;
    /**
     * Konstruktorissa luodaan uusi LaivojenAsettaja-olio
     * Olion pelilauta saa ensin null-viitteen
     * @param laivatSaaKoskea Saavatko laivat koskea toisiinsa
     */
    public LaivojenAsettaja(boolean laivatSaaKoskea) {
        this.arpoja = new Random();
        this.pelilauta = null;
        this.laivatSaaKoskea = laivatSaaKoskea;
    }
    /**
     * Metodi arpoo laivojen sijainnit HashMap-olion sisältämän ohjeen mukaan ja palauttaa Laiva-oliot listana
     * @param laivojenKokoJaLukumaara HashMap, jossa on avaimena laivan koko ja arvoina lukumaara
     * @param pelilauta Pelilauta, joka pitää kirjaa varatuista ruuduista
     * @return Lista, joka sisältää luodut laivat
     */
    public ArrayList luoLaivatAutomaattisesti(HashMap<Integer, Integer> laivojenKokoJaLukumaara, Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
        ArrayList<Laiva> laivat = new ArrayList<>();
        for (Integer laivanKoko : laivojenKokoJaLukumaara.keySet()) {
            for (int i = 0; i < laivojenKokoJaLukumaara.get(laivanKoko); i++) {
                Laiva uusi = (new Laiva(arvoRuudutLaivalle(laivanKoko)));
                laivat.add(uusi);
                lisaaRuudutVaratuiksi(uusi);
            }
        }
        return laivat;
    }

    public Ruutu[] arvoRuudutLaivalle(int koko) {
        Ruutu ensimmainenRuutu = arvoRuutu();
        boolean laivaOnVaakaSuorassa = (arpoja.nextInt(2) == 1);
        Ruutu[] ruudut = new Ruutu[koko];
        ruudut[0] = ensimmainenRuutu;
        if (laivaOnVaakaSuorassa) {
            for (int i = 1; i < koko; i++) {
                ruudut[i] = (new Ruutu(ensimmainenRuutu.haeX(), ensimmainenRuutu.haeY() + i));
            }
        } else {
            for (int i = 1; i < koko; i++) {
                ruudut[i] = (new Ruutu(ensimmainenRuutu.haeX() + i, ensimmainenRuutu.haeY()));
            }
        }
        if (voikoLaivanAsettaa(ruudut)) {
            return ruudut;
        } else {
            return arvoRuudutLaivalle(koko);
        }
    }

    public Ruutu arvoRuutu() {
        int ensimmaisenRuudunXKoordinaatti = arpoja.nextInt(pelilauta.haeLeveys());
        int ensimmaisenRuudunYKoordinaatti = arpoja.nextInt(pelilauta.haePituus());
        return new Ruutu(ensimmaisenRuudunXKoordinaatti, ensimmaisenRuudunYKoordinaatti);
    }

    public boolean voikoLaivanAsettaa(Ruutu[] ruudut) {
        for (Ruutu ruutu : ruudut) {
            if (!onPelilaudalla(ruutu)) {
                return false;
            }
            if (!eiOleVaratullaRuudulla(ruutu)) {
                return false;
            }
        }
        return true;
    }

    public boolean onPelilaudalla(Ruutu ruutu) {
        if (ruutu.haeX() > pelilauta.haeLeveys() - 1) {
            return false;
        }
        return ruutu.haeY() <= pelilauta.haePituus() - 1;
    }

    public boolean eiOleVaratullaRuudulla(Ruutu ruutu) {
        HashSet<Ruutu> varatutRuudut = pelilauta.haeVaratutRuudut();
        for (Ruutu ruutu2 : varatutRuudut) {
            if (ruutu2.equals(ruutu)) {
                return false;
            }
        }
        return true;
    }

    public void lisaaRuudutVaratuiksi(Laiva laiva) {
        if (!laivatSaaKoskea) {
            pelilauta.lisaaVarattujaRuutuja(luoLaivaaYmparoivatRuudut(laiva));
        }
        pelilauta.lisaaVarattujaRuutuja(laiva.haeRuudut());
    }

    public Ruutu[] luoLaivaaYmparoivatRuudut(Laiva laiva) {
        HashSet<Ruutu> ruudut = new HashSet<>();
        for (Ruutu ruutu : laiva.haeRuudut()) {
            int x = ruutu.haeX();
            int y = ruutu.haeY();
            ruudut.add(new Ruutu(x + 1, y));
            ruudut.add(new Ruutu(x, y + 1));
            ruudut.add(new Ruutu(x - 1, y));
            ruudut.add(new Ruutu(x, y - 1));
        }
        Ruutu[] ymparoivat = new Ruutu[ruudut.size()];
        ruudut.toArray(ymparoivat);
        return ymparoivat;
    }
    
    public Pelilauta haePelilauta() {
        return this.pelilauta;
    }
    
    public boolean laivatSaaKoskea() {
        return laivatSaaKoskea;
    }
    
    public void asetaPelilauta(Pelilauta pelilauta) {
        this.pelilauta = pelilauta;
    }
    

}
