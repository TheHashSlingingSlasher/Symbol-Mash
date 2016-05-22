package directantiderivative;

// Java Libaries
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
// Symbol-Mash Libraries
import calculus.Integral;

/**
 *
 * @author alec
 */
public class TestConstant {
    
    String antiderivative;
    
    @Before
    public void setUp() {
        
        antiderivative = new Integral("2").antiderivative;
        
    }
    
    @Test
    public void testConstant() {
        
        assertEquals("2x", antiderivative);
        
    }
    
}
