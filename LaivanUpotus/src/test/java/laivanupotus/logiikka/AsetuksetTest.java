package laivanupotus.logiikka;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AsetuksetTest {

    private Asetukset asetukset;

    public AsetuksetTest() {
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luotuAsetuksetLeveysOn10() {
        assertEquals(10, asetukset.haePelilautaLeveys());
    }

    @Test
    public void luotuasetuksetPituusOn10() {
        assertEquals(10, asetukset.haePelilautaPituus());
    }

    @Test
    public void asetaOletusLaivatLisaaOikeanMaaranEriLaivoja() {
        asetukset.asetaOletusLaivat();
        assertEquals(5, asetukset.haeLaivat().keySet().size());
    }

    @Test
    public void asetOletusLaivatLisaaOikeanMaaranLaivoja() {
        asetukset.asetaOletusLaivat();
        int laivoja = 0;
        for (int avain : asetukset.haeLaivat().keySet()) {
            laivoja += asetukset.haeLaivat().get(avain);
        }
        assertEquals(6, laivoja);
    }

    @Test
    public void onkoLaivaLisattyPalauttaaTrueJosLaivaOnJoLisatty() {
        asetukset.lisaaLaiva(1, 1);
        assertEquals(true, asetukset.onkoLaivaLisatty(1));
    }

    @Test
    public void onkoLaivaLisattyPalauttaaFalseJosLaivaaEiOleLisatty() {
        assertEquals(false, asetukset.onkoLaivaLisatty(4));
    }

    @Test
    public void pituudenAsettamisenJalkeenPituusOnKonstruktorissaAnnettuArvo() {
        asetukset.asetaPituus(50);
        assertEquals(50, asetukset.haePelilautaPituus());
    }

    @Test
    public void leveydenAsettamisenJalkeenLeveysOnKonstruktorissaAnnettuArvo() {
        asetukset.asetaLeveys(50);
        assertEquals(50, asetukset.haePelilautaLeveys());
    }
    
    @Test
    public void asetusSaannonAsettamisenJalkeenSeOnAnnettuArvo() {
        asetukset.laivatSaaKoskea(true);
        assertEquals(true, asetukset.laivatSaaKoskea());
    }
    
    @Test
    public void laivojenMaaranKerroinPalauttaaOikeanArvon() {
        asetukset.asetaLeveys(50);
        asetukset.asetaPituus(50);
        assertEquals(25, asetukset.haeLaivojenMaaranKerroin());
    }
    
    
    

}
