package util.datastructures.functionlookupmap;

import util.datastructures.pair.Pair;
import java.util.HashMap;
/**
 *
 * @author alec
 */
public final class FunctionLookUpMap {
    
    public static final HashMap<String, Pair> table;
    static{
        HashMap<String, Pair> tempTable = new HashMap();
        tempTable .put("ln", new Pair("1/x", "xln(x)-ln(x)"));
        tempTable .put("log", new Pair("log(a,x)(1/x)", "log(a,x)/x"));       
        tempTable .put("e^x", new Pair("e^(x)", "e^(x)"));
        tempTable .put("a^x", new Pair("cos", "-cos"));
        tempTable .put("x^n", new Pair("cos", "-cos"));
        tempTable .put("sin", new Pair("cos(x)", "-cos(x)"));
        tempTable .put("cos", new Pair("-sin(x)", "sin(x)"));
        tempTable .put("tan", new Pair("(sec(x))^(2)", "ln(sec(x))"));
        tempTable .put("csc", new Pair("-csc(x)cot(x)", "ln(csc(x)-cot(x))"));
        tempTable .put("sec", new Pair("sec(x)tan(x)", "ln(sec(x)+tan(x))"));
        tempTable .put("cot", new Pair("ln(sin(x))", "ln(sin(x))"));
        tempTable .put("arcsin", new Pair("1/(1+x^(2))", "-cos"));
        tempTable .put("arccos", new Pair("cos", "-cos"));
        tempTable .put("arctan", new Pair("cos", "-cos"));
        tempTable .put("sinh", new Pair("cosh", "cosh"));
        tempTable .put("cosh", new Pair("sinh", "sinh"));
        table = tempTable;
    }
    
    public static boolean contains(String str) {
        
        return table.containsKey(str);
        
    }
    
}
