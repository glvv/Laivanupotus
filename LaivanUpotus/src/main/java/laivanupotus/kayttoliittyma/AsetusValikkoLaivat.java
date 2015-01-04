package laivanupotus.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Hashtable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import laivanupotus.logiikka.Asetukset;

public class AsetusValikkoLaivat implements Runnable {

    private JFrame frame;
    private Asetukset asetukset;

    public AsetusValikkoLaivat(Asetukset asetukset) {
        this.asetukset = asetukset;
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
        container.add(luoLaivojenAsetus(), BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }

    private JPanel luoLaivojenAsetus() {
        JSlider[] laivat = luoLaivatSlider();
        JButton aloitaPeli = new JButton("Aloita peli");
        AsetusValikkoLaivatKuuntelija kuuntelija = new AsetusValikkoLaivatKuuntelija(laivat, aloitaPeli, this.getFrame());
        aloitaPeli.addActionListener(kuuntelija);
        JPanel laivojenAsetus = new JPanel();
        laivojenAsetus.setLayout(new BoxLayout(laivojenAsetus, BoxLayout.Y_AXIS));
        lisaaLaivat(laivat, laivojenAsetus);
        laivojenAsetus.add(aloitaPeli);
        return laivojenAsetus;
    }
    
    private JSlider[] luoLaivatSlider(){
        int laivojenKerroin = asetukset.haeLaivojenMaaranKerroin();
        JSlider[] laivat = new JSlider[5];
        laivat[0] = luoJSlider(1, 4 * laivojenKerroin, "Sukellusveneet(1 ruutu)");
        laivat[1] = luoJSlider(0, 3 * laivojenKerroin, "Havittajat(2 ruutua)");
        laivat[2] = luoJSlider(0, 3 * laivojenKerroin, "Risteilijat(3 ruutua)");
        laivat[3] = luoJSlider(0, 1 * laivojenKerroin, "Taistelulaivoja(4 ruutua");
        laivat[4] = luoJSlider(0, 1 * laivojenKerroin, "Lentotukialuksia(5 ruutua)");
        return laivat;
    }
    
    private JPanel lisaaLaivat(JSlider[] laivat, JPanel panel) {
        for (JSlider slider : laivat) {
            panel.add(slider);
        }
        return panel;
    }

    private JSlider luoJSlider(int vahintaan, int enintaan, String kuvaavaNimi) {
        JSlider slider = new JSlider(vahintaan, enintaan);
        Hashtable<Integer, JLabel> minimiJaMaksimi = new Hashtable();
        minimiJaMaksimi.put(vahintaan, new JLabel(vahintaan + ""));
        minimiJaMaksimi.put(enintaan, new JLabel(enintaan + ""));
        minimiJaMaksimi.put((enintaan - vahintaan) / 2 + vahintaan, new JLabel(kuvaavaNimi));
        slider.setLabelTable(minimiJaMaksimi);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        return slider;
    }
}
