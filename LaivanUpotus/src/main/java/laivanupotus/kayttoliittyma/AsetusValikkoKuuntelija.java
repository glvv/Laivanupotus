package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import laivanupotus.domain.Asetukset;

/**
 * Luokka asettaa asetukset olioon, pelilaudan leveyden, sekä pelimoodin.
 * Tämän jälkeen luokka sulkee asetusvalikon ja avaa LaivojenAsetusValikko käyttöliittymän.
 * @author raot
 */
public class AsetusValikkoKuuntelija implements ActionListener {

    private final JSlider leveysAsetin;
    private final JRadioButton kaksinpeli;
    private final Asetukset asetukset;
    private final JFrame valikko;

    public AsetusValikkoKuuntelija(JSlider leveysAsetin, JRadioButton kaksinpeli, Asetukset asetukset, JFrame valikko) {
        this.leveysAsetin = leveysAsetin;
        this.kaksinpeli = kaksinpeli;
        this.asetukset = asetukset;
        this.valikko = valikko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        asetukset.asetaKaksinpeli(kaksinpeli.isSelected());
        asetukset.asetaLeveys(leveysAsetin.getValue());
        asetukset.asetaPituus(leveysAsetin.getValue());
        valikko.dispose();
        LaivojenAsetusValikko laivojenAsetus = new LaivojenAsetusValikko(asetukset);
        SwingUtilities.invokeLater(laivojenAsetus);
    }
}
