package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestArctan {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("arctan(x)");
        
    }
    
    @Test
    public void testArctan() {
        
        assertEquals("1/(1+x^(2))", fPrime);
        
    }
    
}
