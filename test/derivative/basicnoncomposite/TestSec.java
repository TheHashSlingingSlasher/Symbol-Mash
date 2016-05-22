package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestSec {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("sec(x)").fPrime;
        
    }
    
    @Test
    public void testSec() {
        
        assertEquals("sec(x)tan(x)", fPrime);
        
    }
    
}
