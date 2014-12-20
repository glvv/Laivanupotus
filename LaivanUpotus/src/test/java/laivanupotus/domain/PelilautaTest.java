package laivanupotus.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {

    private Pelilauta pelilauta;

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
        this.pelilauta = new Pelilauta(12, 16);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luotuPelilautaSisaltaaRuutujaOikeanMaaran() {
        int ruutujenSumma = 0;
        Ruutu[][] ruudut = pelilauta.haeRuudukko();
        for (Ruutu[] ruudukko : ruudut) {
            for (Ruutu ruutu : ruudukko) {
                ruutujenSumma++;
            }
        }
        assertEquals(12 * 16, ruutujenSumma);
    }
    
    @Test
    public void luotuPelilautaAntaaOikeanRuudun() {
        assertEquals(new Ruutu(3, 4), pelilauta.haeRuutu(3, 4));
    }
    
    
}
