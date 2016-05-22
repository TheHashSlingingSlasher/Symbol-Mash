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
public class TestCsc {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = new Integral("csc(x)").antiderivative;
        
    }
    
    @Test
    public void testCsc() {
        
        assertEquals("ln(csc(x)-cot(x)", antiderivative);
        
    }
    
}
