package integral.directantiderivative;

// Java Libaries
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
// Symbol-Mash Libraries
import math.calculus.Integral;

/**
 *
 * @author alec
 */
public class TestConstant {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = Integral.integrate("2");
        
    }
    
    @Test
    public void testConstant() {
        
        assertEquals("2x", antiderivative);
        
    }
    
}
