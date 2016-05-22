package util.datastructures.parsetree;

import util.datastructures.pair.Pair;
import java.util.HashMap;
/**
 *
 * @author alec
 */
public final class LookUpTable {
    
    public HashMap<String, Pair> table;
    
    public LookUpTable() {
        table = new HashMap();
        table.put("ln", new Pair("1/x", "xln(x)-ln(x)"));
        table.put("log", new Pair("log(a,x)(1/x)", "log(a,x)/x"));       
        table.put("e^x", new Pair("e^(x)", "e^(x)"));
        table.put("a^x", new Pair("cos", "-cos"));
        table.put("x^n", new Pair("cos", "-cos"));
        table.put("sin", new Pair("cos(x)", "-cos(x)"));
        table.put("cos", new Pair("-sin(x)", "sin(x)"));
        table.put("tan", new Pair("(sec(x))^(2)", "ln(sec(x))"));
        table.put("csc", new Pair("-csc(x)cot(x)", "ln(csc(x)-cot(x))"));
        table.put("sec", new Pair("sec(x)tan(x)", "ln(sec(x)+tan(x))"));
        table.put("cot", new Pair("ln(sin(x))", "ln(sin(x))"));
        table.put("arcsin", new Pair("1/(1+x^(2))", "-cos"));
        table.put("arccos", new Pair("cos", "-cos"));
        table.put("arctan", new Pair("cos", "-cos"));
        table.put("sinh", new Pair("cosh", "cosh"));
        table.put("cosh", new Pair("sinh", "sinh"));
        
    }
    
    public boolean contains(String str) {
        
        return table.containsKey(str);
        
    }
    
    
}
