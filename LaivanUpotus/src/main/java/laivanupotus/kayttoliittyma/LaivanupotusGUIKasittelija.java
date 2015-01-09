package laivanupotus.kayttoliittyma;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import laivanupotus.domain.Ruutu;
import laivanupotus.domain.Asetukset;
import laivanupotus.logiikka.Logiikka;
import laivanupotus.logiikka.TekoalyPelaaja;

/**
 * LaivanupotusGUIKasittelija muokkaa pelilautojen grafiikkakomponenttien tilaa.
 */
public class LaivanupotusGUIKasittelija {

    private final Logiikka logiikka;
    private final HashMap<Integer, HashMap<Ruutu, JButton>> pelilaudat;
    private final HashMap<Integer, JTextField> tekstikentat;
    private final Asetukset asetukset;
    private final LaivanupotusGUI gui;
    private TekoalyPelaaja tekoaly;

    public LaivanupotusGUIKasittelija(Asetukset asetukset, LaivanupotusGUI gui) {
        this.asetukset = asetukset;
        this.gui = gui;
        this.logiikka = new Logiikka(asetukset);
        this.pelilaudat = new HashMap<>();
        pelilaudat.put(1, new HashMap<Ruutu, JButton>());
        pelilaudat.put(2, new HashMap<Ruutu, JButton>());
        this.tekstikentat = new HashMap<>();
        tekoaly = null;
    }

    /**
     * Jos yksinpeli on päällä, metodi luo TekoalyPelaaja - olion, sekä asettaa toisen
     * pelilaudan reagoimattomaksi ja näyttää sillä olevat laivat. Metodia tulee
     * käyttää kun komponentit ovat luotu. Muuten Tekoäly ei toimi.
     */
    public void tarkistaOnkoYksinPeliJaLuoTekoalyJosOn() {
        if (!asetukset.onkoKaksinpeli()) {
            this.tekoaly = new TekoalyPelaaja(asetukset, logiikka);
            asetaRuudutToimimattomiksi(2);
            naytaPelaajanLaivat();
        }
    }

    /**
     * Metodi välittää siirron logiikalle ja muokkaa pelilautaa tuloksen mukaan.
     * Metodi tarkistaa onko pelaajan vuoro. Jos ei ole pelaajan vuoro, metodi
     * ilmoittaa siitä tekstikenttään, eikä tee muuta. Yksinpelissä metodikutsu
     * suorittaa siirron jälkeen tekoälyn vuoron.
     *
     * @param ruutu
     * @param pelilauta
     */
    public void kokeileSiirto(Ruutu ruutu, int pelilauta) {
        if (logiikka.oikeaVuoro(pelilauta)) {
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

    /**
     * Metodilla lisätään JButton HashMap-olioon.
     *
     * @param nappula Lisättävä JButton.
     * @param ruutu HashMap-olion avaimena toimiva Ruutu-olio.
     * @param pelilauta Kumpaan pelilautaan JButton kuuluu.
     */
    public void lisaaJButton(JButton nappula, Ruutu ruutu, int pelilauta) {
        pelilaudat.get(pelilauta).put(ruutu, nappula);
    }

    /**
     * Metodilla lisätään JTextField HashMap-olioon.
     *
     * @param tekstikentta Lisättävä JTextField-olio.
     * @param pelilauta Avaimena toimiva pelilauta. Kumpaan pelilautaan
     * JTextField kuuluu.
     */
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
        tekoaly.teeSiirto();
        paivitaTekoalynPelilauta();
        tekoaly.vuoroPelattu();
        paivitaUponneetLaivat(2);
        logiikka.vuoroPelattu();
        jatkuukoPeli();
    }

    private void paivitaTekoalynPelilauta() {
        for (Ruutu ruutu : tekoaly.haeTehdytSiirrot()) {
            pelilaudat.get(2).get(ruutu).setBackground(Color.GRAY);
        }
        for (Ruutu ruutu : tekoaly.haeOsumat()) {
            pelilaudat.get(2).get(ruutu).setBackground(Color.RED);
        }
    }

    private void jatkuukoPeli() {
        if (logiikka.voittaakoJompikumpi()) {
            if (logiikka.tasoittavaVuoroPelattu()) {
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
