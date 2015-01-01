/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.domain;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raot
 */
public class LaivaTest {
    
    private Laiva laiva;
    
    public LaivaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Ruutu[] ruudut = {new Ruutu(3, 4), new Ruutu(3, 5)};
        this.laiva = new Laiva(ruudut);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunLaivan1RuutuOnOikein() {
        Ruutu[] laivanRuudut = laiva.haeRuudut();
        assertEquals(new Ruutu(3, 4), laivanRuudut[0]);
    }
    
    @Test
    public void luodunLaivan2RuutuOnOikein() {
        Ruutu[] laivanRuudut = laiva.haeRuudut();
        assertEquals(new Ruutu(3, 5), laivanRuudut[1]);
    }
    
    @Test
    public void laivaUppoaaJosKaikissaRuuduissaOnOsuma() {
        laiva.lisaaOsuma(new Ruutu(3, 4));
        laiva.lisaaOsuma(new Ruutu(3, 5));
        assertEquals(true, laiva.uppoaako());
    }
    
    @Test
    public void laivaEiuppoaJosKaikissaRuuduissaEiOleOsumaa() {
        laiva.lisaaOsuma(new Ruutu(3, 4));
        assertEquals(false, laiva.uppoaako());
    }
    
    @Test
    public void luodullaLaivallaEiOleOsumia() {
        boolean onOsuma = false;
        HashMap<Ruutu, Boolean> osumat = laiva.haeOsumat();
        for (Ruutu avain : laiva.haeRuudut()) {
            onOsuma = osumat.get(avain);
        }
        assertEquals(false, onOsuma);
    }
    
    

}
