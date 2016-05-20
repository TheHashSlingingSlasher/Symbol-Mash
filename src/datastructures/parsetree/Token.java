package datastructures.parsetree;

import calculus.differential.*;

/**
 * Class for the Token objects which each node of the Parse_Tree will contain
 * @author Alec
 */
public class Token {
    
    // Data members
    private String original;  // Original String
    private String operand_1; // Left operand of expression
    private String operand_2; // Right operand of expression
    private String operator;  // Binary operator between the two operands
    
    /**
     * Constructor for the Token Class that sets each data member to null
     */
    public Token(){
        
        original = null;
        operand_1 = null;
        operand_2 = null;
        operator = null;
        
    }
    
    /**
     * Constructor for the Token Class that assigns the parameter f to the 
     * data member original
     * @param f The function f(x)
     */
    public Token(String f){
        
        original = f;
        operand_1 = null;
        operand_2 = null;
        operator = null;
        
    }
    
    /**
     * Constructor for the Token class
     * @param operand_1 Left operand of the expression
     * @param operand_2 Right operand of the expression
     * @param operator  Binary operator between the two operands
     */
    public Token(String operand_1,String operand_2,String operator){
        
        this.operand_1 = operand_1;
        this.operand_2 = operand_2;
        this.operator = operator;
        
    }
    
        /**
     * Constructor for the Token class
     * @param operand_1 Left operand of the expression
     * @param operand_2 Right operand of the expression
     * @param operator  Binary operator between the two operands
     */
    public Token(String original,String operand_1,String operand_2,String operator){
        
        this.operand_1 = operand_1;
        this.operand_2 = operand_2;
        this.operator = operator;
        this.original = original;
    }
    
    /**
     * Setter method for operand_1
     * @param original Value to be assigned to original
     */
    public void set_original(String original){
        
        this.original = original;
        
    }
    
    /**
     * Getter method for operand_1
     * @return The data member operand_1
     */
    public String get_original(){
        
        return original;
        
    }
    
    /**
     * Setter method for operand_1
     * @param operand Value to be assigned to operand_1
     */
    public void set_operand_1(String operand){
        
        operand_1 = operand;
        
    }
    
    /**
     * Getter method for operand_1
     * @return The data member operand_1
     */
    public String get_operand_1(){
        
        return operand_1;
        
    }
    
    /**
     * Setter method for operand_2
     * @param operand Value to be assigned to operand_2
     */
    public void set_operand_2(String operand){
        
        operand_2 = operand;
        
    }
    
    /**
     * Getter method for operand_2
     * @return The data member operand_2
     */
    public String get_operand_2(){
        
        return operand_2;
        
    }
    
    /**
     * Setter method for operator
     * @param operator Value to be assigned to operator
     */
    public void set_operator(String operator){
        
        this.operator = operator;
        
    }
    
    /**
     * Getter method for operator
     * @return The data member operator
     */
    public String get_operator(){
        
        return operator;
        
    }
    
}
