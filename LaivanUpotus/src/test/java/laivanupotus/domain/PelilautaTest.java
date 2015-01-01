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
        Ruutu ruutu = new Ruutu(5, 6);
        Ruutu ruutu2 = new Ruutu(5, 7);
        this.laiva = new Laiva(ruutu, ruutu2);
        ArrayList<Laiva> laivat = new ArrayList<>();
        laivat.add(laiva);
        this.pelilauta = new Pelilauta(laivat);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodustaPelilaudastaLoytyyOikeallaRuudullaSiihenLiittyvaLaiva() {
        assertEquals(laiva, pelilauta.haeLaiva(new Ruutu(5, 6)));
    }
    
    @Test
    public void pelilautaPalauttaaNullJosRuutuunEiLiityLaivaa() {
        assertEquals(null, pelilauta.haeLaiva(new Ruutu(0, 0)));
    }
    
    
}
