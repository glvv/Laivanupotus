package laivanupotus.logiikka;

import laivanupotus.domain.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TekoalyTest {

    private Tekoaly tekoaly;
    private Ruutu ruutu;
    private Ruutu arvaus;

    public TekoalyTest() {
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
        this.tekoaly = new Tekoaly(10, 10);
        this.arvaus = tekoaly.arvaaSatunnainen();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodunTekoalynOsumatOnTyhja() {
        assertEquals(true, tekoaly.haeOsumat().isEmpty());
    }

    @Test
    public void luodunTekoalynArvauksetOnTyhja() {
        assertEquals(true, tekoaly.haeArvatut().isEmpty());
    }

    @Test
    public void arvaaSatunnainenPalauttaaRuudunJonkaXKoordinaattiOnLaudalla() {
        assertEquals(true, (arvaus.haeX() >= 0 && arvaus.haeX() < 10));
    }

    @Test
    public void arvaaSatunnainenPalauttaaRuudunJonkaYKoordinaattiOnLaudalla() {
        assertEquals(true, (arvaus.haeY() >= 0 && arvaus.haeY() < 10));
    }

    @Test
    public void arvaaVierekkainenPalauttaaVierekkaisenRuudun() {
        Ruutu vierekkainen = tekoaly.arvaaVierekkainen(ruutu);
        int vierekkainenX = vierekkainen.haeX();
        int vierekkainenY = vierekkainen.haeY();
        int ruutuX = ruutu.haeX();
        int ruutuY = ruutu.haeY();
        boolean xVieressa = vierekkainenX == ruutuX + 1 || vierekkainenX == ruutuX - 1;
        boolean yVieressa = vierekkainenY == ruutuY + 1 || vierekkainenY == ruutuY - 1;
        boolean xSama = vierekkainenX == ruutuX;
        boolean ySama = vierekkainenY == ruutuY;
        assertEquals(true, ((xVieressa && ySama) || (yVieressa && xSama)));
    }

    @Test
    public void tarkistaArvausPalauttaaFalseJosRuudunXKoordinaattiOnNegatiivinen() {
        assertEquals(false, tekoaly.tarkistaArvaus(new Ruutu(-5, 5)));
    }

    @Test
    public void tarkistaArvausPalauttaaFalseJosRuudunYKoordinaattiOnNegatiivinen() {
        assertEquals(false, tekoaly.tarkistaArvaus(new Ruutu(5, -8)));
    }

    @Test
    public void tarkistaArvausPalauttaaFalseJosRuudunXKoordinaattiMeneeYliPelilaudasta() {
        assertEquals(false, tekoaly.tarkistaArvaus(new Ruutu(14, 0)));
    }

    @Test
    public void tarkistaArvausPalauttaaFalseJosRuudunYKoordinaattiMeneeYliPelilaudasta() {
        assertEquals(false, tekoaly.tarkistaArvaus(new Ruutu(4, 88)));
    }

    @Test
    public void tarkistaArvausPalauttaaFalseJosRuutuOnJoArvattu() {
        Ruutu arvattu = tekoaly.teeSiirto();
        assertEquals(false, tekoaly.tarkistaArvaus(arvattu));
    }

    @Test
    public void tarkistaArvausAariarvotMeneeLapi1() {
        Ruutu aariarvo = new Ruutu(9, 9);
        assertEquals(true, tekoaly.tarkistaArvaus(aariarvo));
    }

    @Test
    public void tarkistaArvausAariarvotMeneeLapi2() {
        Ruutu aariArvo = new Ruutu(0, 0);
        assertEquals(true, tekoaly.tarkistaArvaus(aariArvo));
    }

    @Test
    public void tarkistaArvausPalauttaaTrueJosRuutuOnPelilaudallaJaRuutujaEiOleLisatty() {
        assertEquals(true, tekoaly.tarkistaArvaus(ruutu));
    }

    @Test
    public void arvaaOsumienPerusteellaPalauttaaOikeanRuudunKunArvauksiaOnEnemmanKuinYksi() {
        tekoaly.lisaaOsuma(new Ruutu(3, 4));
        tekoaly.lisaaOsuma(new Ruutu(3, 5));
        Ruutu arvattu = tekoaly.arvaaOsumienPerusteella();
        boolean arvo = arvattu.equals(new Ruutu(3, 6)) || arvattu.equals(new Ruutu(3, 3));
        assertEquals(true, arvo);
    }

    @Test
    public void arvaaOsumienPerusteellaPalauttaaOikeanRuudunKunArvauksiaOnEnemmanKuin1JaToinenPaaEiOlePelilaudalla() {
        tekoaly.lisaaOsuma(new Ruutu(9, 9));
        tekoaly.lisaaOsuma(new Ruutu(9, 8));
        assertEquals(new Ruutu(9, 7), tekoaly.arvaaOsumienPerusteella());
    }
    
    @Test
    public void osumatVaakaSuorassaPalauttaaTrueKunRuudukonRuutujenYKoordinaatitOvatSamat() {
        tekoaly.lisaaOsuma(new Ruutu(3, 4));
        tekoaly.lisaaOsuma(new Ruutu(4, 4));
        tekoaly.lisaaOsuma(new Ruutu(5, 4));
        assertEquals(true, tekoaly.osumatVaakasuorassa());
    }
    
    @Test
    public void osumatVaakaSuorassaPalauttaaFalseKunRuudukonRuutujenYKoordinaatitEivatOleSamat() {
        tekoaly.lisaaOsuma(new Ruutu(2, 2));
        tekoaly.lisaaOsuma(new Ruutu(2, 3));
        assertEquals(false, tekoaly.osumatVaakasuorassa());
    }
    
    @Test
    public void laivaUpotettuTyhjentaaOsumat() {
        tekoaly.lisaaOsuma(ruutu);
        tekoaly.laivaUpotettu();
        assertEquals(true, tekoaly.haeOsumat().isEmpty());
    }
    
    
    
    
}
