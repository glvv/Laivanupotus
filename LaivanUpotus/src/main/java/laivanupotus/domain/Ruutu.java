package laivanupotus.domain;

public class Ruutu implements Comparable<Ruutu> {

    private final int x;
    private final int y;
    private boolean osuttu;
    private Laiva laiva;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.osuttu = false;
        this.laiva = null;
    }

    public boolean haeOsuttu() {
        return osuttu;
    }

    public void onAmmuttu() {
        osuttu = true;
        if (this.laiva != null) {
            //muokkaa tähän jotain
        }
    }

    public int haeX() {
        return x;
    }

    public int haeY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }

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

    public Laiva haeLaiva() {
        return laiva;
    }

    public void asetaLaiva(Laiva laiva) {
        this.laiva = laiva;
    }

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
