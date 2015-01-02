package laivanupotus.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import laivanupotus.domain.Ruutu;
import laivanupotus.laivanupotus.Laivanupotus;
import laivanupotus.logiikka.Logiikka;

public class UpotusKuuntelija implements ActionListener {

    private final Ruutu ruutu;
    private final Laivanupotus laivanupotus;
    private final JButton nappula;
    private final JTextField tekstikentta;
    private final Logiikka logiikka;
    private final JPanel panel;

    public UpotusKuuntelija(Ruutu ruutu, Laivanupotus laivanupotus, JButton nappula, JTextField tekstikentta, Logiikka logiikka, JPanel panel) {
        this.ruutu = ruutu;
        this.laivanupotus = laivanupotus;
        this.nappula = nappula;
        this.tekstikentta = tekstikentta;
        this.logiikka = logiikka;
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        nappula.setEnabled(false);
        tekstikentta.setText("");
        if (logiikka.pelaaVuoroPelaaja1(ruutu)) {
            tekstikentta.setText("Osuma!");
            nappula.setBackground(Color.black);
        } else {
            panel.setEnabled(false);
        }
    }
    
}
