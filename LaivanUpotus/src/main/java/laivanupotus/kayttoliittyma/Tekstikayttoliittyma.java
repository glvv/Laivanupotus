package laivanupotus.kayttoliittyma;

import java.util.Scanner;
import laivanupotus.logiikka.Asetukset;
import laivanupotus.logiikka.Logiikka;

public class Tekstikayttoliittyma implements Paivitettava {

    private Logiikka logiikka;
    private Scanner lukija = new Scanner(System.in);

    public Tekstikayttoliittyma(Scanner lukija) {
        this.lukija = lukija;
    }

    public void kaynnista() {
        System.out.println("Tervetuloa laivanupotukseen");
        Asetukset asetukset = new Asetukset();
        asetukset.asetaOletusLaivat();
        this.logiikka = new Logiikka(asetukset, this);
        aloitaPeli();
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

    public void kysyAsetukset() {
        System.out.println("Haluatko pelata oletussäännöillä?");
        System.out.println("Vastaa kyllä tai ei");
        System.out.println("Anna pelilaudan leveys väliltä 10-100");
        System.out.println("Anna pelilaudan pituus väliltä 10-100");
    }

    public void kerroSaannot() {

    }

    @Override
    public void paivity() {

    }

}
