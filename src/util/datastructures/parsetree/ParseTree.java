package util.datastructures.parsetree;

// Java libraries
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
// Custom libraries
import util.datastructures.tree.BinaryTree;

public class ParseTree extends BinaryTree<Token>{

    public static LookUpTable lookUpTable = new LookUpTable();;
    
    public ParseTree(String f){
        super(new Token());
          fill_tree(f);
    }
    
    /**
     * Recursively parses the string passed into the parameter f until the form
     * of the string f is in one of the two base cases
     * @param f
     */
    public void fill_tree(String f){

        // Parse the function f(x) and create the Token object for current node
        Token this_node = parse(f);
        // Base Case 1: f is a single variable or constant
        if(this_node.get_operand_1() == null&&this_node.get_operator() == null){
            this.set_tree(new Token(f,this_node.get_operand_1(),
            this_node.get_operand_2(),this_node.get_operator()),null,null);
            
        }
        // Base Case 2: f is a known function
        else if(this_node.get_operator().equals("x^n")){
            this.set_tree(new Token(f,this_node.get_operand_1(),
                 this_node.get_operand_2(),this_node.get_operator()),null,null);
  
        }
        else if(this_node.get_operand_1() == null &&
            lookUpTable.contains(this_node.get_operator())){
            this.set_tree(new Token(f,this_node.get_operand_1(),
                    this_node.get_operand_2(),this_node.get_operator()),null,null);
        }
        // Recursive Case: f is a sum, difference, product or quotient of functions
        else if(!(this_node.get_operand_1() == null && this_node.get_operator() 
                == null)){
            
            ParseTree left_sub = new ParseTree(this_node.get_operand_1());
            ParseTree right_sub = new ParseTree(this_node.get_operand_2());
            this.set_tree(new Token(f,this_node.get_operand_1(),
               this_node.get_operand_2(),this_node.get_operator()),
                    left_sub,right_sub);
            
            
        }
    }
    
    /**
     * @breif Takes a string representing a mathematical expression and attempts
     *        to reduce it to either a constant, single variable, or a single
     *        function. What is meant by "single function" is a function that is
     *        not a sum, difference, product, or quotient of functions. A
     *        composite function is considered a single function.
     * @param expression String representing a mathematical expression.
     * @return A Token object with the reduced expression.
     */
    public Token parse(String expression){
        
        // Remove redundant spaces, parentheses, ect.
        expression = clean(expression);
        // Check if the expression is a sum, difference, product, or quotient
        if(isReducible(expression))
            return expressionToken(expression);
        // Check if the expression is a single constant or variable
        if(isConstant(expression) || isSingleVar(expression))
            return singleTermToken(expression);
        // If above two cases are false, expression must be a single function
        else
            return singleFunctionToken(expression);
             
    }
    
    /**
     * @breif Removes redundant characters from the expression.
     *        WARNING: Code unfinished, will return after clean-up work
     * @param expression A string representing a mathematical expression
     * @return A string of the above expression without redundant characters or
     *         spaces
     */
    public String clean(String expression){
        
        String cleaned_string = expression;
        
        if(expression != null && !expression.contains("^") && 
          !expression.equals("")){
            HashMap<Integer,Integer> parentheses = getParentheses(expression);
            while(cleaned_string.charAt(0) == '(' && parentheses.size() > 0 &&
              cleaned_string.charAt(cleaned_string.length()-1) == ')'){
                if(parentheses.get(0) == cleaned_string.length()-1){
                    cleaned_string = cleaned_string.substring(1,
                                 cleaned_string.length()-1);
                }
                else{
                    break;
                }
            }
            while(cleaned_string.charAt(0) == ' '){
                cleaned_string = cleaned_string.substring(1,
                                 cleaned_string.length());
            }
            while(cleaned_string.charAt(cleaned_string.length()-1) == ' '){
                cleaned_string = cleaned_string.substring(0,
                                 cleaned_string.length()-1);
            }
        }
        
        return cleaned_string;
        
    }
    
    /**
     * @brief Creates a HashMap filled with pairs of indeces corresponding to
     *        an opening parentheses and its associated closing parentheses.
     * @param expression The string representing a mathematical expression
     * @return A HashMap containing the indeces of parentheses pairs
     */
    public HashMap getParentheses(String expression){
        
        HashMap<Integer,Integer> parentheses = new HashMap();
        
        if(expression != null && numParentheses(expression) % 2 == 0){
            for(int i = 0; i < expression.length() - 1; i++){
                if(expression.charAt(i) == '('){
                    int j = i + 1;
                    int count = 1;
                    while(count!=0){
                        if(expression.charAt(j)=='(')
                            count++;
                        if(expression.charAt(j)==')')
                            count--;
                        j++;
                    }
                    parentheses.put(i,j-1);
                }
            }
        }
        
        return parentheses;
        
    }
    
    /**
     * @breif Counts the number of parentheses in an expression
     * @param expression A string representing a mathematical expression
     * @return The number of parentheses in the expression
     */
    public static int numParentheses(String expression){
        
        int count = 0;
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '(' || expression.charAt(i) == ')')
                count++;
        }
        
        return count;
        
    }
    
    /**
     * @breif Determines whether or not the expression is a sum, difference,
     *        product, or quotient of functions. If so we call it reducible.
     * @param expression A string representing a mathematical expression
     * @return True if the function is reducible, false otherwise
     */
    public boolean isReducible(String expression){
        
        // Check if expression has explicit or implicit arithmetic operators
        return hasExplicitOperator(expression) || 
               hasConstantMultiple(expression) ||
               hasParentheseMultiple(expression);
        
    }
    
    /**
     * @breif Determines whether or not the expression explicitly contains an
     *        arithmetic operator.
     * @param expression A string representing a mathematical function
     * @return True if expression contains an operator outside of any 
     *         parentheses, false otherwise.
     */
    public boolean hasExplicitOperator(String expression){
        
        for(int i = 0; i < expression.length(); i++){
            if(isArithmeticOperator(expression, i) && 
               isOutsideParentheses(expression, i))
                    return true;
      
        }
    
        return false;
        
    }
    
    /**
     * @breif Determines whether a character at some index of a string
     *        representing a mathematical expression is an arithmetic operator
     * @param expression A string representing a mathematical expression
     * @param index The index of the string to examine
     * @return True if the character is an arithmetic operator, false otherwise
     */
    public boolean isArithmeticOperator(String expression, int index) {
        
        return expression.charAt(index) == '+' || 
               expression.charAt(index) == '-' || 
               expression.charAt(index) == '/';
        
    }
    
    /**
     * @breif Determines whether a given index is outside any parentheses in
     *        a mathematical expression
     * @param expression A string representing a mathematical expression
     * @param index The index of the string to examine
     * @return True if index is outside of parentheses, false otherwise
     */
    public boolean isOutsideParentheses(String expression, int index) {
        
        // Retrieve the set of indeces of corresponding parentheses
        HashMap<Integer,Integer> parentheses = getParentheses(expression);
        // Check index against the set of indeces to see if it is inside any
        for(Map.Entry<Integer,Integer> entry : parentheses.entrySet()){
            // If the index is inside parentheses return false
            if(index > entry.getKey() && index < entry.getValue())
                return false;
        }
        
        return true;
        
    }
    
    /**
     * @breif Determines whether or not an expression possesses a constant
     *        multiplier.
     * @param expression A String representing a mathematical expression.
     * @return True if expression contains constant multiplier, false otherwise.
     */
    public boolean hasConstantMultiple(String expression){
        
        if(expression.length() > 1)
            return Character.isDigit(expression.charAt(0)) && 
                   expression.charAt(1) != '^';
        else
            return false;
        
    }
    
    /**
     * @breif Determines whether or not an expression possesses an implicit
     *        multiplication operator ie sin(x)cos(x).
     * @param expression A String representing a mathematical expression.
     * @return True if expression contains implicit multiplication operator
     *         false otherwise.
     */
    public boolean hasParentheseMultiple(String expression){
        
        for(int i = 0; i < expression.length() - 1; i++){
            if(expression.charAt(i) == ')' && expression.charAt(i + 1) == '(' || 
               expression.charAt(i) == ')' && 
              (expression.charAt(i + 1) != ')' && 
               expression.charAt(i + 1) != '^'))
                return true;
        }
        
        return false;
        
    }
    
    /**
     * @breif Creates a Token object by splitting the expression into two 
     *        smaller expressions          
     * @param expression A string representing a mathematical expression
     * @return A Token object that stores the smaller expressions and the 
     *         operator that connects them
     */
    public Token expressionToken(String expression){

        if(hasExplicitOperator(expression))
            return explicitOperatorSplice(expression);

        if(hasConstantMultiple(expression))
            return constMultSplice(expression);

        else
            return parentheseMultSplice(expression);
        
    }
    
    /**
     * @breif Creates a Token object by splitting the expression into two 
     *        smaller expressions by finding a character representing an
     *        arithmetic operator that joins the two smaller expressions.
     * @param expression A string representing a mathematical expression
     * @return A Token object that stores the smaller expressions and the 
     *         operator that connects them
     */
    public Token explicitOperatorSplice(String expression){

        for(int i = 0; i < expression.length(); i++){
            if(isArithmeticOperator(expression, i) &&
               isOutsideParentheses(expression, i)){

                String lValue = expression.substring(0, i);
                String rValue = expression.substring(i + 1, expression.length());
                String operator = Character.toString(expression.charAt(i));
                return new Token(lValue, rValue, operator);

            }
        }

        return new Token(); // Program never reaches this point

    }
    
    /**
     * @breif Creates a Token object by splitting the expression into two
     *        smaller expression where one of the expressions is a constant
     *        multiplier
     * @param expression A string representing a mathematical expression
     * @return A Token object that stores the constant multiplier, other 
     *         expression, and a multiplication operator
     */
    public Token constMultSplice(String expression){
        
        int i = 0;
        while(i < expression.length() && 
              Character.isDigit(expression.charAt(i)))
            i++;
        
        String lValue = expression.substring(0, i);
        String rValue = expression.substring(i, expression.length());
        
        return new Token(lValue, rValue, "*");
    
    }
    
    /**
     * @breif Creates a Token object by splitting the expression into two
     *        smaller expressions where the two smaller expressions are
     *        separated by an implicit multiplication operator ie sin(x)cos(x)
     * @param expression A string representing a mathematical expression
     * @return A Token object that stores the smaller expressions and a 
     *         multiplication operator
     */
    public Token parentheseMultSplice(String expression){
        
        int index = 0;
        
        for(int i = 0; i < expression.length() - 1; i++){
            if(expression.charAt(i) == ')' && expression.charAt(i + 1) == '(' || 
               expression.charAt(i) == ')' && (expression.charAt(i+1) != ')' && 
               expression.charAt(i+1) != '^')){
                index = i + 1;
                break; 
            }
        }
        
        String lValue = expression.substring(0, index);
        String rValue = expression.substring(index, expression.length());
        
        return new Token(lValue, rValue, "*"); 
        
    }
    
    /**
     * @brief Determines whether or not a given expression is a constant
     *        function
     * @param expression A string representing a mathematical expression
     * @return True if the expression is a constant function, false otherwise
     */
    public boolean isConstant(String expression){
        
        for(int i = 0; i < expression.length(); i++){
            if(!Character.isDigit(expression.charAt(i)))
                return false;
        }
        
        return true;
        
    }
    
    /**
     * @breif Determines whether or not a given expression is a single variable.
     * @param expression A string representing a mathematical expression
     * @return True if the expression is a single variable, false otherwise
     */
    public boolean isSingleVar(String expression){
        
        return expression.equals("x");
        
    }

    /**
     * @breif Creates a Token object representing a single term. What is meant
     *        by "single term" is either a constant function or single variable.
     * @param expression A string representing a mathematical expression
     * @return A Token object that stores the single term, but not a second
     *         expression or operator
     */
    public Token singleTermToken(String expression){
       
        return new Token(null, expression, null);
 
    }
    
    /**
     * @breif Creates a Token object representing a single function. What is 
     *        meant by "single function" is a function that is not a sum, 
     *        difference, product, or quotient of functions. A composite 
     *        function is considered a single function.
     * @param expression A string representing a mathematical expression
     * @return A Token object that stores the single function, but not a second
     *         expression or operator
     */
    public Token singleFunctionToken(String expression){
        
        for(int i = 0; i < expression.length(); i++){
            String sub_function = expression.substring(0, i + 1); 
            
            if(isLogOrTrig(sub_function))
                return makeLogOrTrig(expression, sub_function); 
            
            if(isExpOrPower(sub_function, i))
                return makeExpOrPower(expression, sub_function, i);
            
        }
        
        return new Token(); // Program never reaches this point
        
    }
    
    /**
     * @breif Determines whether the expression is a log or trig function.
     * @param expression A string representing a mathematical expression
     * @return True if the expression is a trig or log function, false otherwise
     */
    public boolean isLogOrTrig(String expression) {
        
        return lookUpTable.contains(expression);
        
    }
    
    /**
     * @breif Creates a Token object of either a log or trig function.
     * @param expression A string representing a mathematical expression.
     * @param expressionSubstring A substring of the above expression.
     * @return A Token object where the "operator" is a log or trig function.
     */
    public Token makeLogOrTrig(String expression, String expressionSubstring){
        
        return new Token(null, expression, expressionSubstring);
        
    }
    
    /**
     * @breif Determines whether the expression is an exponential or power
     *        function.
     * @param expression A string representing a mathematical expression
     * @param index The index to search for the exponential character '^'
     * @return 
     */
    public boolean isExpOrPower(String expression, int index){
        
        return expression.charAt(index) == '^';
        
    }
    
    /**
     * @breif Creates a Token object of either an exponential or power function.
     * @param expression A string representing a mathematical expression.
     * @param expressionSubstring A substring of the above expression.
     * @param index The index where the '^' character occurs in the expression.
     * @return A Token Object with one operand and either e^x or x^n as
     *         the operator
     */
    public Token makeExpOrPower(String expression, String expressionSubstring, 
                                int index){ 

        int start = findBaseStart(expressionSubstring, index - 1);
        int end = findBaseEnd(expressionSubstring, index);
        String base = findBase(expressionSubstring, start, end);
        
        expressionSubstring = injectArgument(expression, expressionSubstring, 
                                            index + 1);
        start = findExponentStart(index);
        end = findExponentEnd(expressionSubstring, start);
        String exponent = findExponent(expressionSubstring, start, end);
        
        String tag = base.equals("e") ? "e^x" : "x^n";
        
        return new Token(base, exponent, tag);
        
    }
    
    /**
     * @breif Finds the starting index of the base of either an exponential or
     *        power function.
     * @param expressionSubstring A substring of the above expression.
     * @param start The index to begin searching from.
     * @return The starting index of the base of the appropriate function.
     */
    public int findBaseStart(String expressionSubstring, int start) {
        
        if(start >= 0 && expressionSubstring.charAt(start)==')'){
            start--;
            int count = 1;
            while(count > 0){
                if(expressionSubstring.charAt(start) == ')')
                    count++;
                if(expressionSubstring.charAt(start) == '(')
                    count--;
                start--;
            }
            start += 2;
        }
        
        else{
            while(start > 0 && Character.isLetter(
                                         expressionSubstring.charAt(start)))
                start--;
        }
        
        return start;
        
    }
    
    /**
     * @breif Finds the ending index of the base of either an exponential or
     *        power function.
     * @param expressionSubstring A substring of the above expression.
     * @param end The index to begin searching from
     * @return The ending index of the base of the appropriate function.
     */
    public int findBaseEnd(String expressionSubstring, int end) {
        
        return (end >= 0 && expressionSubstring.charAt(end) == ')') ? end - 1 :
                                                                    end;
         
    }
    
    /**
     * @breif Retrieves the base of either an exponential or power function.
     * @param expressionSubstring A substring of the above expression.
     * @param start The start of the part of the string to splice.
     * @param end The end of the part of the string to splice.
     * @return A string representing the base of the appropriate function.
     */
    public String findBase(String expressionSubstring, int start, int end) {
        
        return expressionSubstring.substring(start, end);
        
    }
    
    /**
     * @breif Finds the starting index of the exponent of either an 
     *        exponential or power function.
     * @param index The index where the '^' character occurs.
     * @return The starting index of the exponent of the appropriate function.
     */
    public int findExponentStart(int index) {
        
        return index + 1;
        
    }
    
    /**
     * @breif Finds the ending index of the exponent of either an exponential or
     *        power function.
     * @param expressionSubstring A substring of the main expression.
     * @param index The index where the '^' character occurs.
     * @return The ending index of the exponent of the appropriate function.
     */    
    public int findExponentEnd(String expressionSubstring, int index) {
        
        while(expressionSubstring.charAt(index) != ')')
            index++;
        
        return index;
        
    }
    
    /**
     * @breif Retrieves the exponent of either an exponential or power function.
     * @param expressionSubstring A substring of the above expression.
     * @param start The start of the part of the string to splice.
     * @param end The end of the part of the string to splice.
     * @return A string representing the base of the appropriate function.
     */
    public String findExponent(String expressionSubstring, int start, int end) {
        
        return expressionSubstring.substring(start,end);
        
    }
    
    /**
     * @breif Concatenates the argument of the function found in the expression
     *        string to the expressionSubstring
     * @param expression A string representing a mathematical expression.
     * @param expressionSubstring A substring of the above expression.
     * @param index The index where the '(' character appears.
     * @return The expressionSubstring with the argument put into the parameter.
     */
    public String injectArgument(String expression, String expressionSubstring,
                                 int index){
        
        int j = index + 1;
        
        while(expression.charAt(j) != ')'){
            expressionSubstring += expression.charAt(j);
            j++;
        }
        expressionSubstring += expression.charAt(j);
        
        return expressionSubstring;
        
    }
    
    /**
     * @breif Simplifies an algebraic expression
     *        WARNING: Code incomplete, simplify is an involved process that
     *                 requires some thinking. Will return after clean-up is
     *                 finished.
     * @param expression A string representing a mathematical expression
     * @return A string containing the simplified expression
     */
    public String simplify(String expression){
        
        if(expression.length() > 1){
             for(int i = 0; i < expression.length(); i++ ){
                if(expression.charAt(i) == '0'){
                    int index = i + 1;
                    while(!(expression.charAt(index)== ')' || 
                            expression.charAt(index)=='+' ||
                            expression.charAt(index)=='-') ||                      
                            isConstant("" + expression.charAt(index))){
                        index++;
                    }
                    String lValue = expression.substring(0, i);
                    String rValue = expression.substring(index + 1,
                                    expression.length());
                    expression = lValue + rValue;
                }
                if(expression.length() > i + 1){
                    if(expression.charAt(i) == '1' && 
                       expression.charAt(i + 1) == '('){
                    int index = i + 1;
                    String lValue = expression.substring(0, i);
                    String rValue = expression.substring(i + 1, 
                                    expression.length());
                    expression = lValue + rValue;
                    }
                } 
            }   
        }
         
        return expression;
        
    }
    
    /**
     * @expression Encloses an expression in parentheses
     * @param expression A string representing a mathematical expression
     * @return  The given expression with parentheses enclosing it.
     */
    public String enclose(String expression){
        
        return "(" + expression + ")";
        
    }
    
    /**
     * @breif Prints the nodes of the tree given an iterator to that tree .
     * @param order An iterator pointing to the root of a tree.
     */
    @Override
    public void print(Iterator<Token> order){
        
        Iterator<Token> pointer = order;
        
        while(pointer.hasNext()){
            Token output = pointer.next();
            System.out.println(output.get_original());
        }
        
    }
    
}
