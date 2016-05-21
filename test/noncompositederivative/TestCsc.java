package noncompositederivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Derivative;

/**
 *
 * @author alec
 */
public class TestCsc {

    String fPrime;
    
    @Before
    public void setUp() {
        
        fPrime = new Derivative("csc(x)").fPrime;
        
    }
    
    @Test
    public void testCsc() {
        
        assertEquals("-csc(x)cot(x)", fPrime);
        
    }
    
}
