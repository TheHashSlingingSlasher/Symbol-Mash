package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestLog {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("log(x)").fPrime;
        
    }
    
    @Test
    public void testLog() {
        
        assertEquals("","");
        
    }
    
}