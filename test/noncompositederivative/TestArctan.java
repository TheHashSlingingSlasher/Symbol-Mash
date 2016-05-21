package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestArctan {
    
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("arctan(x)").fPrime;
        
    }
    
    @Test
    public void testArctan() {
        
        assertEquals("1/(1+(x)^(2))", fPrime);
        
    }
    
}
