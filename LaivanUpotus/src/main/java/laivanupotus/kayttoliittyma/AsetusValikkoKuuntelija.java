package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import laivanupotus.logiikka.Asetukset;

public class AsetusValikkoKuuntelija implements ActionListener {

    private final JSlider leveysAsetin;
    private final JRadioButton kaksinpeli;
    private final JRadioButton yksinpeli;
    private final JButton asetusNappula;
    private final Asetukset asetukset;

    public AsetusValikkoKuuntelija(JSlider leveysAsetin, JRadioButton kaksinpeli, JRadioButton yksinpeli, JButton asetusNappula, Asetukset asetukset) {
        this.leveysAsetin = leveysAsetin;
        this.kaksinpeli = kaksinpeli;
        this.yksinpeli = yksinpeli;
        this.asetusNappula = asetusNappula;
        this.asetukset = asetukset;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        asetukset.asetaKaksinpeli(kaksinpeli.isSelected());
        asetukset.asetaLeveys(leveysAsetin.getValue());
        asetukset.asetaPituus(leveysAsetin.getValue());
        asetusNappula.setEnabled(false);
        leveysAsetin.setEnabled(false);
        kaksinpeli.setEnabled(false);
        yksinpeli.setEnabled(false);
    }
}
