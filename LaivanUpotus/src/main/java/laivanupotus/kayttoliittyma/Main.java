package laivanupotus.kayttoliittyma;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Tekstikayttoliittyma tekstikayttoliittyma = new Tekstikayttoliittyma(lukija);
        tekstikayttoliittyma.kaynnista();
    }

}
