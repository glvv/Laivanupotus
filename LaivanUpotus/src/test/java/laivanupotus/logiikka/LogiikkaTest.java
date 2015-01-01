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
        asetukset.asetaOletusLaivat();
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
    public void pelaaVuoroPelaaja1PalauttaaTrueKunSiirtoOsuu() {
        ArrayList<Laiva> laivat = logiikka.haePelaaja2().haeLaivat();
        Laiva laiva = laivat.get(0);
        Ruutu siirto = laiva.haeRuudut()[0];
        assertEquals(true, logiikka.pelaaVuoroPelaaja1(siirto));
    }
    
    @Test
    public void pelaaVuoroPelaaja2PalauttaaTrueKunSiirtoOsuu() {
        ArrayList<Laiva> laivat = logiikka.haePelaaja1().haeLaivat();
        Laiva laiva = laivat.get(0);
        Ruutu siirto = laiva.haeRuudut()[0];
        assertEquals(true, logiikka.pelaaVuoroPelaaja2(siirto));
    }
    
    @Test
    public void kun1VuoroOnPelattuVuoroOn2() {
        logiikka.vuoroPelattu();
        assertEquals(2, logiikka.haeVuoro());
    }
    

}
