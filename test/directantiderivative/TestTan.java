package directantiderivative;

// Java Libraries
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
// Symbol-Mash Libraries
import calculus.Integral;

/**
 *
 * @author alec
 */
public class TestTan {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = new Integral("tan(x)").antiderivative;
        
    }
    
    @Test
    public void testTan() {
        
        assertEquals("ln(sec(x))", antiderivative);
        
    }
}
