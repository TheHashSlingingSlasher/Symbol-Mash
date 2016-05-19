/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus.Differential;

import java.util.HashMap;
import java.util.Arrays;
import Data_Structures.Pair.*;
/**
 *
 * @author alec
 */
public final class LookUpTable {
    
    public HashMap<String, Pair> table;
    
    public LookUpTable() {
        table = new HashMap();
        table.put("sin", new Pair("cos(x)", "-cos(x)"));
        table.put("cos", new Pair("-sin(x)", "sin(x)"));
        table.put("tan", new Pair("(sec(x))^(2)", "ln(sec(x))"));
        table.put("csc", new Pair("-csc(x)cot(x)", "ln(csc(x)-cot(x))"));
        table.put("sec", new Pair("sec(x)tan(x)", "ln(sec(x)+tan(x))"));
        table.put("cot", new Pair("ln(sin(x))", "ln(sin(x))"));
        table.put("arcsin", new Pair("1/(1+x^(2))", "-cos"));
        table.put("arccos", new Pair("cos", "-cos"));
        table.put("arctan", new Pair("cos", "-cos"));
        table.put("e^x", new Pair("cos", "-cos"));
        table.put("a^x", new Pair("cos", "-cos"));
        table.put("x^n", new Pair("cos", "-cos"));
    }
    
    public boolean contains(String str) {
        
        return table.containsKey(str);
        
    }
    
    
}
