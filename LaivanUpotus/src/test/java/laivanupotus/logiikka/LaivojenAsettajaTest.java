package laivanupotus.logiikka;

import java.util.HashMap;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivojenAsettajaTest {

    private LaivojenAsettaja asettajaLaivatEiSaaKoskea;
    private LaivojenAsettaja asettajaLaivatSaaKoskea;
    private Laiva laiva;
    private Ruutu[] ruudut;
    private Pelilauta pelilauta;
    private Ruutu ruutu1;
    private Ruutu ruutu2;

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
        this.ruutu1 = new Ruutu(3, 4);
        this.ruutu2 = new Ruutu(3, 5);
        ruudut = new Ruutu[2];
        ruudut[0] = ruutu1;
        ruudut[1] = ruutu2;
        this.laiva = new Laiva(ruudut);
        this.pelilauta = new Pelilauta(10, 10);
        asettajaLaivatEiSaaKoskea.asetaPelilauta(pelilauta);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void juuriLuotuunAsettajaanEiLiityPelilautaa() {
        LaivojenAsettaja asettaja = new LaivojenAsettaja(false);
        assertEquals(null, asettaja.haePelilauta());
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
            for (Ruutu ruutu3 : metodinTuottamatRuudut) {
                if (ruutu.equals(ruutu3)) {
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
        asettajaLaivatEiSaaKoskea.lisaaRuudutVaratuiksi(laiva);
        assertEquals(8, pelilauta.haeVaratutRuudut().size());
    }
    
    @Test
    public void eiOleVaratullaRuudullaPalauttaaFalseJosRuutuOnVarattu() {
        pelilauta.lisaaVarattujaRuutuja(laiva.haeRuudut());
        boolean tulos = asettajaLaivatEiSaaKoskea.eiOleVaratullaRuudulla(laiva.haeRuudut()[0]);
        assertEquals(false, tulos);
    }
    
    @Test
    public void eiOleVaratullaRuudullaPalauttaaTrueJosRuutuEiOleVarattu() {
        boolean tulos = asettajaLaivatEiSaaKoskea.eiOleVaratullaRuudulla(laiva.haeRuudut()[0]);
        assertEquals(true, tulos);
    }
    
    @Test
    public void onPelilaudallaPalauttaaTrueKunRuutuOnPelilaudalla() {
        assertEquals(true, asettajaLaivatEiSaaKoskea.onPelilaudalla(ruutu1));
    }
    
    @Test
    public void onPelilaudallaPalauttaaFalseKunRuutuEiOlePelilaudalla() {
        Ruutu eiLaudalla = new Ruutu(662, 555);
        assertEquals(false, asettajaLaivatEiSaaKoskea.onPelilaudalla(eiLaudalla));
    }
    
    @Test
    public void voikoLaivanAsettaaPalauttaaFalseKunLaivaMeneeYliLaudan() {
        Ruutu[] ruudukko = {new Ruutu(3, 9), new Ruutu(3, 10), new Ruutu(3, 11)};
        assertEquals(false, asettajaLaivatEiSaaKoskea.voikoLaivanAsettaa(ruudukko));
    }
    
    @Test
    public void voikoLaivanAsettaaPalauttaaTrueKunLaivaOnPelilaudalla() {
        assertEquals(true, asettajaLaivatEiSaaKoskea.voikoLaivanAsettaa(laiva.haeRuudut()));
    }
    
    @Test
    public void arvoRuudutLaivalleArpooOikeanMaaranRuutuja() {
        assertEquals(4, asettajaLaivatEiSaaKoskea.arvoRuudutLaivalle(4).length);
    }
    
    @Test
    public void luoLaivatAutomaattisestiPalauttaaOikeanMaaranLaivoja() {
        HashMap<Integer, Integer> laivat = new HashMap<>();
        laivat.put(1, 3);
        laivat.put(2, 4);
        assertEquals(7, asettajaLaivatEiSaaKoskea.luoLaivatAutomaattisesti(laivat, pelilauta).size());
    }
    
    
    
    
    
    
    
    

}
