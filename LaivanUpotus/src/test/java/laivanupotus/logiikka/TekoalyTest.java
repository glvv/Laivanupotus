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
    private Asetukset asetukset;
    private TestiArpoja testiarpoja;
    private Ruutu ruutu;

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
        this.asetukset = new Asetukset();
        this.tekoaly = new Tekoaly(asetukset);
        this.testiarpoja = new TestiArpoja();
        tekoaly.asetaArpoja(testiarpoja);
        satunnainenRuutux5y5();
    }

    @After
    public void tearDown() {
    }

    private void satunnainenRuutux5y5() {
        testiarpoja.lisaaPalautusArvo(5);
        testiarpoja.lisaaPalautusArvo(5);
        ruutu = tekoaly.teeSiirto();
    }
    
    @Test
    public void teeSiirtoPalauttaaSatunnaisenRuudunKunOsumiaEiOle() {
        assertEquals(new Ruutu(5, 5), ruutu);
    }
    
    @Test
    public void teeSiirtoArpooUudenRuudunKunSatunnainenRuutuOnArvattuJo() {
        testiarpoja.lisaaPalautusArvo(5);
        testiarpoja.lisaaPalautusArvo(5);
        testiarpoja.lisaaPalautusArvo(6);
        testiarpoja.lisaaPalautusArvo(6);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(6, 6), ruutu);
    }
    
    @Test
    public void teeSiirtoArpooYmparoivanRuudunKunOsumiaOnYksi() {
        tekoaly.lisaaOsuma(new Ruutu(5, 5));
        testiarpoja.lisaaPalautusArvo(0);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(4, 5), ruutu);
    }
    
    @Test
    public void teeSiirtoArpooUudenYmparoivanRuudunKunViereinenRuutuOnArvattu() {
        tekoaly.lisaaOsuma(new Ruutu(5, 5));
        testiarpoja.lisaaPalautusArvo(0);
        testiarpoja.lisaaPalautusArvo(0);
        testiarpoja.lisaaPalautusArvo(1);
        tekoaly.teeSiirto();
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(6, 5), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaLaivanPaastaKunOsumiaOnEnemmanKuin1() {
        tekoaly.lisaaOsuma(new Ruutu(5, 5));
        testiarpoja.lisaaPalautusArvo(0);
        tekoaly.teeSiirto();
        tekoaly.lisaaOsuma(new Ruutu(4, 5));
        testiarpoja.lisaaPalautusArvo(0);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(3, 5), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaToisestaPaastaKunToinenPaaOnArvattu() {
        
    }
    
    

}
