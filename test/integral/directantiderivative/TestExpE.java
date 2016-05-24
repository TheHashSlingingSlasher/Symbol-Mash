package integral.directantiderivative;

// Java Libraries
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
// Sybmol-Mash Libraries
import math.calculus.Integral;

/**
 *
 * @author alec
 */
public class TestExpE {
    
    String antiderivative;
    
    @Before
    public void setUp() {
    
        antiderivative = Integral.integrate("e^(x)");
    
    }
    
    @Test
    public void testExpE() {
        
        assertEquals("e^(x)", antiderivative);
        
    }
    
}
