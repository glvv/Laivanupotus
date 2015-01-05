package laivanupotus.testivalineet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestiArpojaTest {
    private TestiArpoja testiarpoja;
    
    public TestiArpojaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.testiarpoja = new TestiArpoja();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void nextIntPalauttaaOikeanArvon() {
        testiarpoja.lisaaPalautusArvo(3);
        assertEquals(3, testiarpoja.nextInt(5));
    }

    
}
