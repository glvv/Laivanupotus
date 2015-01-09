package laivanupotus.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import laivanupotus.domain.Ruutu;

/**
 * Luokka välittää tehdun siirron käsittelijälle. Jokaisella pelilaudan ruudulla
 * on oma LaivanUpotusGUIKuuntelija, oliomuuttujina on ruutu ja pelilauta,
 * joista muodostetaan tehty siirto.
 */
public class LaivanupotusGUIKuuntelija implements ActionListener {

    private final Ruutu ruutu;
    private final int pelilauta;
    private final LaivanupotusGUIKasittelija logiikka;

    /**
     * Konstruktorissa asetetaan ruutu ja pelilauta.
     *
     * @param ruutu Mikä ruutu liittyy pelilaudan JButton-olioon.
     * @param pelilauta Kummassa pelilaudasa JButton on.
     * @param kasittelija Kasittelijaluokka, joka vie siirrot logiikalle.
     */
    public LaivanupotusGUIKuuntelija(Ruutu ruutu, int pelilauta, LaivanupotusGUIKasittelija kasittelija) {
        this.ruutu = ruutu;
        this.pelilauta = pelilauta;
        this.logiikka = kasittelija;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        logiikka.kokeileSiirto(ruutu, pelilauta);
    }

}
