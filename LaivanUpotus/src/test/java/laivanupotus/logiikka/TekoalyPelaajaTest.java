package laivanupotus.logiikka;

import laivanupotus.domain.Asetukset;
import laivanupotus.domain.Ruutu;
import laivanupotus.testivalineet.TestiArpoja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TekoalyPelaajaTest {

    private TekoalyPelaaja tk;
    private Logiikka logiikka;
    private TestiArpoja arpoja;

    public TekoalyPelaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Asetukset asetukset = new Asetukset();
        asetukset.lisaaLaiva(1, 1);
        logiikka = new Logiikka(asetukset);
        tk = new TekoalyPelaaja(asetukset, logiikka);
        arpoja = new TestiArpoja();
        tk.haeTekoaly().asetaArpoja(arpoja);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodunTekoalynSiirrotListaEiOleNull() {
        assertNotNull(tk.haeTehdytSiirrot());
    }

    @Test
    public void luodunTekoalyPelaajanOsumatListaEiOleNull() {
        assertNotNull(tk.haeOsumat());
    }

    private Ruutu haeOsuvaRuutu() {
        return logiikka.haeLaivojenRuudut(2).get(0);
    }

    private Ruutu haeRuutuJokaEiOsu() {
        Ruutu ruutu = haeOsuvaRuutu();
        if (ruutu.haeX() < 8) {
            ruutu = new Ruutu(ruutu.haeX() + 2, ruutu.haeY());
        } else {
            ruutu = new Ruutu(ruutu.haeX() - 2, ruutu.haeY());
        }
        return ruutu;
    }

    private Ruutu luoPalautusArvotEiOsuvalleSiirrolle() {
        Ruutu eiOsu = haeRuutuJokaEiOsu();
        arpoja.lisaaPalautusArvo(eiOsu.haeX());
        arpoja.lisaaPalautusArvo(eiOsu.haeY());
        return eiOsu;
    }

    private Ruutu luoPalautusArvotOsuvalleSiirrolle() {
        Ruutu osuva = haeOsuvaRuutu();
        arpoja.lisaaPalautusArvo(osuva.haeX());
        arpoja.lisaaPalautusArvo(osuva.haeY());
        return osuva;
    }

    @Test
    public void ohiAmmutunSiirronJalkeenRuutuSiirroissaOnOikeaRuutu() {
        Ruutu eiOsu = luoPalautusArvotEiOsuvalleSiirrolle();
        tk.teeSiirto();
        assertEquals(eiOsu, tk.haeTehdytSiirrot().get(0));
    }

    private Ruutu osuJaAmmuOhi() {
        Ruutu osuva = luoPalautusArvotOsuvalleSiirrolle();
        luoPalautusArvotEiOsuvalleSiirrolle();
        tk.teeSiirto();
        return osuva;
    }

    @Test
    public void laivanUpottamisenJalkeenTekoalyllaEiOleOsumia() {
        osuJaAmmuOhi();
        assertEquals(true, tk.haeTekoaly().haeOsumat().isEmpty());
    }

    @Test
    public void ruutuunOsumisenJalkeenOsumissaOnOikeaRuutu() {
        Ruutu osuva = osuJaAmmuOhi();
        assertEquals(osuva, tk.haeOsumat().get(0));
    }

    @Test
    public void vuoroPelattuTyhjentaaSiirrot() {
        osuJaAmmuOhi();
        tk.vuoroPelattu();
        assertEquals(true, tk.haeTehdytSiirrot().isEmpty());
    }
}
