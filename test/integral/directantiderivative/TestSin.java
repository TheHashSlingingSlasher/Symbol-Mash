package integral.directantiderivative;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import math.calculus.Integral;

/**
 *
 * @author alec
 */
public class TestSin {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = Integral.integrate("sin(x)");
        
    }
    
    @Test
    public void testSin() {
        
        assertEquals("-cos(x)", antiderivative);
        
    }
    
}
