package laivanupotus.logiikka;

import java.util.ArrayList;
import laivanupotus.domain.Laiva;
import laivanupotus.domain.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivojenAsettajaTest {

    private LaivojenAsettaja asettaja;
    private TestiArpoja testiarpoja;
    private Asetukset asetukset;

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
        this.asetukset = new Asetukset();
        asetukset.lisaaLaiva(1, 1);
        this.asettaja = new LaivojenAsettaja(asetukset);
        this.testiarpoja = new TestiArpoja();
        asettaja.asetaArpoja(testiarpoja);
    }

    @After
    public void tearDown() {
    }

    private void lisaaPalautusArvot(int x, int y, int vaaka0pysty1) {
        testiarpoja.lisaaPalautusArvo(x);
        testiarpoja.lisaaPalautusArvo(y);
        testiarpoja.lisaaPalautusArvo(vaaka0pysty1);
    }

    private boolean tarkistaEttaLaivassaOnOikeatRuudut(int laivanIndeksi, Ruutu... odotetut) {
        ArrayList<Laiva> lista = asettaja.luoLaivatAutomaattisesti();
        Laiva tarkistettava = lista.get(laivanIndeksi);
        Ruutu[] laivanRuudut = tarkistettava.haeRuudut();
        if (laivanRuudut.length == odotetut.length) {
            for (int i = 0; i < odotetut.length; i++) {
                if (odotetut[i].equals(laivanRuudut[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void asetaLaivatAutomaattisestiAsettaaYhdenLaivanOikeisiinKoordinaatteihinKunArpojaAntaa5Ja5() {
        lisaaPalautusArvot(5, 5, 1);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(0, new Ruutu(5, 5)));
    }

    @Test
    public void toinenLaivaArvotaanMuualleKunSeOsuuEnsimmaiseenLaivaan() {
        lisaaPalautusArvot(5, 5, 1);
        lisaaPalautusArvot(5, 5, 1);
        lisaaPalautusArvot(0, 1, 0);
        asetukset.lisaaLaiva(2, 1);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(1, new Ruutu(0, 1), new Ruutu(1, 1)));
    }

    @Test
    public void asetaLaivatAutomaattisestiEiPalautaNull() {
        lisaaPalautusArvot(5, 5, 1);
        ArrayList<Laiva> lista = asettaja.luoLaivatAutomaattisesti();
        assertEquals(true, lista != null);
    }

    @Test
    public void asetaLaivatAutomaattisestiArpooUudenLaivanJosLaivaEiMahduPelilaudalle() {
        asetukset.lisaaLaiva(2, 1);
        lisaaPalautusArvot(5, 5, 0);
        lisaaPalautusArvot(9, 9, 0);
        lisaaPalautusArvot(2, 2, 1);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(1, new Ruutu(2, 2), new Ruutu(2, 3)));
    }

    @Test
    public void asetaLaivatAutomaattisestiEiArvoUuttaLaivaaJosLaivaKoskeeReunaa1() {
        lisaaPalautusArvot(9, 9, 0);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(0, new Ruutu(9, 9)));
    }

    @Test
    public void asetaLaivatAutomaattisestiEiArvoUuttaLaivaaJosLaivaKoskeeReunaa2() {
        lisaaPalautusArvot(0, 0, 0);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(0, new Ruutu(0, 0)));
    }

    @Test
    public void asetaLaivatAutomaattisestiArpooUudenLaivanJosSeKoskeeToistaLaivaa() {
        lisaaPalautusArvot(0, 0, 0);
        lisaaPalautusArvot(1, 0, 1);
        lisaaPalautusArvot(5, 5, 0);
        asetukset.lisaaLaiva(2, 1);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(1, new Ruutu(5, 5), new Ruutu(6, 5)));
    }

    @Test
    public void asetaLaivatAutomaattisestiEiArvoUuttaLaivaaJosLaivanRuutuKoskeeDiagonaalisestiToistaLaivaa() {
        lisaaPalautusArvot(5, 5, 0);
        lisaaPalautusArvot(3, 6, 0);
        asetukset.lisaaLaiva(2, 1);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(1, new Ruutu(3, 6), new Ruutu(4, 6)));
    }

    @Test
    public void asetaLaivatAutomaattisestiLuoOikeatRuudutViidenRuudunLaivalle() {
        lisaaPalautusArvot(5, 5, 0);
        lisaaPalautusArvot(0, 0, 0);
        asetukset.lisaaLaiva(5, 1);
        assertEquals(true, tarkistaEttaLaivassaOnOikeatRuudut(1, new Ruutu(0, 0), new Ruutu(0, 1), new Ruutu(0, 2), new Ruutu(0, 3), new Ruutu(0, 4)));
    }

}
