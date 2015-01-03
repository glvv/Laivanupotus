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
        JTextField laudanTekstikentta = tekstikentat.get(pelilauta);
        if (pelilauta % 2 == logiikka.haeVuoro() % 2) {
            JButton ruudunNappula = pelilaudat.get(pelilauta).get(ruutu);
            ruudunNappula.setEnabled(false);
            ruudunNappula.setBackground(Color.GRAY);
            if (!logiikka.katsoSiirtoPelilaudasta(ruutu, pelilauta)) {
                laudanTekstikentta.setText("Huti.");
                logiikka.vuoroPelattu();
                if (!asetukset.onkoKaksinpeli()) {
                    tekoalynVuoro();
                }
            } else {
                laudanTekstikentta.setText("Osuma! Saat uuden vuoron.");
                pelilaudat.get(pelilauta).get(ruutu).setBackground(Color.RED);
            }
            paivitaUponneetLaivat(pelilauta);
            if (logiikka.voittaakoJompikumpi()) {
                lopetaPeli();
            }
        } else {
            laudanTekstikentta.setText("Ei ole sinun vuorosi.");
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
    
    private void lopetaPeli() {
        asetaRuudutToimimattomiksi(1);
        asetaRuudutToimimattomiksi(2);
        tekstikentat.get(1).setText("pizzaa");
        tekstikentat.get(2).setText("iiii");
    }

}
