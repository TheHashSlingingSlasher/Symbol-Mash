package derivative.combinednoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class SumOfSingleVariable {
    
    String fPrime;

    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("x+x");
        
    }
    
    @Test
    public void testSumOfSingleVariable() {
        
        assertEquals("(1 + 1)", fPrime);
        
    }
    
}
