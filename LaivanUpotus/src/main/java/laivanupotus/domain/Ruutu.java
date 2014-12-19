package laivanupotus.domain;

public class Ruutu {

    private final int x;
    private final int y;
    private boolean osuttu;
    private boolean sisaltaaLaivan;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.osuttu = false;
        this.sisaltaaLaivan = false;
    }
    
    public boolean haeOsuttu() {
        return osuttu;
    }
    
    public void onAmmuttu() {
        osuttu = true;
    }
    
    public void sisallatLaivan() {
        sisaltaaLaivan = true;
    }
    
    public boolean sisallatkoLaivan() {
        return sisaltaaLaivan;
    }

}
