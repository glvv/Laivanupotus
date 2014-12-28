package laivanupotus.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SyotteenKasittelijaTest {

    private SyotteenKasittelija sk;

    public SyotteenKasittelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.sk = new SyotteenKasittelija();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tarkistaKokonaislukuPalauttaaFalseKunSyotteessaOnMuutaKuinKokonaislukuja1() {
        assertEquals(false, sk.tarkistaKokonaisluku("pizzacake"));
    }

    @Test
    public void tarkistaKokonaislukuPalauttaaFalseKunSyotteessaOnMuutaKuinKokonaislukuja2() {
        assertEquals(false, sk.tarkistaKokonaisluku("0.34444"));
    }

    @Test
    public void tarkistaKokonaislukuPalauttaaFalseKunSyotteessaOnMuutaKuinKokonaislukuja3() {
        assertEquals(false, sk.tarkistaKokonaisluku("1.000000"));
    }

    @Test
    public void tarkistaKokonaislukuPalauttaaFalseKunSyoteOnTyhja() {
        assertEquals(false, sk.tarkistaKokonaisluku(""));
    }

    @Test
    public void tarkistaKokonaislukuPalauttaaTrueKunSyoteOnKokonaisluku() {
        assertEquals(true, sk.tarkistaKokonaisluku("8"));
    }

    @Test
    public void tarkistaPelilaudanSivuPalauttaaFalseJosSivuOnAlle10() {
        assertEquals(false, sk.tarkistaPelilaudanSivu("9"));
    }

    @Test
    public void tarkistaPelilaudanSivuPalauttaaFalseJosSivuOnYli100() {
        assertEquals(false, sk.tarkistaPelilaudanSivu("999"));
    }

    @Test
    public void tarkistaPelilaudanSivuPalauttaaTrueJosSyoteOn10() {
        assertEquals(true, sk.tarkistaPelilaudanSivu("10"));
    }

    @Test
    public void tarkistaPelilaudanSivuPalauttaaTrueJosSyoteOn100() {
        assertEquals(true, sk.tarkistaPelilaudanSivu("100"));
    }
    
    @Test
    public void tarkistaSiirtoPalauttaaFalseJosSyoteOnPienempiKuin1() {
        assertEquals(false, sk.tarkistaSiirto("0", true));
    }
    
    @Test
    public void tarkistaSiirtoPalauttaaFalseJosXOnSuurempiKuinLeveys() {
        assertEquals(false, sk.tarkistaSiirto("15", true));
    }
    
    @Test
    public void tarkistaSiirtoPalauttaaFalseJosYOnSuurempiKuinPituus() {
        assertEquals(false, sk.tarkistaSiirto("1777", false));
    }
    
    @Test
    public void tarkistaSiirtoPalauttaaTrueAariarvolla1() {
        assertEquals(true, sk.tarkistaSiirto("1", true));
    }
    
    @Test
    public void tarkistaSiirtoPalauttaaTrueAariarvolla2() {
        assertEquals(true, sk.tarkistaSiirto("10", false));
    }
    
    @Test
    public void tarkistaValintaPalauttaaFalseJosLukuEiOleValintojenJoukossa() {
        assertEquals(false, sk.tarkistaValinta("444", 2));
    }
    
    @Test
    public void tarkistaValintaPalauttaaTrueKunValintaOn1JaValintojaOn2() {
        assertEquals(true, sk.tarkistaValinta("1", 2));
    }
    
    @Test
    public void tarkistaValintaPalauttaaTrueKunValintaOn2JaValintoja2() {
        assertEquals(true, sk.tarkistaValinta("2", 2));
    }

}
