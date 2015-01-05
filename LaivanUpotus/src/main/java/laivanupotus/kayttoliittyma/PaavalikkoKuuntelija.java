package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import laivanupotus.domain.Asetukset;

public class PaavalikkoKuuntelija implements ActionListener {

    private final JButton oletus;
    private final JButton muutaAsetuksia;
    private final JFrame valikko;

    public PaavalikkoKuuntelija(JButton oletus, JButton muutaAsetuksia, JFrame paavalikko) {
        this.oletus = oletus;
        this.muutaAsetuksia = muutaAsetuksia;
        this.valikko = paavalikko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        valikko.dispose();         
        if (ae.getSource() == oletus) {
            Asetukset asetukset = new Asetukset();
            asetukset.asetaOletusLaivat();
            LaivanupotusGUI gui = new LaivanupotusGUI(asetukset);
            SwingUtilities.invokeLater(gui);
        } else if (ae.getSource() == muutaAsetuksia) {
            AsetusValikko asetukset = new AsetusValikko();
            SwingUtilities.invokeLater(asetukset);
        }
    }
    
}
