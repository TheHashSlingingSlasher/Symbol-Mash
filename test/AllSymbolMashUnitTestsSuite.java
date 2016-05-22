// Java libraries
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
// Symbol-Mash libraries
import derivative.DerivativeTestSuite;
import integral.IntegralTestSuite;

/**
 *
 * @author alec
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DerivativeTestSuite.class, IntegralTestSuite.class})
public class AllSymbolMashUnitTestsSuite {

    
    
}
