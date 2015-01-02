package laivanupotus.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {

    private Pelilauta pelilauta;
    private Laiva laiva;

    public PelilautaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.laiva = new Laiva(new Ruutu(2, 3), new Ruutu(2, 4));
        ArrayList<Laiva> laivat = new ArrayList<>();
        laivat.add(laiva);
        this.pelilauta = new Pelilauta(laivat);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void siirronLisaamisenJalkeenListassaOnSiirto() {
        pelilauta.lisaaSiirto(new Ruutu(3, 4));
        assertEquals(new Ruutu(3, 4), pelilauta.haeSiirrot().get(0));
    }

    @Test
    public void katsoSiirtoPalauttaaRuutuunLiittyvanLaivan() {
        assertEquals(laiva, pelilauta.katsoRuutu(new Ruutu(2, 3)));
    }

    @Test
    public void luodullaPelilaudallaOnKonstruktorissaAnnettuLaiva() {
        assertEquals(laiva, pelilauta.haeLaivat().get(0));
    }

    @Test
    public void luodustaPelilaudastaLoytyyOikeallaRuudullaSiihenLiittyvaLaiva() {
        assertEquals(laiva, pelilauta.katsoRuutu(new Ruutu(2, 3)));
    }

    @Test
    public void pelilautaPalauttaaNullJosRuutuunEiLiityLaivaa() {
        assertEquals(null, pelilauta.katsoRuutu(new Ruutu(0, 0)));
    }

}
