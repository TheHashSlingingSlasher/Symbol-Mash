package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestLn {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("ln(x)");
        
    }
    
    @Test
    public void testLn() {
        
        assertEquals("1/(x)", fPrime);
        
    }
    
}
