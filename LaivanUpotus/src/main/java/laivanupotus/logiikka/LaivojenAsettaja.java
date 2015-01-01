package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Ruutu;

/**
 * Luokka tarjoaa toiminnallisuuden automaattiseen laivojen asettamiseen
 * ruudukkoon.
 */
public class LaivojenAsettaja {

    private Random arpoja;
    private HashSet<Ruutu> varatutRuudut;
    private final Asetukset asetukset;

    /**
     * Konstruktorissa luodaan uusi LaivojenAsettaja-olio
     *
     * @param asetukset Pelin asetukset
     */
    public LaivojenAsettaja(Asetukset asetukset) {
        this.arpoja = new Random();
        this.varatutRuudut = null;
        this.asetukset = asetukset;
    }

    /**
     * Metodi arpoo laivojen sijainnit Asetukset-olion sisältämän ohjeen mukaan
     * ja palauttaa Laiva-oliot listana
     *
     * @return Lista, joka sisältää luodut laivat
     */
    public ArrayList luoLaivatAutomaattisesti() {
        this.varatutRuudut = new HashSet<>();
        ArrayList<Laiva> laivat = new ArrayList<>();
        for (Integer laivanKoko : asetukset.haeLaivat().keySet()) {
            for (int i = 0; i < asetukset.haeLaivat().get(laivanKoko); i++) {
                Laiva uusi = (new Laiva(arvoRuudutLaivalle(laivanKoko)));
                laivat.add(uusi);
                lisaaRuudutVaratuiksi(uusi);
            }
        }
        return laivat;
    }

    public void asetaArpoja(Random arpoja) {
        this.arpoja = arpoja;
    }

    private Ruutu[] arvoRuudutLaivalle(int koko) {
        Ruutu ensimmainenRuutu = arvoRuutu();
        boolean laivaOnPystySuorassa = (arpoja.nextInt(2) == 1);
        Ruutu[] ruudut = new Ruutu[koko];
        ruudut[0] = ensimmainenRuutu;
        if (laivaOnPystySuorassa) {
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

    private Ruutu arvoRuutu() {
        int ensimmaisenRuudunXKoordinaatti = arpoja.nextInt(asetukset.haePelilautaLeveys());
        int ensimmaisenRuudunYKoordinaatti = arpoja.nextInt(asetukset.haePelilautaPituus());
        return new Ruutu(ensimmaisenRuudunXKoordinaatti, ensimmaisenRuudunYKoordinaatti);
    }

    private boolean voikoLaivanAsettaa(Ruutu[] ruudut) {
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

    private boolean onPelilaudalla(Ruutu ruutu) {
        if (ruutu.haeX() > asetukset.haePelilautaLeveys() - 1) {
            return false;
        }
        return ruutu.haeY() <= asetukset.haePelilautaPituus() - 1;
    }

    private boolean eiOleVaratullaRuudulla(Ruutu ruutu) {
        for (Ruutu ruutu2 : varatutRuudut) {
            if (ruutu2.equals(ruutu)) {
                return false;
            }
        }
        return true;
    }

    private void lisaaRuudutVaratuiksi(Laiva laiva) {
        if (!asetukset.laivatSaaKoskea()) {
            lisaaVarattujaRuutuja(luoLaivaaYmparoivatRuudut(laiva));
        }
        lisaaVarattujaRuutuja(laiva.haeRuudut());
    }

    private void lisaaVarattujaRuutuja(Ruutu[] ruudut) {
        varatutRuudut.addAll(Arrays.asList(ruudut));
    }

    private Ruutu[] luoLaivaaYmparoivatRuudut(Laiva laiva) {
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

}
