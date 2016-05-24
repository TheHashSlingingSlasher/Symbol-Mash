package derivative;

import derivative.basicnoncomposite.*;
import derivative.combinednoncomposite.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author alec
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({NonCompositeDerivativeSuite.class, 
                     CombinedNonCompositeDerivativeSuite.class})
public class DerivativeTestSuite {

    
}
