/*
 * Name: Alec Farfan
 * Date: 05/29/2015
 * Purpose: Real Number Class
 */

package math.number;

/**
 *
 * @author Alec
 */
public class RealNumber {

    // Data Fields
    int numerator;
    int denominator;
    
    /**
     * Constructor 1 of 2 for the Real Number Class. Creates a new real number
     * with numerator a and denominator 1.
     * @param a Integer to be assigned to the numerator field
     */
    public RealNumber(int a){
        
        numerator = a;
        denominator = 1;
        
    }
    
    /**
     * Constructor 2 of 2 for the Real Number Class. Creates a new real number
     * with numerator a and denominator b.
     * @param a Integer to be assigned to the numerator field
     * @param b Integer to be assigned to the denominator field
     */
    public RealNumber(int a, int b){
        
        numerator = a;
        denominator = b;
        
    }
    
    /**
     * Setter method for the numerator field
     * @param numerator Integer to be assigned to this object's numerator field
     */
    public void set_numerator(int numerator){
        
        this.numerator = numerator;
        
    }
    
    /**
     * Getter method for the numerator field
     * @return The calling object's numerator field value
     */
    public int get_numerator(){
        
        return numerator;
        
    }
    
    /**
     * Setter method for the numerator field
     * @param denominator Integer to be assigned to this object's denominator 
     *                    field
     */
    public void set_denominator(int denominator){
        
        this.denominator = denominator;
        
    }
    
    /**
     * Getter method for the denominator field
     * @return The calling object's denominator field value
     */
    public int get_denominator(){
        
        return denominator;
        
    }
    
    /**
     * Calculates the sum of two real numbers a and b
     * @param a The first real number a of a + b
     * @param b The second real number b of a + b
     * @return The result c = a + b
     */
    public RealNumber add(RealNumber a, RealNumber b){
        
        if(a.get_denominator() == b.get_denominator()){
            return new RealNumber(a.get_numerator() + b.get_numerator(), 
                                   a.get_denominator());
        }
        else{
            RealNumber result = new RealNumber(a.get_numerator() * 
                                 b.get_denominator()+ b.get_numerator() * 
                                 a.get_denominator(), a.get_denominator() * 
                                 b.get_denominator());
            result = simplify(result);
            return result;
            
        }
        
    }
    
    /**
     * Calculates the difference of two real numbers a and b
     * @param a The first real number a of a - b
     * @param b The second real number b of a - b
     * @return The result c = a - b 
     */
    public RealNumber subtract(RealNumber a, RealNumber b){
        
       if(a.get_denominator() == b.get_denominator()){
            RealNumber result = new RealNumber(a.get_numerator() - b.get_numerator(), 
                                     a.get_denominator());
            return result;
        }
        else{
            RealNumber result = new RealNumber(a.get_numerator() * 
                                 b.get_denominator()- b.get_numerator() * 
                                 a.get_denominator(), a.get_denominator() * 
                                 b.get_denominator());
            result = simplify(result);
            return result;
            
        }
        
    }
    
    /**
     * Calculates the product of two real numbers a and b
     * @param a The first real number a of ab
     * @param b The second real number b of ab
     * @return  The result c = ab
     */
    public RealNumber multiply(RealNumber a, RealNumber b){
        
        RealNumber result = new RealNumber(a.get_numerator() * b.get_numerator(),
                                     a.get_denominator() * b.get_denominator());
        result = simplify(result);
        
        return result;
        
    }
    
    /**
     * Calculates the quotient of two real numbers a and b
     * @param a The first real number a of a/b
     * @param b The second real number b of a/b
     * @return  The result c = a/b
     */
    public RealNumber divide(RealNumber a, RealNumber b){
        
        RealNumber result = new RealNumber(a.get_numerator() * b.get_denominator(),
                                     a.get_denominator() * b.get_numerator());
        result = simplify(result);
        
        return result;
        
    }
    
    /**
     * Simplifies a rational number so that the numerator and denominator have
     * no common factors
     * @param n The RealNumber to be simplified
     * @return The simplified number
     */
    public RealNumber simplify(RealNumber n){
        
        int divisor = gcd(n.get_numerator(),n.get_denominator());
        RealNumber result = new RealNumber(n.get_numerator() / divisor , 
                                             n.get_denominator() / divisor);
        
        return result;
        
    }
    
    /**
     * Calculates the greatest common denominator of two integers
     * @param a The first integer
     * @param b The second integer
     * @return 
     */
    public int gcd(int a, int b){
        
        if(a == 0)
            return b;
        if(a > b)
            return gcd(a%b,b);
        else
            return gcd(b%a,a);
        
    }
    
    /**
     * 
     * @param n 
     */
    public void print_number(RealNumber n){
        
        if(n.get_denominator() == 1)
            System.out.println(n.get_numerator());
        else
            System.out.println(n.get_numerator() + "/" + n.get_denominator());
        
    }
    
}
