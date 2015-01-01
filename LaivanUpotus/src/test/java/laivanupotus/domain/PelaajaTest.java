package laivanupotus.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    
    private Pelaaja pelaaja;
    private Laiva laiva;
    
    public PelaajaTest() {
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
        this.pelaaja = new Pelaaja(laivat);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void siirronLisaamisenJalkeenListassaOnSiirto() {
        pelaaja.lisaaSiirto(new Ruutu(3, 4));
        assertEquals(new Ruutu(3, 4), pelaaja.haeSiirrot().get(0));
    }
    
    @Test
    public void voittaakoPalauttaaTrueJosPisteetOnYhtaSuuretKuinLaivojenMaara() {
        pelaaja.lisaaPiste();
        assertEquals(true, pelaaja.voittaako());
    }
    
    @Test
    public void voittaakoPalauttaaFalseJosPisteitaOnVahemmanKuinLaivojenMaara() {
        assertEquals(false, pelaaja.voittaako());
    }
    
    @Test
    public void lisaaPisteMetodinJalkeenPisteitaOnYksi() {
        pelaaja.lisaaPiste();
        assertEquals(1, pelaaja.haePisteet());
    }
    
    @Test
    public void katsoSiirtoPalauttaaRuutuunLiittyvanLaivan() {
        assertEquals(laiva, pelaaja.katsoSiirto(new Ruutu(2, 3)));
    }
    
    @Test
    public void luodullaPelaajallaOnKonstruktorissaAnnettuLaiva() {
        assertEquals(laiva, pelaaja.haeLaivat().get(0));
    }
    
    
    
    

}
