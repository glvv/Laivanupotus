package laivanupotus.laivanupotus;

import javax.swing.SwingUtilities;
import laivanupotus.kayttoliittyma.LaivanupotusGUI;
import laivanupotus.logiikka.Asetukset;
import laivanupotus.logiikka.Logiikka;

public class Main {

    public static void main(String[] args) {
        Asetukset asetukset = new Asetukset();
        asetukset.asetaOletusLaivat();
        asetukset.asetaLeveys(10);
        asetukset.asetaPituus(10);
        Laivanupotus laivanupotus = new Laivanupotus();
        Logiikka logiikka = new Logiikka(asetukset);
        LaivanupotusGUI gui = new LaivanupotusGUI(asetukset, laivanupotus, logiikka);
        SwingUtilities.invokeLater(gui);
    }


}
