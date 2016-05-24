package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestArccos {
    
    String fPrime;
    
    @Before
    public void setUp() {
    
        fPrime = Derivative.differentiate("arccos(x)");
    
    }
    
    @Test
    public void testArccos() {
        
        assertEquals("-1/(1+x^(2))^(1/2)", fPrime);
        
    }
    
}
