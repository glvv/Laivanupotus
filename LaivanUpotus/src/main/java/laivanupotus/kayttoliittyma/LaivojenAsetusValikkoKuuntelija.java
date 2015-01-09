package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import laivanupotus.domain.Asetukset;

/**
 * Luokka kuuntelee aliotaPeli -nappulaa. Kun nappulaa painetaan, luokka hakee
 * laivojen määrät JSlider-olioista, lisää laivat arvojen mukaan ja aloittaa
 * pelin.
 */
public class LaivojenAsetusValikkoKuuntelija implements ActionListener {

    private final JSlider[] laivaSlider;
    private final JFrame laivaValikko;
    private final Asetukset asetukset;

    /**
     * Konstruktorissa asetataan JSlider-oliot oliomuuttujiin, ja annetaan
     * muokattava Asetukset-olio.
     *
     * @param laivojenMaarat
     * @param laivaValikko
     * @param asetukset
     */
    public LaivojenAsetusValikkoKuuntelija(JSlider[] laivojenMaarat, JFrame laivaValikko, Asetukset asetukset) {
        this.laivaSlider = laivojenMaarat;
        this.laivaValikko = laivaValikko;
        this.asetukset = asetukset;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        asetukset.lisaaLaiva(1, laivaSlider[0].getValue());
        asetukset.lisaaLaiva(2, laivaSlider[1].getValue());
        asetukset.lisaaLaiva(3, laivaSlider[2].getValue());
        asetukset.lisaaLaiva(4, laivaSlider[3].getValue());
        asetukset.lisaaLaiva(5, laivaSlider[4].getValue());
        laivaValikko.dispose();
        LaivanupotusGUI gui = new LaivanupotusGUI(asetukset);
        SwingUtilities.invokeLater(gui);
    }

}
