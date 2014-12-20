/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laivanupotus.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raot
 */
public class LaivaTest {
    
    private Laiva laiva;
    
    public LaivaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Ruutu[] ruudut = {new Ruutu(3, 4), new Ruutu(3, 5)};
        this.laiva = new Laiva(ruudut);
    }
    
    @After
    public void tearDown() {
    }
    
    

}
