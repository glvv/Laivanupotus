package laivanupotus.logiikka;

import java.util.ArrayList;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogiikkaTest {

    private Logiikka logiikka;

    public LogiikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Asetukset asetukset = new Asetukset();
        asetukset.lisaaLaiva(1, 1);
        this.logiikka = new Logiikka(asetukset);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodunLogiikanVuoroOnAlussa1() {
        assertEquals(1, logiikka.haeVuoro());
    }

    @Test
    public void katsoSiirtoPalauttaaTrueKunSiirtoOsuuPelilauta1() {
        Ruutu siirto = haeOsuvaRuutu(1);
        assertEquals(true, logiikka.katsoSiirtoPelilaudasta(siirto, 1));
    }

    @Test
    public void katsoSiirtoPalauttaaTrueKunSiirtoOsuuPelilauta2() {
        Ruutu siirto = haeOsuvaRuutu(2);
        assertEquals(true, logiikka.katsoSiirtoPelilaudasta(siirto, 2));
    }

    private Ruutu haeOsuvaRuutu(int pelilauta) {
        ArrayList<Laiva> laivat = logiikka.haePelilauta(pelilauta).haeLaivat();
        Laiva laiva = laivat.get(0);
        return laiva.haeRuudut()[0];
    }

    private Ruutu haeRuutuJokaEiOsu(int pelilauta) {
        Ruutu ruutu = haeOsuvaRuutu(pelilauta);
        if (ruutu.haeX() < 9) {
            ruutu = new Ruutu(ruutu.haeX() + 1, ruutu.haeY());
        } else {
            ruutu = new Ruutu(ruutu.haeX() - 1, ruutu.haeY());
        }
        return ruutu;
    }

    @Test
    public void kun1VuoroOnPelattuVuoroOn2() {
        logiikka.vuoroPelattu();
        assertEquals(2, logiikka.haeVuoro());
    }

    @Test
    public void katsoSiirtoPalauttaaFalseKunSiirtoEiOsu() {
        assertEquals(false, logiikka.katsoSiirtoPelilaudasta(haeRuutuJokaEiOsu(2), 2));
    }

    @Test
    public void katsoSiirtoLisaaOsumanLaivaanJosSiirtoOsuu() {
        Ruutu osuva = haeOsuvaRuutu(1);
        logiikka.katsoSiirtoPelilaudasta(osuva, 1);
        assertEquals(true, logiikka.haeUpotettujenLaivojenRuudut(1).get(0).equals(osuva));
    }

    @Test
    public void tasoittavaVuoroPalauttaaFalseJosVuoroOnParillinen() {
        logiikka.vuoroPelattu();
        assertEquals(false, logiikka.tasoittavaVuoroPelattu());
    }

    @Test
    public void tasoittavaVuoroPalauttaaTrueJosVuoroOnPariton() {
        assertEquals(true, logiikka.tasoittavaVuoroPelattu());
    }

}
