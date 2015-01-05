package laivanupotus.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Hashtable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import laivanupotus.logiikka.Asetukset;

public class LaivojenAsetusValikko implements Runnable {

    private JFrame frame;
    private final Asetukset asetukset;

    public LaivojenAsetusValikko(Asetukset asetukset) {
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
        LaivojenAsetusValikkoKuuntelija kuuntelija = new LaivojenAsetusValikkoKuuntelija(laivat, this.getFrame(), asetukset);
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
        laivat[0] = luoJSlider(1, 2 * laivojenKerroin, "");
        laivat[1] = luoJSlider(0, 2 * laivojenKerroin, "");
        laivat[2] = luoJSlider(0, 1 * laivojenKerroin, "");
        laivat[3] = luoJSlider(0, 1 * laivojenKerroin, "");
        laivat[4] = luoJSlider(0, 1 * laivojenKerroin, "");
        return laivat;
    }
    
    private HashMap<Integer, String> tekstit() {
        HashMap<Integer, String> tekstit = new HashMap<>();
        tekstit.put(0, "Sukellusveneitä (1 ruutu)");
        tekstit.put(1, "Hävittäjiä (2 ruutua)");
        tekstit.put(2, "Risteilijöitä (3 ruutua)");
        tekstit.put(3, "Taistelulaivoja (4 ruutua)");
        tekstit.put(4, "Lentotukialuksia (5 ruutua)");
        return tekstit;
    }
    
    private JPanel lisaaLaivat(JSlider[] laivat, JPanel panel) {
        HashMap<Integer, String> tekstit = tekstit();
        for (int i = 0; i < laivat.length; i++) {
            panel.add(new JLabel(tekstit.get(i)));
            panel.add(laivat[i]);
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
