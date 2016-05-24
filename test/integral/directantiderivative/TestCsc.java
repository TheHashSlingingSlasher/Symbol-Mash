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
public class TestCsc {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = Integral.integrate("csc(x)");
        
    }
    
    @Test
    public void testCsc() {
        
        assertEquals("ln(csc(x)-cot(x)", antiderivative);
        
    }
    
}
