package laivanupotus.laivanupotus;

import javax.swing.SwingUtilities;
import laivanupotus.kayttoliittyma.LaivanupotusGUI;
import laivanupotus.kayttoliittyma.Paavalikko;
import laivanupotus.logiikka.Asetukset;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Paavalikko());
        Asetukset asetukset = new Asetukset();
        asetukset.asetaOletusLaivat();
        asetukset.asetaLeveys(10);
        asetukset.asetaPituus(10);
        asetukset.asetaKaksinpeli(false);
        LaivanupotusGUI gui = new LaivanupotusGUI(asetukset);
        SwingUtilities.invokeLater(gui);
    }
    
}
