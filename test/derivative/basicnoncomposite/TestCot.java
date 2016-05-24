package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCot {
    
    String fPrime;
    
    
    @Before
    public void setUp() {
    
        fPrime = Derivative.differentiate("cot(x)");
        
    }
    
    @Test
    public void testCot() {
        
        assertEquals("-csc^2(x)", fPrime);
        
    }
    
}
