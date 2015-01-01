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
    public void ruudutJarjestyvatOikeinKunXKoordinaatitOvatSamat() {
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        ruudut.add(new Ruutu(3, 7));
        ruudut.add(new Ruutu(3, 6));
        ruudut.add(ruutu);
        ruudut.add(new Ruutu(3, 5));
        Collections.sort(ruudut);
        assertEquals(ruutu, ruudut.get(0));
    }

    @Test
    public void kahdellaRuudullaJoillaOnSamatXYOnSamaHashCode() {
        Ruutu ruutu2 = new Ruutu(3, 4);
        assertEquals(ruutu.hashCode(), ruutu2.hashCode());
    }

    @Test
    public void ruudutJarjestyvatOikeinKunYKoordinaatitOvatSamat() {
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        ruudut.add(new Ruutu(4, 4));
        ruudut.add(ruutu);
        ruudut.add(new Ruutu(5, 4));
        ruudut.add(new Ruutu(6, 4));
        Collections.sort(ruudut);
        assertEquals(ruutu, ruudut.get(0));
    }
    
    @Test
    public void jarjestettaessaRuuduilleEiTehdaMitaanJosNiidenYtaiXKoordinaatitEivatOleSamat() {
        ArrayList<Ruutu> ruudut = new ArrayList<>();
        ruudut.add(new Ruutu(8, 9));
        ruudut.add(new Ruutu(1, 6));
        ruudut.add(ruutu);
        Collections.sort(ruudut);
        assertEquals(ruutu, ruudut.get(2));
    }
    
    @Test
    public void kaksiRuutuaEivatOleSamatJosToinenOnNull() {
        Ruutu ruutu2 = null;
        assertEquals(false, ruutu.equals(ruutu2));
    }
    
    @Test
    public void ruutuEiOleSamaKuinEriOlio() {
        String nimi = "jjj";
        assertEquals(false, ruutu.equals(nimi));
    }
    

}
