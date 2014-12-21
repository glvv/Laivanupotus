/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.logiikka;

import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;
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
public class LaivojenAsettajaTest {

    private LaivojenAsettaja asettajaLaivatEiSaaKoskea;
    private LaivojenAsettaja asettajaLaivatSaaKoskea;
    private Laiva laiva;
    private Ruutu[] ruudut;
    private Pelilauta pelilauta;

    public LaivojenAsettajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.asettajaLaivatEiSaaKoskea = new LaivojenAsettaja(false);
        this.asettajaLaivatSaaKoskea = new LaivojenAsettaja(true);
        Ruutu ruutu1 = new Ruutu(3, 4);
        Ruutu ruutu2 = new Ruutu(3, 5);
        ruudut = new Ruutu[2];
        ruudut[0] = ruutu1;
        ruudut[1] = ruutu2;
        this.laiva = new Laiva(ruudut);
        this.pelilauta = new Pelilauta(10, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void juuriLuotuunAsettajaanEiLiityPelilautaa() {
        
        assertEquals(null, asettajaLaivatEiSaaKoskea.haePelilauta());
    }

    @Test
    public void juuriluodunAsettajanSaantoOnOikein() {
        assertEquals(false, asettajaLaivatEiSaaKoskea.laivatSaaKoskea());
    }

    @Test
    public void juuriLuodunAsettajanSaantoOnOikein2() {
        assertEquals(true, asettajaLaivatSaaKoskea.laivatSaaKoskea());
    }

    @Test
    public void luoLaivaaYmparoivatRuudutLuoOikeatRuudut() {
        Ruutu[] metodinTuottamatRuudut = asettajaLaivatEiSaaKoskea.luoLaivaaYmparoivatRuudut(laiva);
        boolean taulukossaOnVaadittavatRuudut = true;
        Ruutu[] oikeatRuudut = {new Ruutu(3, 3), new Ruutu(4, 4), new Ruutu(2, 4), new Ruutu(2, 5), new Ruutu(4, 5), new Ruutu(3, 6)};
        for (Ruutu ruutu : oikeatRuudut) {
            boolean loytyy = false;
            for (Ruutu ruutu2 : metodinTuottamatRuudut) {
                if (ruutu.equals(ruutu2)) {
                    loytyy = true;
                }
            }
            taulukossaOnVaadittavatRuudut = loytyy;
        }
        assertEquals(true, taulukossaOnVaadittavatRuudut);
    }

    @Test
    public void luoLaivaaYmparoivatRuudutLuoTarpeeksiRuutuja() {
        Ruutu[] metodinTuottamatRuudut = asettajaLaivatEiSaaKoskea.luoLaivaaYmparoivatRuudut(laiva);
        assertEquals(true, metodinTuottamatRuudut.length >= laiva.haeRuudut().length * 2 + 2);
    }
    
    @Test
    public void lisaaRuudutVaratuiksiLisaaOikeanMaaranRuutujaKunLaivatSaavatKoskea() {
        asettajaLaivatSaaKoskea.asetaPelilauta(pelilauta);
        asettajaLaivatSaaKoskea.lisaaRuudutVaratuiksi(laiva);
        assertEquals(2, pelilauta.haeVaratutRuudut().size());
    }
    
    @Test
    public void lisaaRuudutVaratuiksiLisaaOikeanMaaranRuutujaKunLaivatEivatSaaKoskea() {
        asettajaLaivatEiSaaKoskea.asetaPelilauta(pelilauta);
        asettajaLaivatEiSaaKoskea.lisaaRuudutVaratuiksi(laiva);
        assertEquals(8, pelilauta.haeVaratutRuudut().size());
    }
    
    @Test
    public void eiOleVaratullaRuudullaPalauttaaFalseJosRuutuOnVarattu() {
        //kesken
    }
    
    
    
    

}
