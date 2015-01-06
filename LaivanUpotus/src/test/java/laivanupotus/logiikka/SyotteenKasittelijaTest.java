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
    
    @Test
    public void asetaLeveysPalauttaaFalseJosSyoteEiOleKokonaisluku() {
        assertEquals(false, sk.asetaLeveys("lolcat"));
    }
    
    @Test
    public void asetaLeveysPalauttaaFalseJosSyoteOnKokonaisLukuMuttaLiianPieni() {
        assertEquals(false, sk.asetaLeveys("9"));
    }
    
    @Test
    public void asetaLeveysPalauttaaFalseJosSyoteOnKokonaisLukuMuttaLiianIso() {
        assertEquals(false, sk.asetaLeveys("51"));
    }
    
    @Test
    public void asetaLeveysPalauttaaTrueJosSyoteOnKokonaislukuJaRajoissa() {
        assertEquals(true, sk.asetaLeveys("13"));
    }
    
    @Test
    public void asetaLeveysMuuttaaLeveyttaKunSyoteOnKunnollinen() {
        sk.asetaLeveys("13");
        assertEquals(13, sk.haeAsetukset().haePelilautaLeveys());
    }
    
    @Test
    public void asetaLeveysEiMuutaLeveyttaJosSyoteEiOleKunnollinen() {
        sk.asetaLeveys("2");
        assertEquals(10, sk.haeAsetukset().haePelilautaLeveys());
    }
    
    @Test
    public void asetaPituusPalauttaaTrueKunSyoteOnKunnollinen() {
        assertEquals(true, sk.asetaPituus("13"));
    }
    
    @Test
    public void asetaPituusPalauttaaFalseKunSyoteEiOleKunnollinen() {
        assertEquals(false, sk.asetaPituus("pizzacake"));
    }
    
    @Test
    public void lisaaLaivaPalauttaaFalseKunLaivanKokoOnLiianSuuri() {
        assertEquals(false, sk.lisaaLaiva(900, "1"));
    }
    
    @Test
    public void lisaaLaivaEiLisaaLaivaaKunLaivanMaaraOnLiianSuuri() {
        sk.lisaaLaiva(1, "90");
        assertEquals(true, sk.haeAsetukset().haeLaivat().isEmpty());
    }
            
    @Test
    public void lisaaLaivaPalauttaaTrueKunMaaraOn0() {
        assertEquals(true, sk.lisaaLaiva(2, "0"));
    }
    
    @Test
    public void lisaaLaivaPalauttaaFalseKunMaaraEiOleKokonaisluku(){
        assertEquals(false, sk.lisaaLaiva(1, "llllllll"));
    }
    
    @Test
    public void lisaaLaivaLaivanMaaranKerroinToimii() {
        sk.asetaLeveys("50");
        sk.asetaPituus("50");
        assertEquals(true, sk.lisaaLaiva(5, "25"));
    }
    
    @Test
    public void lisaaLaivaLaivanLisaaminenTapahtuuJosSyoteOnKunnollinen() {
        sk.lisaaLaiva(1, "1");
        assertEquals(true, sk.haeAsetukset().haeLaivat().get(1) == 1);
    }
    
    @Test
    public void lisaaLaivaOnnistuuKunKokoOn1JaMaara2() {
        assertEquals(true, sk.lisaaLaiva(1, "2"));
    }
    
    @Test
    public void lisaaLaivaOnnistuuKunKokoOn2JaMaara1() {
        assertEquals(true, sk.lisaaLaiva(2, "1"));
    }
    
    @Test
    public void lisaaLaivaOnnistuuKunKokoOn3JaMaara1() {
        assertEquals(true, sk.lisaaLaiva(3, "1"));
    }
    
    @Test
    public void lisaaLaivaOnnistuuKunKokoOn4jaMaara1() {
        assertEquals(true, sk.lisaaLaiva(4, "1"));
    }
    
    
    
    
    

}
