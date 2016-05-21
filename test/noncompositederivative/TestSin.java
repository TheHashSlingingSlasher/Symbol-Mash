package noncompositederivative;

import calculus.Derivative;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author alec
 */
public class TestSin {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("sin(x)").fPrime;
        
    }
    
    @Test
    public void testSin() {
        
        assertEquals(fPrime,"cos(x)");
        
    }
    
}
