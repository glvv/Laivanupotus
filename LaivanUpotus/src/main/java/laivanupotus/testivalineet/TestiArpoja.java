package laivanupotus.testivalineet;

import java.util.ArrayList;
import java.util.Random;

/**
 * TestiArpoja on Random-luokan perivä olio, jonka palauttamat arvot voi
 * asettaa.
 */
public class TestiArpoja extends Random {

    private final ArrayList<Integer> palautusArvot;
    private int moneskoSyote;

    /**
     * Konstruktorissa alustetaan oliomuuttujat.
     */
    public TestiArpoja() {
        super();
        this.moneskoSyote = 0;
        this.palautusArvot = new ArrayList<>();
    }

    /**
     * Metodi hakee palautusarvoja listalta, jolle on lisätty arvoja
     * lisaaPalautusArvo - metodilla. Palautusarvot annetaan siinä
     * järjestyksessä, missä ne on lisätty.
     *
     * @param raja Parametri ei tee mitään.
     * @return Haluttu palautusarvo.
     */
    @Override
    public int nextInt(int raja) {
        moneskoSyote++;
        return palautusArvot.get(moneskoSyote - 1);
    }

    /**
     * Metodilla lisätään haluttu palautusarvo.
     *
     * @param luku
     */
    public void lisaaPalautusArvo(int luku) {
        palautusArvot.add(luku);
    }

}
