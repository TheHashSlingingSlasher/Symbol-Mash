package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCosh {
    
    String fPrime;
    
    @Before
    public void setUp() {

        fPrime = Derivative.differentiate("cosh(x)");
        
    }
    
    @Test
    public void testCosh() {
        
        assertEquals("sinh(x)", fPrime);
        
    }
    
}
