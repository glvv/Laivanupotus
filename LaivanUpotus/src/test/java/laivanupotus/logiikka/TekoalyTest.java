package laivanupotus.logiikka;

import laivanupotus.domain.Asetukset;
import laivanupotus.testivalineet.TestiArpoja;
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
    
    private void arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatVaakasuoraan() {
        tekoaly.lisaaOsuma(new Ruutu(5, 5));
        testiarpoja.lisaaPalautusArvo(0);
        tekoaly.teeSiirto();
        tekoaly.lisaaOsuma(new Ruutu(4, 5));
        testiarpoja.lisaaPalautusArvo(0);
        ruutu = tekoaly.teeSiirto();
    }
    
    @Test
    public void teeSiirtoArvaaLaivanPaastaKunOsumiaOnEnemmanKuin1KunLaivatVaakasuorassa() {
        arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatVaakasuoraan();
        assertEquals(new Ruutu(3, 5), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaToisestaPaastaKunToinenPaaOnArvattuKunLaivatVaakasuorassa() {
        arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatVaakasuoraan();
        testiarpoja.lisaaPalautusArvo(0);
        testiarpoja.lisaaPalautusArvo(1);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(6, 5), ruutu);
    }
    
    private void arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatPystysuoraan() {
        tekoaly.lisaaOsuma(ruutu);
        testiarpoja.lisaaPalautusArvo(2);
        ruutu = tekoaly.teeSiirto();
        tekoaly.lisaaOsuma(ruutu);
        testiarpoja.lisaaPalautusArvo(0);
        ruutu = tekoaly.teeSiirto();
    }
    
    @Test
    public void teeSiirtoArvaaLaivanPaastaKunOsumiaOnEnemmanKuin1LaivatPystysuorassa() {
        arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatPystysuoraan();
        assertEquals(new Ruutu(5, 4), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaToisestaPaastaKunOsumiaOnEnemmanKuin1JaToisessaPaassaEiOleOsumaaLaivatPystysuorassa() {
        arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatPystysuoraan();
        testiarpoja.lisaaPalautusArvo(0);
        testiarpoja.lisaaPalautusArvo(1);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(5, 7), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaSatunnaisenRuudunKunLaivaOnUpotettu() {
        arvaaOsumienPerusteellaKunOsumiaOnEnemmanKuin1LaivatPystysuoraan();
        tekoaly.laivaUpotettu();
        testiarpoja.lisaaPalautusArvo(3);
        testiarpoja.lisaaPalautusArvo(7);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(3, 7), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaVierekkaisenRuudunKunOsumiaOnYksi() {
        tekoaly.lisaaOsuma(ruutu);
        testiarpoja.lisaaPalautusArvo(3);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(5, 4), ruutu);
    }
    
    private void teeOsumaPelilaudanYlareunaan() {
        testiarpoja.lisaaPalautusArvo(9);
        testiarpoja.lisaaPalautusArvo(9);
        ruutu = tekoaly.teeSiirto();
        tekoaly.lisaaOsuma(ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaUudenRuudunJosRuutuYKoordinaattiEiOlePelilaudalla() {
        teeOsumaPelilaudanYlareunaan();
        testiarpoja.lisaaPalautusArvo(2);
        testiarpoja.lisaaPalautusArvo(3);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(9, 8), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaUudenRuudunJosRuutuXKoordinaattiEiOlePelilaudalla() {
        teeOsumaPelilaudanYlareunaan();
        testiarpoja.lisaaPalautusArvo(1);
        testiarpoja.lisaaPalautusArvo(0);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(8, 9), ruutu);
    }
    
    @Test
    public void teeSiirtoArvaaUudenRuudunJosArvattuRuutuOnUpotetunLaivanVieressa() {
        tekoaly.lisaaOsuma(new Ruutu(5, 5));
        tekoaly.laivaUpotettu();
        testiarpoja.lisaaPalautusArvo(4);
        testiarpoja.lisaaPalautusArvo(5);
        testiarpoja.lisaaPalautusArvo(9);
        testiarpoja.lisaaPalautusArvo(9);
        ruutu = tekoaly.teeSiirto();
        assertEquals(new Ruutu(9, 9), ruutu);
    }
    
}
