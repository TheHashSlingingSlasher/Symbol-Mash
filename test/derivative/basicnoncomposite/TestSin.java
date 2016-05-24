package derivative.basicnoncomposite;

import math.calculus.Derivative;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author alec
 */
public class TestSin {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("sin(x)");
        
    }
    
    @Test
    public void testSin() {
        
        assertEquals(fPrime,"cos(x)");
        
    }
    
}
