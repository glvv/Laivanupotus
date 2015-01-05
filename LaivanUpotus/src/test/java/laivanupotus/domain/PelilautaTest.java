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
        this.laiva = new Laiva(new Ruutu(2, 3), new Ruutu(2, 4));
        ArrayList<Laiva> laivat = new ArrayList<>();
        laivat.add(laiva);
        this.pelilauta = new Pelilauta(laivat);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void katsoSiirtoPalauttaaRuutuunLiittyvanLaivan() {
        assertEquals(laiva, pelilauta.katsoRuutu(new Ruutu(2, 3)));
    }

    @Test
    public void luodullaPelilaudallaOnKonstruktorissaAnnettuLaiva() {
        assertEquals(laiva, pelilauta.haeLaivat().get(0));
    }

    @Test
    public void luodustaPelilaudastaLoytyyOikeallaRuudullaSiihenLiittyvaLaiva() {
        assertEquals(laiva, pelilauta.katsoRuutu(new Ruutu(2, 3)));
    }

    @Test
    public void pelilautaPalauttaaNullJosRuutuunEiLiityLaivaa() {
        assertEquals(null, pelilauta.katsoRuutu(new Ruutu(0, 0)));
    }
    
    private void upotaAlussaOlevaLaiva() {
        Ruutu ruutu1 = new Ruutu(2, 3);
        pelilauta.katsoRuutu(ruutu1).lisaaOsuma(ruutu1);
        Ruutu ruutu2 = new Ruutu(2, 4);
        pelilauta.katsoRuutu(ruutu2).lisaaOsuma(ruutu2);
    }
    
    @Test
    public void haeUponneidenLaivojenRuudutPalauttaaOikeanMaaranRuutuja() {
        upotaAlussaOlevaLaiva();
        assertEquals(2, pelilauta.haeUponneidenLaivojenRuudut().size());
    }
    
    @Test
    public void haeUponneidenLaivojenRuudutPalauttaaTyhjanListanJosLaivojaEiOleUpotettu() {
        assertEquals(true, pelilauta.haeUponneidenLaivojenRuudut().isEmpty());
    }
    
    @Test
    public void haeUponneidenLaivojenRuudutHakeeOikeatRuudut() {
        upotaAlussaOlevaLaiva();
        ArrayList<Ruutu> ruudut = pelilauta.haeUponneidenLaivojenRuudut();
        assertEquals(true, ruudut.contains(new Ruutu(2, 3)) && ruudut.contains(new Ruutu(2, 4)));
    }
    
    @Test
    public void haeLaivojenRuudutPalauttaaOikeanMaaranRuutuja() {
        assertEquals(2, pelilauta.haeLaivojenRuudut().size());
    }
    
    @Test
    public void haeLaivojenRuudutPalauttaaOikeatRuudut() {
        ArrayList<Ruutu> ruudut = pelilauta.haeLaivojenRuudut();
        assertEquals(true, ruudut.contains(new Ruutu(2, 3)) && ruudut.contains(new Ruutu(2, 4)));
    }
    
    @Test
    public void kaikkiLaivatUpotettuPalauttaaFalseKunKaikkiEivatOleUpotettu() {
        assertEquals(false, pelilauta.kaikkiLaivatUpotettu());
    }
    
    @Test
    public void kaikkiLaivatUpotettuPalauttaaTrueKunKaikkiLaivatOnUpotettu() {
        upotaAlussaOlevaLaiva();
        assertEquals(true, pelilauta.kaikkiLaivatUpotettu());
    }
    
    @Test
    public void uppoaakoRuutuunLiittyvaLaivaPalauttaaFalseJosRuutuunLiittyvaLaivaEiUppoa() {
        assertEquals(false, pelilauta.uppoaakoRuutuunLiittyvaLaiva(new Ruutu(2, 3)));
    }
    
    @Test
    public void uppoaakoRuutuunLiittyvaLaivaPalauttaaTrueKunRuutuunLiittyvaLaivaUppoaa() {
        upotaAlussaOlevaLaiva();
        assertEquals(true, pelilauta.uppoaakoRuutuunLiittyvaLaiva(new Ruutu(2, 3)));
    }
    
}
