package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestSinh {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("sinh(x)").fPrime;
        
    }
    
    @Test
    public void testSinh() {
        
        assertEquals("cosh(x)", fPrime);
        
    }
    
}
