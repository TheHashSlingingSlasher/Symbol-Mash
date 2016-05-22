package integral.directantiderivative;

// Java Libraries
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
// Symbol-Mash Libraries
import math.calculus.Integral;

/**
 *
 * @author alec
 */
public class TestLn {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = new Integral("ln(x)").antiderivative;
        
    }
    
    @Test
    public void testLn() {
        
        assertEquals("xln(x)-x", antiderivative);
        
    }
    
}
