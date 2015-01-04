package laivanupotus.kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.domain.Ruutu;

public class LaivanupotusGUIKuuntelija implements ActionListener {

    private final Ruutu ruutu;
    private final int pelilauta;
    private final LaivanupotusGUILogiikka logiikka;
    
    public LaivanupotusGUIKuuntelija(Ruutu ruutu, int pelilauta, LaivanupotusGUILogiikka logiikka) {
        this.ruutu = ruutu;
        this.pelilauta = pelilauta;
        this.logiikka = logiikka;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        logiikka.kokeileSiirto(ruutu, pelilauta);
    }

}
