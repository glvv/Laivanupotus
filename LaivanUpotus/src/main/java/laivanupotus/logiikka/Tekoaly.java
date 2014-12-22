package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import laivanupotus.domain.Pelilauta;
import laivanupotus.domain.Ruutu;

public class Tekoaly extends Pelaaja {

    private final ArrayList<Ruutu> arvatut;
    private final Random arvaaja;
    private final ArrayList<Ruutu> osumat;

    public Tekoaly(Pelilauta pelilauta) {
        super(pelilauta);
        this.arvatut = new ArrayList<>();
        this.arvaaja = new Random();
        this.osumat = new ArrayList<>();
    }

    public Ruutu arvaaSatunnainen() {
        int x = arvaaja.nextInt(pelilauta.haeLeveys());
        int y = arvaaja.nextInt(pelilauta.haePituus());
        Ruutu arvaus = new Ruutu(x, y);
        if (!tarkistaArvaus(arvaus)) {
            return arvaaSatunnainen();
        }
        return arvaus;
    }

    public Ruutu arvaaVierekkainen(Ruutu ruutu) {
        int viereinen = arvaaja.nextInt(4);
        Ruutu arvaus;
        if (viereinen == 0) {
            arvaus = new Ruutu(ruutu.haeX() - 1, ruutu.haeY());
        } else if (viereinen == 1) {
            arvaus = new Ruutu(ruutu.haeX() + 1, ruutu.haeY());
        } else if (viereinen == 2) {
            arvaus = new Ruutu(ruutu.haeX(), ruutu.haeY() + 1);
        } else {
            arvaus = new Ruutu(ruutu.haeX(), ruutu.haeY() - 1);
        }
        if (!tarkistaArvaus(arvaus)) {
            return arvaaVierekkainen(ruutu);
        }
        return arvaus;
    }

    public boolean tarkistaArvaus(Ruutu arvaus) {
        if (arvaus.haeX() > pelilauta.haeLeveys() - 1 || arvaus.haeX() < 0) {
            return false;
        }
        if (arvaus.haeY() > pelilauta.haePituus() - 1 || arvaus.haeY() < 0) {
            return false;
        }
        return !arvatut.contains(arvaus);
    }

    @Override
    public Ruutu teeSiirto() {
        if (osumat.isEmpty()) {
            Ruutu arvaus = arvaaSatunnainen();
            arvatut.add(arvaus);
            return arvaus;
        } else {
            Ruutu arvaus = arvaaOsumienPerusteella();
            arvatut.add(arvaus);
            return arvaus;
        }
    }

    public void lisaaOsuma(Ruutu ruutu) {
        osumat.add(ruutu);
    }

    public void laivaUpotettu() {
        osumat.clear();
    }

    public Ruutu arvaaOsumienPerusteella() {
        if (osumat.size() < 2) {
            return arvaaVierekkainen(osumat.get(0));
        }
        if (osumatVaakasuorassa()) {
            return arvaaVaakasuorassaOlevienOsumienPerusteella();
        } else {
            return null;
            //kesken
        }
    }

    public boolean osumatVaakasuorassa() {
        Ruutu ensimmainen = osumat.get(0);
        for (int i = 1; i < osumat.size(); i++) {
            if (osumat.get(i).haeY() != ensimmainen.haeY()) {
                return false;
            }
        }
        return true;
    }

    public Ruutu arvaaVaakasuorassaOlevienOsumienPerusteella() {
        Collections.sort(osumat);
        int arvaus = arvaaja.nextInt(2);
        Ruutu arvattuRuutu;
        if (arvaus == 0) {
            Ruutu ruutu = osumat.get(0);
            arvattuRuutu = new Ruutu(ruutu.haeX() - 1, ruutu.haeY());
        } else {
            Ruutu ruutu = osumat.get(osumat.size() - 1);
            arvattuRuutu = new Ruutu(ruutu.haeX() + 1, ruutu.haeY());
        }
        if (!tarkistaArvaus(arvattuRuutu)) {
            return arvaaVaakasuorassaOlevienOsumienPerusteella();
        }
        return arvattuRuutu;
    }
//
//    public void arvaaPystysuorassaOlevienOsumienPerusteella() {
//        
//    }
}
