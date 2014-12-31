package laivanupotus.kayttoliittyma;

import java.util.HashMap;
import java.util.Scanner;
import laivanupotus.logiikka.SyotteenKasittelija;

/**
 * Tekstikäyttöliittymä Laivanupotus-peliin.
 */
public class Tekstikayttoliittyma implements Kayttoliittyma {

    private Scanner lukija = new Scanner(System.in);
    private final SyotteenKasittelija syotteenkasittelija;

    public Tekstikayttoliittyma(Scanner lukija) {
        this.lukija = lukija;
        this.syotteenkasittelija = new SyotteenKasittelija();
    }

    private void tulostaOhjeet() {
        System.out.println("Tervetuloa laivanupotukseen");
        System.out.println("");
        System.out.println("Valinnat: ");
        System.out.println("1. Pelaa oletusasetuksilla");
        System.out.println("2. Muokkaa asetuksia");
        System.out.println("");
    }

    public void kysyAsetukset() {
        String syote;
        while (true) {
            System.out.print("Anna komento (1 tai 2) ");
            syote = lukija.nextLine();
            if (syotteenkasittelija.tarkistaValinta(syote, 2)) {
                break;
            } else {
                System.out.println("Virheellinen syöte");
                System.out.println("");
            }
        }
        int valinta = Integer.parseInt(syote);
        System.out.println("");
        if (valinta == 1) {
            syotteenkasittelija.haeAsetukset().asetaOletusLaivat();
        } else {
            kysyLeveys();
            kysyPituus();
            System.out.println("");
            kysyLaivat();
        }
    }

    public void kysyLeveys() {
        while (true) {
            System.out.print("Anna pelilaudan leveys väliltä 10 - 100 ");
            String syote = lukija.nextLine();
            if (syotteenkasittelija.asetaPituus(syote)) {
                break;
            }
            System.out.println("Virheellinen syöte");
            System.out.println("");
        }
    }

    public void kysyPituus() {
        while (true) {
            System.out.print("Anna pelilaudan pituus väliltä 10 - 100 ");
            String syote = lukija.nextLine();
            if (syotteenkasittelija.asetaLeveys(syote)) {
                break;
            }
            System.out.println("Virheellinen syöte");
            System.out.println("");
        }
    }

    public void kysyLaivat() {
        HashMap<Integer, String> kysymykset = new HashMap<>();
        kysymykset.put(1, "Kuinka monta sukellusvenettä (1 ruutu) haluat? Valitse väliltä 1 - 3 ");
        kysymykset.put(2, "Kuinka monta hävittäjää (2 ruutua) haluat? Valitse väliltä 0 - 2 ");
        kysymykset.put(3, "Kuinka monta risteilijää (3 ruutua) haluat? Valitse väliltä 0 - 2 ");
        kysymykset.put(4, "Kuinka monta taistelulaivaa (4 ruutua) haluat? Valitse väliltä 0 - 1 ");
        kysymykset.put(5, "Kuinka monta lentotukialusta (5 ruutua) haluat? Valitse väliltä 0 - 1 ");
        for (int i = 1; i < 6; i++) {
            while (true) {
                System.out.print(kysymykset.get(i));
                String syote = lukija.nextLine();
                if (syotteenkasittelija.lisaaLaiva(i, syote)) {
                    break;
                }
                System.out.println("Virheellinen syöte");
            }
        }
    }

    public int[] kysySiirto() {
        String x;
        String y;
        while (true) {
            System.out.print("Anna X koordinaatti väliltä 1 - 10 ");
            x = lukija.nextLine();
            if (syotteenkasittelija.tarkistaSiirto(x, false)) {
                break;
            }
            System.out.println("Virheellinen syote");
        }
        while (true) {
            System.out.print("Anna Y koordinaatti väliltä 1 - 10 ");
            y = lukija.nextLine();
            if (syotteenkasittelija.tarkistaSiirto(y, true)) {
                break;
            }
            System.out.println("Virheellinen syöte");
        }
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

    @Override
    public void paivity() {

    }

}
