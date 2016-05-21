package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCosh {
    
    String fPrime;
    
    @Before
    public void setUp() {

        fPrime = new Derivative("cosh(x)").fPrime;
        
    }
    
    @Test
    public void testCosh() {
        
        assertEquals("sinh(x)", fPrime);
        
    }
    
}
