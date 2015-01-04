package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import laivanupotus.logiikka.Asetukset;

public class PaavalikkoKuuntelija implements ActionListener {

    private final JButton oletus;
    private final JButton muutaAsetuksia;
    private final Paavalikko valikko;

    public PaavalikkoKuuntelija(JButton oletus, JButton muutaAsetuksia, Paavalikko valikko) {
        this.oletus = oletus;
        this.muutaAsetuksia = muutaAsetuksia;
        this.valikko = valikko;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {        
        if (ae.getSource() == oletus) {
            Asetukset asetukset = new Asetukset();
            asetukset.asetaOletusLaivat();
            LaivanupotusGUI gui = new LaivanupotusGUI(asetukset);
            SwingUtilities.invokeLater(gui);
        } else if (ae.getSource() == muutaAsetuksia) {
            
        }
        valikko.getFrame().dispose(); 
    }
    
}
