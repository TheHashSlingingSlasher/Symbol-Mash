package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCsc {

    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("csc(x)");
        
    }
    
    @Test
    public void testCsc() {
        
        assertEquals("-csc(x)cot(x)", fPrime);
        
    }
    
}
