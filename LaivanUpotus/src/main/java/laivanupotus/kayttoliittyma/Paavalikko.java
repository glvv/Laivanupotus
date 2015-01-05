package laivanupotus.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Päävalikossa voi päättää haluaako pelata oletusasetuksilla vai vaihtaa
 * asetuksia.
 *
 */
public class Paavalikko implements Runnable {

    private JFrame frame;

    public Paavalikko() {
    }

    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(350, 350));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        JButton muutaAsetuksia = new JButton("Muuta asetuksia");
        JButton pelaaOletusAsetuksilla = new JButton("Pelaa oletusasetuksilla");
        PaavalikkoKuuntelija kuuntelija = new PaavalikkoKuuntelija(pelaaOletusAsetuksilla, muutaAsetuksia, this.getFrame());
        muutaAsetuksia.addActionListener(kuuntelija);
        pelaaOletusAsetuksilla.addActionListener(kuuntelija);
        container.add(pelaaOletusAsetuksilla, BorderLayout.CENTER);
        container.add(muutaAsetuksia, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }
}
