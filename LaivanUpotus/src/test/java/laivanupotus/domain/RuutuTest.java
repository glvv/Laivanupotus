package laivanupotus.domain;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {

    private Ruutu ruutu;

    public RuutuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.ruutu = new Ruutu(3, 4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodunRuudunXKoordinaattiOnOikein() {
        assertEquals(3, ruutu.haeX());
    }

    @Test
    public void luodunRuudunYKoordinaattiOnOikein() {
        assertEquals(4, ruutu.haeY());
    }

    @Test
    public void luotuunRuutuunEiKuuluLaivaa() {
        assertEquals(null, ruutu.haeLaiva());
    }

    @Test
    public void kaksiRuutuaJoillaSamatKoordinaatitOvatSamat() {
        Ruutu ruutu2 = new Ruutu(3, 4);
        assertEquals(true, ruutu.equals(ruutu2));
    }

    @Test
    public void kaksiRuutuaJoillaEriKoordinaatitEivatOleSamat() {
        Ruutu ruutu2 = new Ruutu(2, 43);
        assertEquals(false, ruutu.equals(ruutu2));
    }

    @Test
    public void luotuunRuutuunEiOleOsuttu() {
        assertEquals(false, ruutu.haeOsuttu());
    }

    @Test
    public void osumanJalkeenRuutuunOnOsuttu() {
        ruutu.onAmmuttu();
        assertEquals(true, ruutu.haeOsuttu());
    }
    
    @Test
    public void ruudutJarjestyvatOikein() {
        ArrayList<Ruutu> ruudut = new ArrayList<>();        
        ruudut.add(new Ruutu(3, 7));
        ruudut.add(new Ruutu(3, 6));
        ruudut.add(ruutu);
        ruudut.add(new Ruutu(3, 5));
        Collections.sort(ruudut);
        assertEquals(ruutu, ruudut.get(0));
    }

}
