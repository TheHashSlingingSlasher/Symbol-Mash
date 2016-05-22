package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestTan {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("tan(x)").fPrime;
        
    }
    
    @Test
    public void testTan() {
        
        assertEquals("sec^2(x)", fPrime);
        
    }

}
