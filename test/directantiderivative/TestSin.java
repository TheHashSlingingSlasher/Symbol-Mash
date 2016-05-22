package directantiderivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import calculus.Integral;

/**
 *
 * @author alec
 */
public class TestSin {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = new Integral("sin(x)").antiderivative;
        
    }
    
    @Test
    public void testSin() {
        
        assertEquals("-cos(x)", antiderivative);
        
    }
    
}
