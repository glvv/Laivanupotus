package laivanupotus.domain;

public class Laiva {

    private final Ruutu[] ruudut;

    public Laiva(Ruutu... ruudut) {
        this.ruudut = ruudut;
    }

    public boolean uppoaako() {
        for (Ruutu ruutu : ruudut) {
            if (ruutu.haeOsuttu() == false) {
                return false;
            }
        }
        return true;
    }

    public Ruutu[] haeRuudut() {
        return ruudut;
    }

}
