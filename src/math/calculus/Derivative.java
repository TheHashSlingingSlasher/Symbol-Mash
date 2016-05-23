package math.calculus;

// Java libraries
import util.datastructures.parsetree.Token;
import util.datastructures.parsetree.ParseTree;
import java.util.HashSet;

public class Derivative {
    
    // Fields
    public String fPrime;
    public static HashSet<String> elementaries = fill_elementaries();
    
    /**
     * Constructor of the Derivative class
     * 
     */
    public Derivative(){
        
       
             
    }
    
    public Derivative(String function) {
        
        ParseTree tree = new ParseTree(function);
        tree.fill_tree(function);
        fPrime = differentiate(tree);
        
    }
    
    public String differentiate(ParseTree tree) {
        
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
            elementaries.contains(f.getOperator())){
            return derive(f.getOperator(),
                          get_argument(f.getRValue()), "x");
        }
        // Recursive Case: f is a sum, difference, product or quotient of       
        ParseTree left_sub = new ParseTree(f.getLValue());
        ParseTree right_sub = new ParseTree(f.getRValue());
        return temp(f.getOperator(),left_sub,right_sub);
        
    }
    
    public String temp(String operator, ParseTree left, ParseTree right) {
        
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
     * Fills up a HashSet of type String with all of the known elementary 
     * functions to be used in parsing the expression
     * @return a HashSet containing all the known functions
     */
    public static HashSet fill_elementaries() {
        
        HashSet<String> elementaries = new HashSet();
        elementaries.add("sin");
        elementaries.add("cos");
        elementaries.add("tan");
        elementaries.add("csc");
        elementaries.add("sec");
        elementaries.add("cot");
        elementaries.add("arcsin");
        elementaries.add("arccos");
        elementaries.add("arctan");
        elementaries.add("sinh");
        elementaries.add("cosh");
        elementaries.add("ln");
        elementaries.add("log");
        elementaries.add("^");
        
        return elementaries;
        
    }
    
    /**
     * Serves as an interface to retrieve the derivative of a given function
     * with a given argument
     * @param function The type of elementary function
     * @param x        The only parameter of single argument functions
     * @param y        Additional parameter for two argument functions
     * @return         A String representing the derivative
     */
    public String derive(String function, String x, String y){
        
        switch(function){
            case "sin":
                return sin(x);
            case "cos":
                return cos(x);
            case "tan":
                return tan(x);
            case "csc":
                return csc(x);
            case "sec":
                return sec(x);
            case "cot":
                return cot(x);
            case "arcsin":
                return arcsin(x);
            case "arccos":
                return arccos(x);
            case "arctan":
                return arctan(x);
            case "e^x":
                return exp_e(x);
            case "a^x":
                return exp_a(x,y);
            case "x^n":
                return power(x,y);
            case "ln":
                return ln(x);
            case "log":
                return log(x,y);
            case "constant":
                return constant(x);
            case "single":
                return single(x);
        }
        
        return "FAILURE";
        
    }
    
    /**
     * Computes the derivative of a constant c
     * @param c The constant to differentiate
     * @return  A String containing a single zero
     */
    public String constant(String c){
    
        return "0";
    
    }
    
    /**
     * Computes the derivative of a single variable x
     * @param x The variable x to differentiate
     * @return A string containing a 1
     */
    public String single(String x){
        
        return "1";
        
    }
    
    /**
     * Calculates the derivative of a power function x^n
     * @param base     The base x
     * @param exponent The exponent n
     * @return         A String containing n(x)^(n-1)
     */
    public String power(String exponent,String base){
        
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
     * Calculates the derivative of an exponential function a^x
     * @param base     The base a
     * @param exponent The exponent x
     * @return         The String containing a^x ln(a)
     */
    public String exp_a(String base, String exponent){
     
        return base + "^(" +  exponent +")" + "ln(" + base + ")";
        
    }
    
    /**
     * Calculates the derivative of an exponential function e^x
     * @param exponent The exponent x
     * @return         The String containing e^x
     */
    public String exp_e(String exponent){
        
        return "e^(" + exponent +")";
   
    }
    
    /**
     * Calculates the derivative of the natural logarithmic function ln(x)
     * @param x The parameter x of the function ln(x)
     * @return  String containing 1/x
     */
    public String ln(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){         
            return "1/(" + x + ")";
        }
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return differentiate(chain) + "1/(" + x + ")";
        }
        
    }
    
    /**
     * Calculates the derivative of a logarithmic function with base a
     * @param base The base of the logarithm
     * @param x    The parameter x of the function log_a(x)
     * @return     The String containing 1/(x ln(a))
     */
    public String log(String base, String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "1/(" + x + "ln(" + base + "))";
        }
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "1/(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the sine function
     * @param x The parameter x of the function sin(x)
     * @return  A String containing cos(x)
     */
    public String sin(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "cos(" + x + ")";
        }
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return  "cos(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the cosine function
     * @param x The parameter x of the function cos(x)
     * @return  A string containing -sin(x)
     */
    public String cos(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "-sin(" + x + ")";
        }
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "-sin(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the tangent function
     * @param x The parameter x of the function tan(x)
     * @return  A String containing sec^2(x)
     */
    public String tan(String x){
        
        // If the function is elementray, return the known derivative
        if(x.equals("x")){
            return "sec^2(" + x + ")";
        }
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "sec^2(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the cosecant function
     * @param x The parameter x of the function csc(x)
     * @return 
     */
    public String csc(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "-csc(" + x + ")" + "cot(" + x + ")";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "-csc(" + x + ")" + "cot(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the secant function
     * @param x The parameter x of the function sec(x)
     * @return  A String containing sec(x)tan(x)
     */
    public String sec(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "sec(" + x + ")" + "tan(" + x + ")";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "sec(" + x + ")" + "tan(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the cotangent function
     * @param x The parameter x of the function cot(x)
     * @return  A String containing -csc^2(x)
     */
    public String cot(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "-csc^2(" + x + ")";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "-csc^2(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the arcsine function
     * @param x The parameter x of the function arcsin(x)
     * @return  The String 1/(1-x^(2))^(1/2)
     */
    public String arcsin(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "1/(1-" + x + "^(2))^(1/2)";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "1/(1-" + x + "^(2))^(1/2)" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the arccosine function
     * @param x The parameter of the function arccos(x)
     * @return  The String -1/(1+x^(2))^(1/2)
     */
    public String arccos(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
           return "-1/(1+" + x + "^(2))^(1/2)"; 
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "-1/(1+" + x + "^(2))^(1/2)" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the arctangent function
     * @param x The parameter of the function arctan(x)
     * @return  The String 1/(1+x^(2))
     */
    public String arctan(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "1/(1+(" + x + ")^(2))";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "1/(1 + (" + x + ")^(2))" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculates the derivative of the sinh(x) function
     * @param x The parameter of the function sinh(x)
     * @return The string cosh(x)
     */
    public String sinh(String x){
       
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "cosh(" + x + ")";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "cosh(" + x + ")" + differentiate(chain);
        }
        
    }
    
    /**
     * Calculate the derivative of the cosh(x) function
     * @param x The parameter x of cosh(x)
     * @return  The string sinh(x)
     */
    public String cosh(String x){
        
        // If the function is elementary, return the known derivative
        if(x.equals("x")){
            return "sinh(" + x + ")";
        }
        
        // Else, apply the chain rule
        else{
            ParseTree chain = new ParseTree(x);
            return "sinh(" + x + ")" + differentiate(chain);
        }
    }
    
    
    /**
     * Calculates the derivative of a sum
     * @param f The function f(x)
     * @param g The function g(x)
     * @return d/dx(f + g) = d/dx(f) + d/dx(g)
     */
    public String sum(String f, String g){
        
        return f + " + " + g;
        
    }
    
    /**
     * Calculates the derivative of a difference
     * @param f The function f(x)
     * @param g The function g(x)
     * @return d/dx(f - g) = d/dx(f) - d/dx(g)
     */
    public String difference(String f, String g){
        
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
    public String product(String f, String f_prime, String g, String g_prime){
        
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
    public String quotient(String f, String f_prime, String g, String g_prime){
        
        return  "(" + g + "(" + f_prime + ")" + " - "
              + f + "(" + g_prime + "))/(" + g + ")^(2)";
        
    }
    
    /**
     * Takes a function and retrieves it's argument within its parentheses
     * @param f The function f(x)
     * @return the argument x of f(x), May be another function entirely
     */
    public String get_argument(String f){
        
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
    public boolean is_elementary(String f){
        
        return get_argument(f).equals("x");
        
    }
    
} // End of Derivative
