package laivanupotus.domain;

/**
 * Luokka kuvaa yhtä ruutua laivanupotuksen pelilaudalla.
 */
public class Ruutu implements Comparable<Ruutu> {

    private final int x;
    private final int y;

    /**
     * Konstruktorissa ruudulle annetaan x ja y -koordinaatit.
     *
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti.
     */
    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int haeX() {
        return x;
    }

    public int haeY() {
        return y;
    }

    /**
     * Kaksi Ruutu-oliota ovat samat jos niiden x ja y koordinaatit ovat samat.
     *
     * @return HashCode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }

    /**
     * Kaksi ruutua ovat samat, jos niiden x ja y koordinaatit ovat samat
     *
     * @param obj Vertailtava Ruutu
     * @return Ovatko Ruutujen x ja y koordinaatit samat
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ruutu other = (Ruutu) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    /**
     * Metodilla voi verrata kahta ruutua jos ja vain jos niiden x-koordinaatit
     * ovat samat tai niiden y koordinaatit ovat samat Tällöin metodi vertaa
     * sitä koordinaattia,joka ei ole sama
     *
     * @param t verrattava Ruutu-olio
     * @return Onko ruutu pienempi vai suurempi
     */
    @Override
    public int compareTo(Ruutu t) {
        if (t.haeX() == this.x) {
            return this.y - t.haeY();
        } else if (t.haeY() == this.y) {
            return this.x - t.haeX();
        } else {
            return 0;
        }
    }
}
