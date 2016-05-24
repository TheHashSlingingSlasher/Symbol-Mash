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
public class TestArcsin {
    
    String antiderivative;
    
    @Before
    public void setUp() {
    
        antiderivative = Integral.integrate("arcsin(x)");
    
    }
    
    @Test
    public void testArcsin() {
        
        assertEquals("xarcsin(x)+(1-x^(2))^(1/2)", antiderivative);
        
    }
    
}
