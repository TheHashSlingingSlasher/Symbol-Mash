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
public class TestSec {
    
    String antiderivative;
    
    @Before
    public void setUp() {
    
        antiderivative = new Integral("sec(x)").antiderivative;
    
    }
    
    @Test
    public void testSec() {
        
        assertEquals("ln(sec(x)+tan(x))", antiderivative);
        
    }
    
}
