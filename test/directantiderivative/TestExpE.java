package directantiderivative;

// Java Libraries
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
// Sybmol-Mash Libraries
import calculus.Integral;

/**
 *
 * @author alec
 */
public class TestExpE {
    
    String antiderivative;
    
    @Before
    public void setUp() {
    
        antiderivative = new Integral("e^(x)").antiderivative;
    
    }
    
    @Test
    public void testExpE() {
        
        assertEquals("e^(x)", antiderivative);
        
    }
    
}
