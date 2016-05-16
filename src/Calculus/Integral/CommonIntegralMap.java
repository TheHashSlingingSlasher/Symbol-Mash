package Calculus.Integral;
/**
 * Name: Joshua Camacho
 */

import java.util.HashMap;
import Calculus.Differential.*;
/**
 * Class containing a map of common integrals and the ability to pass
 * strings in order to check common integrals
 * @author josh
 */
public class CommonIntegralMap {
    
    static HashMap<String, String> m=new HashMap<String, String>();
    
    CommonIntegralMap(){
        m.put("cos(x)", "sin(x)");
        m.put("sin(x)", "-cos(x)");
        m.put("e^(x)", "e^(x)");
        m.put("sec^2(x)", "tan(x)");
        m.put("csc^2(x)", "-cot(x)");
        m.put("sec(x)*tan(x)", "sec(x)");
        m.put("csc(x)*cot(x)", "-csc(x)");
        m.put("sec(x)", "ln|sec(x)+tan(x)|");
        m.put("csc(x)", "ln|csc(x)-cot(x)|");
        m.put("tan(x)", "ln|sec(x)|");
        m.put("cot(x)", "ln|sin(x)|");
        m.put("sinh(x)", "cosh(x)");
        m.put("cosh(x)", "sinh(x)");
        m.put("1/x", "ln|x|");
        m.put("x^(2)", "(1/3)*x^(3)");
        m.put("x", "(1/2)*x^(2)");
        m.put("1/(1+x^(2))","arctan(x)");
        m.put("sin^2(x)", "(1/2)*(x-sin(x)cos(x))");
        m.put("cos^2(x)", "(1/2)*(x+sin(x)cos(x))");
        m.put("sin(x)*cos(x)","-1*(1/2)cos^2(x)");
        m.put("cos(x)*sin(x)","-1*(1/2)cos^2(x)");
    }
    
    /**
     * A function that returns true if the argument is contained in the
     * common integral map
     * @param s String to be used to check if it is contained in map
     * @return boolean true if it contains, false if not contains
     */
    public boolean contains(String s){
        
        boolean cont=false;
        
        if(m.containsKey(s)) cont=true;
        
        return cont;
    }
    
    /**
     * Function that returns true if the passed in string is a function
     * that is in the form x^(n) where n is a rational number
     * @param s String to be evaluated
     * @return true if form x^(n), false if not
     */
    public boolean checkPowerRule(String s){
        
        if(s.charAt(0)!='x')return false;
        
        for( int i=0; i<s.length(); i++ ){
            
            if(s.charAt(i)=='^') return true;
        
        }
        
        return false;
    }
    
    /**
     * Public function that can be passed a string and if it contains a
     * function that has a common integral, it will return the function's
     * integral
     * @param s String to be evaluated
     * @return The integral of the function or NOEVAL if cannot be found
     */
    public String getIntegral(String s){
        
        if (contains(s)) return m.get(s);
        
        if(checkPowerRule(s)) return evalPowerRule(s);
        
        if(isConstant(s))return constantRule(s);
        
        if(iscommonUsub(s)) return commonUsub(s);
        
        return "NOEVAL";
    }
    
    /**
     * Function called to evaluate the integral power rule 
     * @param s String to be evaluated
     * @return The evaluated integral power rule
     */
    private String evalPowerRule(String s) {
        boolean foundSign=false;
        String powNum="";
        for(int i=0; i<s.length();i++){
            
            if(foundSign && s.charAt(i)!='(' && s.charAt(i)!=')') 
                
                powNum+=s.charAt(i);
            
            if(s.charAt(i)=='^')foundSign=true;
        
        }
        
        return getMultPowRule(powNum)+"*x^"+getPowerPowRule(powNum);
    
    }

    /**
     * Private function to evaluate the constant (1/n+1) in the integral
     * power rule (1/n+1)*x^(n+1)
     * @param s string to be evaluated
     * @return (1/n+1) portion of power rule
     */
    private String getMultPowRule(String s) {
        
        if(isFraction(s)){
            
            return "("+reciprocal(addOneFraction(s))+")";
        
        }else{
            
            return "(1/"+addOne(s)+')';
        
        }
        
    }
    
    /**
     * private function that returns true if the entered string is of the form
     * n/m where n and m are integers
     * @param s String to be evaluated
     * @return true if is of form n/m, false if not
     */
    private boolean isFraction(String s){
        
        for(int i=0;i<s.length();i++){
            
            if(s.charAt(i)=='/')return true;
        
        }
        
        return false;
    
    }
    
    /**
     * Private function to evaluate the power (n+1) portion of the integral
     * power rule (1/n+1)*x^(n+1)
     * @param s String to be evaluated
     * @return (n+1)
     */
    private String getPowerPowRule(String s) {
        
        if(isFraction(s)) return "("+addOneFraction(s)+")";
        
        else return addOne(s);
    
    }

    /**
     * Private function to increase the value of a number contained in a string
     * by 1. 
     * @param powNum Number to be increased
     * @return powNum + 1
     */
    private String addOne(String powNum) {
        
        int x=Integer.parseInt(powNum)+1;
        
        return Integer.toString(x);
    
    }

    /**
     * A function to add 1 to a rational number contained in a string
     * @param s String containing the rational number
     * @return 1 added to the rational number
     */
    private String addOneFraction(String s) {
       String top="";
       String bottom="";
        
       boolean foundDivide=false;
        
        for(int i=0; i<s.length();i++){
        
            if(s.charAt(i)=='/')foundDivide=true;
            
            if(foundDivide && s.charAt(i)!='/')bottom+=s.charAt(i);
            
            else if(s.charAt(i)!='/') top+=s.charAt(i);
        
        }
        
        int t = Integer.parseInt(top);
        
        int b = Integer.parseInt(bottom);
        
        t+=b;
       
        return Integer.toString(t)+"/"+Integer.toString(b);
    }

    /**
     * Function to return the reciprocal of a rational number passed into
     * it
     * @param s String containing the rational number
     * @return The reciprocal of the rational number
     */
    private String reciprocal(String s) {
        String top="";
        String bottom="";
        
        boolean foundDivide=false;
        
        for(int i=0; i<s.length();i++){
            
            if(s.charAt(i)=='/')foundDivide=true;
            
            if(foundDivide && s.charAt(i)!='/')bottom+=s.charAt(i);
        
            else if(s.charAt(i)!='/') top+=s.charAt(i);
        
        }
        
        return bottom+"/"+top;
    }

    /**
     * Private function to check if the input string contains only rational 
     * numbers including negative numbers in the form -n
     * @param s String to be evaluated
     * @return true if is a rational number, false if not
     */
    private boolean isConstant(String s) {
       
        for(int i=0;i<s.length();i++){
        
            if(!Character.isDigit(s.charAt(i))&&
                    s.charAt(i)!='/' 
                    && s.charAt(i)!='('
                    && s.charAt(i)!=')' 
                    && s.charAt(i)!='-'
                    ) return false;
       }
       return true;
    }

    /**
     * Function to evaluate the constant integral rule
     * @param s String to be evaluated
     * @return s * x constant integral rule
     */
    private String constantRule(String s) {
        
        return s+"*x";
    
    }

    private boolean iscommonUsub(String s) {
        String operand1="";
        String outerFunction="";
        String innerFunction="";
        int parenthesis=0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)==')') parenthesis--;
            if(parenthesis>0) innerFunction+=s.charAt(i);
            if(s.charAt(i)=='(') parenthesis++;
           
            
            if(s.charAt(i)=='*'&& parenthesis==0 && i!=s.length()-1){
                outerFunction=s.substring(i+1);
                break;
            }
            
            
            
        }
        System.out.println("Inner Function is "+innerFunction);
        System.out.println("Outer Function is "+outerFunction);
        Parse_Tree t=new Parse_Tree(innerFunction);
        if(outerFunction.equals(t.f_prime.f_prime)) return true;
        
        return false;
    }

    private String commonUsub(String s) {
        int parenthesis=0;
        String operand1="";
        String outerFunction="";
        String innerFunction="";
        int startInner=0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)==')') parenthesis--;
            if(parenthesis>0) innerFunction+=s.charAt(i);
            if(s.charAt(i)=='('){
                parenthesis++;
                if(startInner==0)startInner=i;
            }
           
            
            if(s.charAt(i)=='*'&& parenthesis==0 && i!=s.length()-1){
                outerFunction=s.substring(i+1);
                break;
            }
        }
        s=s.substring(0,startInner+1)+"x)";
        System.out.println("S= "+s);
        s=m.get(s);
        startInner=0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(') {
                s=s.substring(0,i+1);
                break;
            }
        }    
        return s + innerFunction+")";    
        }
    

}
