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
public class TestCot {
    
    String antiderivative;
    
    @Before
    public void setUp() {
    
        antiderivative = Integral.integrate("cot(x)");
    
    }
    
    @Test
    public void testCot() {
        
        assertEquals("ln(sin(x))", antiderivative);
        
    }
    
}
