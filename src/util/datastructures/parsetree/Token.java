package util.datastructures.parsetree;

/**
 * @author Alec Farfan
 */
public class Token {
    
    private String lValue;    // Left operand of expression
    private String rValue;    // Right operand of expression
    private String operator;  // Binary operator between the two operands
    
    /**
     * @breif Default constructor for the Token Class. Sets each data member to
     *        null.
     */
    public Token(){
        
        lValue = null;
        rValue = null;
        operator = null;
        
    }
    
    /**
     * @breif Constructor for the Token class. Assigns values for all three data
     *        members of the class. 
     * @param lValue Left operand of the expression.
     * @param rValue Right operand of the expression.
     * @param operator  Binary operator between the two operands.
     */
    public Token(String lValue, String rValue, String operator){
        
        this.lValue = lValue;
        this.rValue = rValue;
        this.operator = operator;
        
    }
    
    /**
     * @breif Setter method for the lValue field.
     * @param operand Value to be assigned to the calling object's lValue field.
     */
    public void setLValue(String operand){
        
        lValue = operand;
        
    }
    
    /**
     * @breif Getter method for the lValue field.
     * @return Value of the calling object's lValue field.
     */
    public String getLValue(){
        
        return lValue;
        
    }
    
    /**
     * @brief Setter method for the rValue field.
     * @param operand Value to be assigned to the calling object's rValue field.
     */
    public void setRValue(String operand){
        
        rValue = operand;
        
    }
    
    /**
     * @breif Getter method for the rValue field.
     * @return Value of the calling object's rValue field.
     */
    public String getRValue(){
        
        return rValue;
        
    }
    
    /**
     * @brief Setter method for the operator field.
     * @param operator Value to be assigned to the calling object's operator
     *                 field
     */
    public void setOperator(String operator){
        
        this.operator = operator;
        
    }
    
    /**
     * @breif Getter method for the operator field.
     * @return Value of the calling object's operator field.
     */
    public String getOperator(){
        
        return operator;
        
    }
    
    /**
     * @brief Determines whether or not the token has a lValue.
     * @return True if an lValue exists, false otherwise.
     */
    public boolean hasLeft() {
        
        return lValue != null;
        
    }
    
    /**
     * @brief Determines whether or not the token has an rValue.
     * @return True if an rValue exists, false otherwise.
     */
    public boolean hasRight() {
        
        return rValue != null;
        
    }
    
    /**
     * @brief Determines whether or not the token has an operator.
     * @return True if an operator exists, false otherwise.
     */
    public boolean hasOperator() {
        
        return operator != null;
        
    }
    
    /**
     * @breif Determines whether or not the token represents a single constant
     *        or single variable, that is, only a single operand with no
     *        operator.
     * @return True if the expression is a single term, false otherwise.
     */
    public boolean isSingleTerm() {
        
        return (!this.hasLeft()) && (!this.hasOperator());
        
    }
    
}
