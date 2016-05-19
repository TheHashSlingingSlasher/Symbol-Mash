package Calculus.Differential;

// Java libraries
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
// Custom libraries
import Data_Structures.Tree.Binary_Tree;

public class Parse_Tree extends Binary_Tree<Token>{

    public Derivative f_prime;
    public static LookUpTable lookUpTable;
    
    public Parse_Tree(String f){
        super(new Token());
        f_prime = new Derivative();
        lookUpTable = new LookUpTable();
        f_prime.f_prime = fill_tree(f);
        
    }
    
    /**
     * Recursively parses the string passed into the parameter f until the form
     * of the string f is in one of the two base cases
     * @param f
     * @return 
     */
    public String fill_tree(String f){
        
        // Parse the function f(x) and create the Token object for current node
        Token this_node = parse(f);
        
        // Base Case 1: f is a single variable or constant
        if(this_node.get_operand_1() == null&&this_node.get_operator() == null){
            this.set_tree(new Token(f),null,null);
            if(this_node.get_operand_2().equals("x")){
                f_prime.f_prime = f_prime.derive("single","x","x");
            }
            else if(isConstant(this_node.get_operand_2())){
                f_prime.f_prime = f_prime.derive("constant","x","x");
            }
            
        }
        
        // Base Case 2: f is a known function
        else if(this_node.get_operator().equals("x^n")){
            this.set_tree(new Token(f),null,null);
            f_prime.f_prime = f_prime.derive(this_node.get_operator(),this_node.get_operand_2(),
                          this_node.get_operand_1());    
        }
        else if(this_node.get_operand_1() == null &&
            lookUpTable.contains(this_node.get_operator())){
            this.set_tree(new Token(f),null,null);
            f_prime.f_prime = f_prime.derive(this_node.get_operator(),
                          f_prime.get_argument(this_node.get_operand_2()), "x");
        }
        
        // Recursive Case: f is a sum, difference, product or quotient of functions
        else if(!(this_node.get_operand_1() == null && this_node.get_operator() == null)){
            
            Parse_Tree left_sub = new Parse_Tree(this_node.get_operand_1());
            Parse_Tree right_sub = new Parse_Tree(this_node.get_operand_2());
            this.set_tree(new Token(f),left_sub,right_sub);
            
            switch(this_node.get_operator()){
                case "+":
                    f_prime.f_prime = enclose(f_prime.sum(left_sub.f_prime.f_prime,
                                      right_sub.f_prime.f_prime));
                    break;
                case "-":
                    f_prime.f_prime = enclose(f_prime.difference(
                           left_sub.f_prime.f_prime,right_sub.f_prime.f_prime));
                    break;
                case "*":
                    f_prime.f_prime = enclose(f_prime.product(
                                      left_sub.f_prime.f_prime,
                                      left_sub.get_root_data().get_original(),
                                      right_sub.f_prime.f_prime,
                                      right_sub.get_root_data().get_original()));
                    break;
                case "/":
                    f_prime.f_prime = enclose(f_prime.quotient(
                                      left_sub.get_root_data().get_original(),
                                      left_sub.f_prime.f_prime,
                                      right_sub.get_root_data().get_original(),
                                      right_sub.f_prime.f_prime));
                    
                    break;
            }
            
        }

        return f_prime.f_prime;
    
    }
    
    /**
     * @breif Takes a string representing a mathematical expression and attempts
     *        to reduce it to either a constant, single variable, or a single
     *        function. What is meant by "single function" is a function that is
     *        not a sum, difference, product, or quotient of functions. A
     *        composite function is considered a single function.
     * @param expression String representing a mathematical expression
     * @return A Token object with the reduced expression
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
     * 
     * @param f
     * @return 
     */
    public String clean(String f){
        
        String cleaned_string = f;
        
        if(f != null && !f.contains("^") && !f.equals("")){
            HashMap<Integer,Integer> parentheses = get_parentheses(f);
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
     * Finds all ordered pairs representing the indeces of an opening parentheses
     * and its corresponding closing parentheses.
     * @param f The string representing the function f(x)
     * @return A HashMap<Integer,Integer> of the indeces of parentheses pairs
     */
    public HashMap get_parentheses(String f){
        
        HashMap<Integer,Integer> parentheses = new HashMap();
        
        if(f != null && num_parentheses(f) % 2 == 0){
            for(int i=0;i<f.length()-1;i++){
                if(f.charAt(i)=='('){
                    int j = i + 1;
                    int count = 1;
                    while(count!=0){
                        if(f.charAt(j)=='('){
                            count++;
                        }
                        if(f.charAt(j)==')'){
                            count--;
                        }
                        j++;
                    }
                    parentheses.put(i,j-1);
                }
            }
        }
        
        return parentheses;
        
    }
    
    /**
     * 
     * @param f
     * @return 
     */
    public static int num_parentheses(String f){
        
        int count = 0;
        for(int i=0;i<f.length();i++){
            if(f.charAt(i) == '(' || f.charAt(i) == ')')
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
        HashMap<Integer,Integer> parentheses = get_parentheses(expression);
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
    
    public Token makeLogOrTrig(String f, String sub_function){
        
        return new Token(null, f, sub_function);
        
    }
    
    public boolean isExpOrPower(String str, int index){
        
        return str.charAt(index) == '^';
        
    }
    
    public Token makeExpOrPower(String f, String sub_function, int i){
        
        String left = "";
        int end = i - 1;
        int start = i - 1;
        int j = 0;
        if(start >= 0 && sub_function.charAt(start)==')'){
            start--;
            int count = 1;
            while(count > 0){
                if(sub_function.charAt(start) == ')'){
                    count++;
                }
                if(sub_function.charAt(start) == '('){
                    count--;
                }
                start--;
            }
            left = sub_function.substring(start+2, end);
            j = i + 1;
        }
        else{
            start = i;
            end = i;
            while(start > 0 && Character.isLetter(sub_function.charAt(start))){
                start--;
            }
            left = sub_function.substring(start,end);
            j = i + 1;
        }
        while(f.charAt(j) != ')'){
            sub_function += f.charAt(j);
            j++;
        }
        sub_function += f.charAt(j);
        String right = "";
        start = i + 2;
        end = i + 2;
        while(sub_function.charAt(end) != ')'){
            end++;
        }
        right = sub_function.substring(start,end);
        return new Token(left, right, "x^n");
        
    }
    
    /**
     * 
     * @param f
     * @return 
     */
    public String simplify(String f){
        
        if(f.length() > 1){
             for(int i = 0; i < f.length() ; i++ ){
                if(f.charAt(i) == '0'){
                    int index = i+1;
                    while(!(f.charAt(index)==')' || f.charAt(index)=='+' ||
                                                  f.charAt(index)=='-') ||
                                                  isConstant("" + f.charAt(index))){
                        index++;
                    }
                    String a = f.substring(0,i);
                    String b = f.substring(index+1,f.length());
                    f = a + b;
                }
                if(f.length() > i + 1){
                    if(f.charAt(i) == '1' && f.charAt(i+1) == '('){
                    int index = i+1;
                    String a = f.substring(0,i);
                    String b = f.substring(i + 1,f.length());
                    f = a + b;
                    }
                } 
            }   
        }
         
        return f;
        
    }
    
    /**
     * Encloses a given function in a set of parentheses
     * @param f The function f(x)
     * @return  The original string with parentheses added to the beginning and
     *          to the end.
     */
    public String enclose(String f){
        
        return "(" + f + ")";
        
    }
    
    /**
     * Prints the nodes of the tree in the order determined by the iterator 
     * passed into the parameter order. The iterator my contain a traversal
     * in preorder, postorder, inorder, or level order
     * @param order 
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
