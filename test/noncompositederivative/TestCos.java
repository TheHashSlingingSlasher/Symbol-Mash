package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCos {
   
    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("cos(x)").fPrime;
        
    }
    
    @Test
    public void testCos() {
        
        assertEquals("-sin(x)", fPrime);
        
    }

}
