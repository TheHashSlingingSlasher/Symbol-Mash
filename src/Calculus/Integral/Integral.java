package Calculus.Integral;
import java.util.*;
/**
 * Class for evaluating integrals
 * @author Joshua Camacho
 */

public class Integral {
	private String s;
        private String antiderivative;
	private Node head;
	
	public Integral(){}
	
        public String get_antiderivative(){
            
            return antiderivative + " + C";
            
        }
        /**
         * Function to evaluate integral. Starts the recursive method
         * for solving the integral
         * @param str A string containing the function to be evaluated
         */
	public void eval(String str){
		this.s=str;
		Node head=new Node();
		antiderivative = recurse(head,s);
	}
	
        /**
         * Function to return the base of the function if it's a function
         * with a constant multiplier
         * @param data
         * @return 
         */
	private String getBase(String data) {
		String s =data.substring(getMult(data).length()+1);
		if(s.charAt(0)=='(') s=s.substring(1);
		int parenthesis=0;
		for(int i=0; i<s.length();i++){
			if(s.charAt(i)=='(') parenthesis++;
			if(s.charAt(i)==')') parenthesis--;
			if(i==s.length()-1 && parenthesis!=0) s=s.substring(0,s.length()-1);
		}
		
		return s;
	}
	
	private String getMult(String data) {
		String s="";
		int parenthesis=0;
		for(int i=0;i<data.length(); i++){
			if(data.charAt(i)=='(') parenthesis++;
			if(data.charAt(i)==')') parenthesis--;
			if((parenthesis==0) &&  data.charAt(i)=='*') break;
			s+=data.charAt(i);
		}
		return s;
	}

	public String recurse(Node n, String s){
		n.data=s;
		if(multipleTerms(s)){
			String top=getTopTerm(s);
			String bottom=getBottomTerm(s);
                        n.termConnect=getTermConnect(s);
			n.leftchild=new Node();
			n.rightchild=new Node();
			n.leftchild.data=recurse(n.leftchild,top);
			n.rightchild.data=recurse(n.rightchild,bottom);
			return n.leftchild.data + n.termConnect + n.rightchild.data;
		}
		if(constantMultiplier(s)){
			if(multipleTerms(getBase(s))){
				String mult=getMult(s);
				s=getBase(s);
				String top=getTopTerm(s);
				String bottom=getBottomTerm(s);
                                n.termConnect=getTermConnect(s);
				n.leftchild=new Node();
				n.rightchild=new Node();
				n.leftchild.data=recurse(n.leftchild,top);
				n.rightchild.data=recurse(n.rightchild,bottom);
				return mult+ "*(" + n.leftchild.data + n.termConnect + n.rightchild.data+")";
			}else{
				String mult=getMult(s);
				s=getBase(s);
				CommonIntegralMap m= new CommonIntegralMap();
				return mult+"*"+m.getIntegral(s);
			}
		}
		if(!multipleTerms(s)){
			CommonIntegralMap m= new CommonIntegralMap();
			return m.getIntegral(s);
		}
		return "Unable to evaluate";
	}
	
	private String getBottomTerm(String s) {
		int parenthesis=0;
		for(int i=0; i<s.length(); i++){
			if (s.charAt(i)=='(') parenthesis++;
			if (s.charAt(i)==')') parenthesis--;
			if ( (s.charAt(i)=='+' || s.charAt(i) == '-') &&
					parenthesis==0 && i!=0)
			{ 
				s=s.substring(i+1);
				return s;
			}
		}
		return s;
	}
	
	private String getTopTerm(String s) {
		int parenthesis=0;
		String temp = "";
		for(int i=0; i<s.length(); i++){
			if (s.charAt(i)=='(') parenthesis++;
			if (s.charAt(i)==')') parenthesis--;
			if ( (s.charAt(i)=='+' || s.charAt(i) == '-') &&
					parenthesis==0 && i!=0)
			{ 
				return temp;
			}
			temp+=s.charAt(i);
		}
		return s;
	}
	
	private boolean multipleTerms(String s) {
		if(s==getTopTerm(s))return false;
		return true;
	}
	
	private boolean constantMultiplier(String s) {
		boolean foundnum=false;
		boolean hasMultiplier=false;
		for(int i=0;i<s.length();i++){
			if(Character.isDigit(s.charAt(i))){
				foundnum=true;
			}
			if(foundnum && s.charAt(i)=='*'){
				hasMultiplier=true;
			}
			if(s.charAt(i)=='x') break;
		}
		return hasMultiplier;
	}

    private String getTermConnect(String s) {
        int parenthesis=0;
        String connect="";
		for(int i=0; i<s.length(); i++){
			if (s.charAt(i)=='(') parenthesis++;
			if (s.charAt(i)==')') parenthesis--;
			if ( (s.charAt(i)=='+' || s.charAt(i) == '-') &&
					parenthesis==0 && i!=0)
			{ 
				connect+=s.charAt(i);
				return connect;
			}
		}
                return "";
    }
	
	
}
