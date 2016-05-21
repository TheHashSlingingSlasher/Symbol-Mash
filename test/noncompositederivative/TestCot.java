package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCot {
    
    String fPrime;
    
    
    @Before
    public void setUp() {
    
        fPrime = new Derivative("cot(x)").fPrime;
        
    }
    
    @Test
    public void testCot() {
        
        assertEquals("-csc^2(x)", fPrime);
        
    }
    
}
