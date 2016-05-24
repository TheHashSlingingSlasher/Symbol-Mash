package math.calculus;

// Symbol-Mash Libraries
import util.datastructures.mathfunctioncache.MathFunctionCache;
import util.datastructures.parsetree.Token;
import util.datastructures.parsetree.ParseTree;

/**
 *
 * @author Alec Farfan
 */
public class Derivative {
    
    public static String differentiate(String function) {
        
        ParseTree tree = new ParseTree(function);
        return differentiate(tree);
        
    }
    
    private static String differentiate(ParseTree tree) {
        
        Token f = tree.get_root_data();
        // Base Case 1: f is a single variable or constant
        if(f.getLValue() == null&& f.getOperator() == null){
            if(f.getRValue() != null && f.getRValue().equals("x")){
                return derive("single","x","x");
            }
            else if(f.getRValue() != null && tree.isConstant(f.getRValue())){
                return derive("constant","x","x");
            }
            
        }
        // Base Case 2: f is a known function
        else if(f.getOperator().equals("x^n")){
            return derive(f.getOperator(),f.getRValue(), f.getLValue());    
        }
        else if(f.getLValue() == null &&
            MathFunctionCache.contains(f.getOperator())){
            return derive(f.getOperator(),
                          getArgument(f.getRValue()), "x");
        }
        // Recursive Case: f is a sum, difference, product or quotient of       
        ParseTree left_sub = new ParseTree(f.getLValue());
        ParseTree right_sub = new ParseTree(f.getRValue());
        return temp(f.getOperator(),left_sub,right_sub);
        
    }
    
    private static String temp(String operator, ParseTree left, ParseTree right) {
        
            switch(operator){
                case "+":
                    return left.enclose(sum(differentiate(left),
                                       differentiate(right)));
                case "-":
                    return right.enclose(difference(differentiate(left),
                                         differentiate(right)));
                default:
                    return "***";
            }
            //                case "*":
//                    f_prime.f_prime = enclose(f_prime.product(
//                                      left_sub.f_prime.f_prime,
//                                      left_sub.get_root_data().get_original(),
//                                      right_sub.f_prime.f_prime,
//                                      right_sub.get_root_data().get_original()));
//                    break;
//                case "/":
//                    f_prime.f_prime = enclose(f_prime.quotient(
//                                      left_sub.get_root_data().get_original(),
//                                      left_sub.f_prime.f_prime,
//                                      right_sub.get_root_data().get_original(),
//                                      right_sub.f_prime.f_prime));
//                    
//                    break;
    }
    
    /**
     * Serves as an interface to retrieve the derivative of a given function
     * with a given argument
     * @param function The type of elementary function
     * @param x        The only parameter of single argument functions
     * @param y        Additional parameter for two argument functions
     * @return         A String representing the derivative
     */
    private static String derive(String function, String x, String y){
        
        if(isComposite(x))
            return MathFunctionCache.table.get(function).getKey();
        else{
            return MathFunctionCache.table.get(function).getKey() +
                   Derivative.differentiate(x);
        }
        
    }
    
    /**
     * Calculates the derivative of a power function x^n
     * @param base     The base x
     * @param exponent The exponent n
     * @return         A String containing n(x)^(n-1)
     */
    private static String power(String exponent,String base){
        
        System.out.println(base + " * " + exponent);
        
        int new_exponent = Integer.parseInt(exponent);
        if(base.equals("x")){
            return new_exponent + base + "^(" + (new_exponent-1) + ")";
        }
        else{
            ParseTree chain = new ParseTree(base);
            return new_exponent + base + "^(" + (new_exponent-1) + ")" + differentiate(chain);
        }
            
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
    
    /**
     * Calculates the derivative of a product
     * @param f       // The function f(x)
     * @param f_prime // The function f'(x)
     * @param g       // The function g(x)
     * @param g_prime // The function g'(x)
     * @return        // d/dx(fg) = f(x)g'(x) + g(x)f'(x)
     */
    private static String product(String f, String f_prime, String g, String g_prime){
        
        return  f + "(" + g_prime + ")" + " + "
              + g + "(" + f_prime + ")";
        
    }
  
    /**
     * Calculates the derivative of a quotient
     * @param f       // The function f(x)
     * @param f_prime // The function f'(x)
     * @param g       // The function g(x)
     * @param g_prime // The function g'(x)
     * @return        // d/dx(f/g) = (g(x)f'(x) - f(x)g'(x))/(g(x))^(2)
     */
    private static String quotient(String f, String f_prime, String g, String g_prime){
        
        return  "(" + g + "(" + f_prime + ")" + " - "
              + f + "(" + g_prime + "))/(" + g + ")^(2)";
        
    }
    
    /**
     * Takes a function and retrieves it's argument within its parentheses
     * @param f The function f(x)
     * @return the argument x of f(x), May be another function entirely
     */
    private static String getArgument(String f){
        
        if(f.equals("x"))
            return "x";
        
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
     * Determines whether a function is elementary or composite 
     * @param f The function f(x)
     * @return True if the function is elementary, false if composite
     */
    private static boolean isComposite(String f){
        
        return getArgument(f).equals("x");
        
    }
    
}
