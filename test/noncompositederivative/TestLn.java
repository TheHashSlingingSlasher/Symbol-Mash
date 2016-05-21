package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestLn {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("ln(x)").fPrime;
        
    }
    
    @Test
    public void testLn() {
        
        assertEquals("1/(x)", fPrime);
        
    }
    
}
