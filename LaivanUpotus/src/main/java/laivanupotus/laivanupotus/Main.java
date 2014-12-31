package laivanupotus.laivanupotus;

import java.util.Scanner;
import laivanupotus.kayttoliittyma.Tekstikayttoliittyma;

public class Main {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Tekstikayttoliittyma tekstikayttoliittyma = new Tekstikayttoliittyma(lukija);
    }

}
