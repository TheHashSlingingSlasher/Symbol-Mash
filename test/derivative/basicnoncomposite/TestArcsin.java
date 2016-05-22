package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestArcsin {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("arcsin(x)").fPrime;
        
    }
    
    @Test
    public void testArcsin() {
        
        assertEquals("1/(1-x^(2))^(1/2)", fPrime);
        
    }
    
}
