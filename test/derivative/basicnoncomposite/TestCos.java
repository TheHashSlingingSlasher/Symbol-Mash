package derivative.basicnoncomposite;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCos {
   
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = Derivative.differentiate("cos(x)");
        
    }
 
    @Test
    public void testCos() {
        
        assertEquals("-sin(x)", fPrime);
        
    }

}
