package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestSinh {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("sinh(x)");
        
    }
    
    @Test
    public void testSinh() {
        
        assertEquals("cosh(x)", fPrime);
        
    }
    
}
