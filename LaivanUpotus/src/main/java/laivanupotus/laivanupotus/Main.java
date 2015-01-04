package laivanupotus.laivanupotus;

import javax.swing.SwingUtilities;
import laivanupotus.kayttoliittyma.AsetusValikko;
import laivanupotus.kayttoliittyma.AsetusValikkoLaivat;
import laivanupotus.kayttoliittyma.Paavalikko;
import laivanupotus.logiikka.Asetukset;

public class Main {

    public static void main(String[] args) {
        Asetukset asetukset = new Asetukset();
        asetukset.asetaLeveys(30);
        asetukset.asetaPituus(30);
        SwingUtilities.invokeLater(new AsetusValikkoLaivat(asetukset));
    }
    
}
