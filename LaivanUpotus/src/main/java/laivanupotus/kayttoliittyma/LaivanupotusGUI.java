/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import laivanupotus.domain.Ruutu;
import laivanupotus.domain.Asetukset;

/**
 * LaivanupotusGUI sisältää 2 pelilautaa, joissa on nappulat siirron antamista varten.
 * 
 */
public class LaivanupotusGUI implements Runnable {

    private JFrame frame;
    private final Asetukset asetukset;
    private final LaivanupotusGUIKasittelija logiikka;
    private int pelilautaIndeksi;

    public LaivanupotusGUI(Asetukset asetukset) {
        this.asetukset = asetukset;
        this.logiikka = new LaivanupotusGUIKasittelija(asetukset, this);
        this.pelilautaIndeksi = 1;
    }

    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(350, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(luoLauta());
        container.add(luoLauta());
        logiikka.tarkistaOnkoYksinPeliJaLuoTekoalyJosOn();
    }

    private JPanel luoLauta() {
        JPanel lautaJaTekstiKentta = new JPanel(new BorderLayout());
        JTextField tekstikentta = new JTextField("");
        logiikka.lisaaJTextField(tekstikentta, pelilautaIndeksi);
        tekstikentta.setEditable(false);
        lautaJaTekstiKentta.add(tekstikentta, BorderLayout.NORTH);
        JPanel nappulat = luoNappulat();
        lautaJaTekstiKentta.add(nappulat, BorderLayout.CENTER);
        pelilautaIndeksi++;
        return lautaJaTekstiKentta;
    }
    
    private JPanel luoNappulat() {
        int leveys = asetukset.haePelilautaLeveys();
        int pituus = asetukset.haePelilautaPituus();
        GridLayout grid = new GridLayout(leveys, pituus);
        JPanel lauta1 = new JPanel(grid);
        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < pituus; y++) {
                JButton nappula = new JButton();
                logiikka.lisaaJButton(nappula, new Ruutu(x, y), pelilautaIndeksi);
                LaivanupotusGUIKuuntelija kuuntelija = new LaivanupotusGUIKuuntelija(new Ruutu(x, y), pelilautaIndeksi, logiikka);
                nappula.addActionListener(kuuntelija);
                lauta1.add(nappula);
            }
        }
        return lauta1;
    }
    
        
    public JFrame getFrame() {
        return frame;
    }
}
