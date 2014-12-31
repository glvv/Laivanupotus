package laivanupotus.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class TestiArpoja extends Random {
    
    private final ArrayList<Integer> palautusArvot;
    private int moneskoSyote;

    public TestiArpoja() {
        super();
        this.moneskoSyote = 0;
        this.palautusArvot = new ArrayList<>();
    }
    
    @Override
    public int nextInt(int raja) {
        moneskoSyote++;
        return palautusArvot.get(moneskoSyote - 1);
    }
    
    public void lisaaPalautusArvo(int luku) {
        palautusArvot.add(luku);
    }
    
    
}
