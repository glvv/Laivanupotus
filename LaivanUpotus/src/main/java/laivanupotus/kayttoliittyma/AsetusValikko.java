package laivanupotus.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Hashtable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import laivanupotus.logiikka.Asetukset;

public class AsetusValikko implements Runnable {

    private JFrame frame;
    private Asetukset asetukset;

    public AsetusValikko() {
        this.asetukset = new Asetukset();
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
        JPanel asetukset1 = luoAsetukset1();
        container.add(asetukset1);
    }
    
    private JSlider luoJSlider(int vahintaan, int enintaan, String kuvaavaNimi) {
        JSlider slider = new JSlider(vahintaan, enintaan);
        Hashtable<Integer, JLabel> minimiJaMaksimi = new Hashtable();        
        minimiJaMaksimi.put(vahintaan, new JLabel(vahintaan + "") );
        minimiJaMaksimi.put(enintaan, new JLabel(enintaan + "") );
        minimiJaMaksimi.put((enintaan - vahintaan) / 2 + vahintaan, new JLabel(kuvaavaNimi));
        slider.setLabelTable(minimiJaMaksimi);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        return slider;
    }
    
    private JPanel luoAsetukset1() {
        JPanel asetukset1 = new JPanel();        
        JRadioButton[] nappulat = lisaaMonivalintaYksinPeliVaiMoninPeli();
        JSlider leveysSlider = luoJSlider(10, 30, "Pelilaudan leveys");
        JButton asetaNappula = lisaaAsetustenAsetusNappula();
        AsetusValikkoKuuntelija kuuntelija = new AsetusValikkoKuuntelija(leveysSlider, nappulat[0], nappulat[1], asetaNappula, asetukset);
        asetaNappula.addActionListener(kuuntelija);
        JPanel monivalinta = new JPanel();
        monivalinta.add(nappulat[0]);
        monivalinta.add(nappulat[1]);
        asetukset1.add(monivalinta, BorderLayout.NORTH);
        asetukset1.add(leveysSlider, BorderLayout.CENTER);
        asetukset1.add(asetaNappula, BorderLayout.SOUTH);
        return asetukset1;
    }
    
    private JRadioButton[] lisaaMonivalintaYksinPeliVaiMoninPeli() {
        JRadioButton[] nappulat = new JRadioButton[2];
        nappulat[0] = new JRadioButton("Kaksinpeli");
        nappulat[1] = new JRadioButton("Yksinpeli");
        nappulat[1].doClick();
        ButtonGroup pelivalinta = new ButtonGroup();
        pelivalinta.add(nappulat[0]);
        pelivalinta.add(nappulat[1]);
        return nappulat;
    }
    
    private JButton lisaaAsetustenAsetusNappula() {
        JButton aseta = new JButton("Aseta");
        return aseta;
    }

    public JFrame getFrame() {
        return frame;
    }
}