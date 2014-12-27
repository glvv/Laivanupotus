package laivanupotus.kayttoliittyma;

import java.util.Scanner;
import laivanupotus.logiikka.Asetukset;
import laivanupotus.logiikka.Logiikka;
import laivanupotus.logiikka.SyotteenKasittelija;

public class Tekstikayttoliittyma implements Paivitettava {

    private Logiikka logiikka;
    private Scanner lukija = new Scanner(System.in);
    private final SyotteenKasittelija syotteenkasittelija;

    public Tekstikayttoliittyma(Scanner lukija) {
        this.lukija = lukija;
        this.syotteenkasittelija = new SyotteenKasittelija();
    }

    public void kaynnista() {
        tulostaOhjeet();
        Asetukset asetukset = kysyAsetukset();
        this.logiikka = new Logiikka(asetukset, this);
        aloitaPeli();
    }
    
    public void tulostaOhjeet(){
        System.out.println("Tervetuloa laivanupotukseen");
        System.out.println("");
        System.out.println("Valinnat: ");
        System.out.println("1. Pelaa oletusasetuksilla");
        System.out.println("2. Muokkaa asetuksia");
        System.out.println("");
    }
    
    public Asetukset kysyAsetukset() {
        String syote;
        while (true) {
            System.out.println("Anna komento (1 tai 2)");
            syote = lukija.nextLine();
            if (syotteenkasittelija.tarkistaValinta(syote, 2)) {    
                break;
            } else {
                System.out.println("Virheellinen syöte");
                System.out.println("");
            }
        }
        int valinta = Integer.parseInt(syote);
        Asetukset oletus = syotteenkasittelija.haeAsetukset();
        if (valinta == 1) {
            oletus.asetaOletusLaivat();
        } else {
            kysyMittasuhteet(oletus);
            kysyLaivat(oletus);
        }
        return oletus;
    }
    
    public Asetukset kysyMittasuhteet(Asetukset asetukset) {
        return null;
    }

    public Asetukset kysyLaivat(Asetukset asetukset) {
        return null;
    }

    public void aloitaPeli() {
        while (true) {
            tulostaTilanne();
            logiikka.pelaaVuoro(kysySiirto());
        }
    }

    public int[] kysySiirto() {
        System.out.print("Anna X koordinaatti väliltä 1 - 10 ");
        String x = lukija.nextLine();
        System.out.print("Anna Y koordinaatti väliltä 1 - 10 ");
        String y = lukija.nextLine();
        int xMuunnettu = Integer.parseInt(x);
        int yMuunnettu = Integer.parseInt(y);
        xMuunnettu = xMuunnettu - 1;
        yMuunnettu = logiikka.haePelaaja1Pelilauta().haeLeveys() - yMuunnettu;
        int[] siirto = {yMuunnettu, xMuunnettu};
        return siirto;
    }

    public void tulostaTilanne() {
        System.out.println("Vuoro = " + logiikka.haeVuoro());
        char[][] tilannePelaaja1 = logiikka.haeTilanne(logiikka.haePelaaja1Pelilauta(), logiikka.haePelaaja1Laivat());
        System.out.println();
        System.out.println("Pelaajan ruudut");
        tulostaPelilauta(tilannePelaaja1);
        char[][] tilannePelaaja2 = logiikka.haeTilanne(logiikka.haePelaaja2Pelilauta(), logiikka.haePelaaja2Laivat());
        System.out.println();
        System.out.println("Tekoalyn ruudut");
        tulostaPelilauta(tilannePelaaja2);
        System.out.println("");
        tulostaPisteet();
        System.out.println("");
    }

    public void tulostaPisteet() {
        System.out.println("Pelaajan pisteet " + logiikka.upotetutLaivat(logiikka.haePelaaja2Laivat()));
        System.out.println("Tekoalyn pisteet " + logiikka.upotetutLaivat(logiikka.haePelaaja1Laivat()));
    }

    public void tulostaPelilauta(char[][] tilanne) {
        for (char[] taulukko : tilanne) {
            for (char kirjain : taulukko) {
                if (kirjain != '\0') {
                    System.out.print(kirjain + " ");
                } else {
                    System.out.print("v ");
                }
            }
            System.out.println("");
        }
    }


    public void kerroSaannot() {

    }

    @Override
    public void paivity() {

    }

}
