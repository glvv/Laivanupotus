package laivanupotus.kayttoliittyma;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import laivanupotus.domain.Ruutu;
import laivanupotus.logiikka.Asetukset;
import laivanupotus.logiikka.Logiikka;
import laivanupotus.logiikka.Tekoaly;

public class LaivanupotusGUILogiikka {

    private final Logiikka logiikka;
    private final HashMap<Integer, HashMap<Ruutu, JButton>> pelilaudat;
    private final HashMap<Integer, JTextField> tekstikentat;
    private final Asetukset asetukset;
    private final LaivanupotusGUI gui;
    private Tekoaly tekoaly;

    public LaivanupotusGUILogiikka(Asetukset asetukset, LaivanupotusGUI gui) {
        this.asetukset = asetukset;
        this.gui = gui;
        this.logiikka = new Logiikka(asetukset);
        this.pelilaudat = new HashMap<>();
        pelilaudat.put(1, new HashMap<Ruutu, JButton>());
        pelilaudat.put(2, new HashMap<Ruutu, JButton>());
        this.tekstikentat = new HashMap<>();
        tekoaly = null;
    }

    public void tarkistaOnkoYksinPeliJaLuoTekoalyJosOn() {
        if (!asetukset.onkoKaksinpeli()) {
            this.tekoaly = new Tekoaly(asetukset);
            asetaRuudutToimimattomiksi(2);
            naytaPelaajanLaivat();
        }
    }

    public void kokeileSiirto(Ruutu ruutu, int pelilauta) {
        if (pelilauta % 2 == logiikka.haeVuoro() % 2) {
            asetaRuutuunKuuluvaNappulaOsutuksi(pelilauta, ruutu);
            if (!logiikka.katsoSiirtoPelilaudasta(ruutu, pelilauta)) {
                siirtoEiOsunut(pelilauta);
            } else {
                siirtoOsui(pelilauta, ruutu);
            }
            paivitaUponneetLaivat(pelilauta);
        } else {
            tekstikentat.get(pelilauta).setText("Ei ole sinun vuorosi.");
        }
    }
    
    private void asetaRuutuunKuuluvaNappulaOsutuksi(int pelilauta, Ruutu ruutu) {
        JButton ruudunNappula = pelilaudat.get(pelilauta).get(ruutu);
        ruudunNappula.setEnabled(false);
        ruudunNappula.setBackground(Color.GRAY);
    }

    private void siirtoOsui(int pelilauta, Ruutu ruutu) {
        if (!logiikka.voittaakoPelaaja(pelilauta)) {
            tekstikentat.get(pelilauta).setText("Osuma! Saat uuden vuoron.");
        } else {
            tekstikentat.get(pelilauta).setText("Osuma! Kaikki laivat upotettu.");
            tarkistaJatkuukoPeliTeeKeinoalynVuoroJosYksinpeli();
        }
        pelilaudat.get(pelilauta).get(ruutu).setBackground(Color.RED);
    }

    private void siirtoEiOsunut(int pelilauta) {
        tekstikentat.get(pelilauta).setText("Huti.");
        tarkistaJatkuukoPeliTeeKeinoalynVuoroJosYksinpeli();
    }
    
    private void tarkistaJatkuukoPeliTeeKeinoalynVuoroJosYksinpeli() {
        logiikka.vuoroPelattu();
        jatkuukoPeli();
        if (!asetukset.onkoKaksinpeli()) {
            tekoalynVuoro();
        }
    }

    public void lisaaJButton(JButton nappula, Ruutu ruutu, int pelilauta) {
        pelilaudat.get(pelilauta).put(ruutu, nappula);
    }

    public void lisaaJTextField(JTextField tekstikentta, int pelilauta) {
        tekstikentat.put(pelilauta, tekstikentta);
    }

    private void asetaRuudutToimimattomiksi(int pelilauta) {
        for (JButton nappula : pelilaudat.get(pelilauta).values()) {
            nappula.setEnabled(false);
        }
    }

    private void naytaPelaajanLaivat() {
        for (Ruutu ruutu : logiikka.haeLaivojenRuudut(2)) {
            pelilaudat.get(2).get(ruutu).setBackground(Color.CYAN);
        }
    }

    private void paivitaUponneetLaivat(int pelilauta) {
        for (Ruutu ruutu : logiikka.haeUpotettujenLaivojenRuudut(pelilauta)) {
            pelilaudat.get(pelilauta).get(ruutu).setBackground(Color.BLACK);
        }
    }

    private void tekoalynVuoro() {
        tekoAlynSiirto();
        logiikka.vuoroPelattu();
        jatkuukoPeli();
    }

    private void tekoAlynSiirto() {
        Ruutu siirto = tekoaly.teeSiirto();
        pelilaudat.get(2).get(siirto).setBackground(Color.GRAY);
        if (logiikka.katsoSiirtoPelilaudasta(siirto, 2)) {
            tekoaly.lisaaOsuma(siirto);
            pelilaudat.get(2).get(siirto).setBackground(Color.RED);
            if (logiikka.upottikoSiirtoLaivan(siirto, 2)) {
                tekoaly.laivaUpotettu();
            }
            paivitaUponneetLaivat(2);
            tekoAlynSiirto();
        }
    }

    private void jatkuukoPeli() {
        if (logiikka.voittaakoJompikumpi()) {
            if (logiikka.haeVuoro() % 2 == 1) {
                asetaRuudutToimimattomiksi(1);
                asetaRuudutToimimattomiksi(2);
                String voittaja = haeVoittaja();
                tekstikentat.get(1).setText(voittaja);
                tekstikentat.get(2).setText(voittaja);
            }
        }
    }

    private String haeVoittaja() {
        String tuloste;
        int voittaja = logiikka.haeVoittaja();
        if (voittaja == 0) {
            tuloste = "Tasapeli";
        } else if (voittaja == 1) {
            tuloste = "Pelaaja 1 on voittaja.";
        } else {
            if (!asetukset.onkoKaksinpeli()) {
                tuloste = "Tekoäly on voittaja.";
            } else {
                tuloste = "Pelaaja 2 on voittaja.";
            }
        }
        return tuloste;
    }

}
