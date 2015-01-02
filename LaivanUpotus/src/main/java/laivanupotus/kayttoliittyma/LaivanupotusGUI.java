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
import laivanupotus.laivanupotus.Laivanupotus;
import laivanupotus.logiikka.Asetukset;
import laivanupotus.logiikka.Logiikka;

public class LaivanupotusGUI implements Runnable {

    private JFrame frame;
    private final Asetukset asetukset;
    private final Laivanupotus laivanupotus;
    private final Logiikka logiikka;
    private JPanel j1;

    public LaivanupotusGUI(Asetukset asetukset, Laivanupotus peli, Logiikka logiikka) {
        this.asetukset = asetukset;
        this.laivanupotus = peli;
        this.logiikka = logiikka;
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
    }

    private JPanel luoLauta() {
        JPanel lautaJaTekstiKentta = new JPanel(new BorderLayout());
        JTextField tekstikentta = new JTextField("");
        tekstikentta.setEditable(false);
        lautaJaTekstiKentta.add(tekstikentta, BorderLayout.NORTH);
        int leveys = asetukset.haePelilautaLeveys();
        int pituus = asetukset.haePelilautaPituus();
        GridLayout grid = new GridLayout(leveys, pituus);
        JPanel lauta1 = new JPanel(grid);
        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < pituus; y++) {
                JButton nappula = new JButton();
                UpotusKuuntelija kuuntelija = new UpotusKuuntelija(new Ruutu(x, y), laivanupotus, nappula, tekstikentta, logiikka, lauta1);
                nappula.addActionListener(kuuntelija);
                lauta1.add(nappula);
            }
        }
        lautaJaTekstiKentta.add(lauta1, BorderLayout.CENTER);
        j1 = lauta1;
        return lautaJaTekstiKentta;
    }

    public JFrame getFrame() {
        return frame;
    }
}
