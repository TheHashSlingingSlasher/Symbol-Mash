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
public class TestCos {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = Integral.integrate("cos(x)");
        
    }
    
    @Test
    public void testCos() {
        
        assertEquals("sin(x)", antiderivative);
        
    }
    
}
