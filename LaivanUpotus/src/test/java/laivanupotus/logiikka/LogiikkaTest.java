package laivanupotus.logiikka;

import java.util.Scanner;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Ruutu;
import laivanupotus.kayttoliittyma.Tekstikayttoliittyma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogiikkaTest {

    private Logiikka logiikka;

    public LogiikkaTest() {
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
        asetukset.asetaOletusLaivat();
        this.logiikka = new Logiikka(asetukset, new Tekstikayttoliittyma(new Scanner(System.in)));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void liitaLaivatRuutuihinLiittaaLaivanRuutuun() {
        Laiva laiva = logiikka.haePelaaja1Laivat().get(0);
        int ruutuX = laiva.haeRuudut()[0].haeX();
        int ruutuY = laiva.haeRuudut()[0].haeY();
        assertEquals(laiva, logiikka.haePelaaja1Pelilauta().haeRuutu(ruutuX, ruutuY).haeLaiva());
    }

    @Test
    public void upotetutLaivatPalauttaa1Kun1LaivaOnUpotettu() {
        Laiva laiva = logiikka.haePelaaja1Laivat().get(0);
        for (Ruutu ruutu : laiva.haeRuudut()) {
            logiikka.haePelaaja1Pelilauta().haeRuutu(ruutu.haeX(), ruutu.haeY()).onAmmuttu();
        }
        assertEquals(1, logiikka.upotetutLaivat(logiikka.haePelaaja1Laivat()));
    }

}
