package math.calculus;

// Symbol-Mash Libraries
import util.datastructures.mathfunctioncache.MathFunctionCache;
import util.datastructures.parsetree.ParseTree;
import util.datastructures.parsetree.Token;

/**
 *
 * @author Alec Farfan
 */
public class Integral {
    
    public static String integrate(String function){
   
        ParseTree tree = new ParseTree(function);
        return integrate(tree);
        
    }
    
    private static String integrate(ParseTree tree) {
        
        Token f = tree.get_root_data();
        // Base Case 1: f is a single variable or constant
        if(f.getLValue() == null&& f.getOperator() == null){
            if(f.getRValue() != null && f.getRValue().equals("x")){
                return send("single","x","x");
            }
            else if(f.getRValue() != null && tree.isConstant(f.getRValue())){
                return send("constant", "x", f.getRValue());
            }
            
        }
        // Base Case 2: f is a known function
        else if(f.getOperator().equals("x^n")){
            return send(f.getOperator(),f.getRValue(), f.getLValue());    
        }
        else if(f.getLValue() == null &&
                MathFunctionCache.contains(f.getOperator())){
            return send(f.getOperator(),
                          get_argument(f.getRValue()), "x");
        }
        // Recursive Case: f is a sum, difference, product or quotient of       
        ParseTree left_sub = new ParseTree(f.getLValue());
        ParseTree right_sub = new ParseTree(f.getRValue());
        return "";//temp(f.getOperator(),left_sub,right_sub);
        
    }
    
    /**
     * Serves as an interface to retrieve the derivative of a given function
     * with a given argument
     * @param function The type of elementary function
     * @param x        The only parameter of single argument functions
     * @param y        Additional parameter for two argument functions
     * @return         A String representing the derivative
     */
    private static String send(String function, String x, String y){
        
        return MathFunctionCache.table.get(function).getValue();
        
    }
    
    /**
     * Takes a function and retrieves it's argument within its parentheses
     * @param f The function f(x)
     * @return the argument x of f(x), May be another function entirely
     */
    private static String get_argument(String f){
        
        int i = 0; // Index of first parenthese
        int j = 0; // Index of corresponding ending parenthese
        
        // Find the first parentheses
        while(f.charAt(i) != '('){
            i++;
        }
        
        // Find the ending parenthese corresponding to the first
        int count = 1;
        j = i + 1;
        while(count != 0){
            if(f.charAt(j) == ')'){
                count--;
            }
            else if(f.charAt(j) == '('){
                count++;
            }
            j++;
        }
        
        // Strip out the argument and return it
        return f.substring(i+1,j-1);
        
    }
    
    /**
     * Calculates the derivative of a power function x^n
     * @param base     The base x
     * @param exponent The exponent n
     * @return         A String containing n(x)^(n-1)
     */
    private static String power(String exponent,String base){
        
        int new_exponent = Integer.parseInt(exponent);
        
        //if(base.equals("x"))
            return new_exponent + base + "^(" + (new_exponent-1) + ")";
            
    }
    
    /**
     * Calculates the derivative of a sum
     * @param f The function f(x)
     * @param g The function g(x)
     * @return d/dx(f + g) = d/dx(f) + d/dx(g)
     */
    private static String sum(String f, String g){
        
        return f + " + " + g;
        
    }
    
    /**
     * Calculates the derivative of a difference
     * @param f The function f(x)
     * @param g The function g(x)
     * @return d/dx(f - g) = d/dx(f) - d/dx(g)
     */
    private static String difference(String f, String g){
        
        return f + " - " + g;
        
    }
    
}
