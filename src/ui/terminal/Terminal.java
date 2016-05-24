/*
 * Name: Alec Farfan
 * Date: 06/10/15
 * Purpose: Terminal class used to control the Terminal_UI
 */

package ui.terminal;

// Import libraries
import math.linearalgebra.Matrix;
import math.calculus.Integral;
import math.calculus.Derivative;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/**
 *
 * @author Alec
 */
public class Terminal {
    
    private HashMap<String,String> functions;   // Container for regular functions
    private HashMap<String,String> vector_functions; // Container for vector functions
    private HashMap<String,String> matrices;    // Container for matrices
    private HashSet<String> commands;           // Container for known comands
    private ArrayList<String> history;          // Container for issued commands
    private int history_index;// Variable used in retrival of commands from history
    private boolean alive;    // Variable used to flag end of session
    
    /**
     * Constructor for the Terminal class. Instantiates new HashMap objects
     * to store the functions, vector functions, and matrices that the user
     * defines. Instantiates and fills a HashSet 'commands' with all the known
     * terminal commands. Instantiates an ArrayList to store the commands the
     * user issues. Initializes history_index to 0 and sets alive to true.
     */
    public Terminal(){
        
        functions = new HashMap();
        vector_functions = new HashMap();
        matrices = new HashMap();
        commands = fill_commands();
        history = new ArrayList();
        history_index = 0;
        alive = true;
        
    }
    
    /**
     * Setter method for the functions field. Sets the calling object's 
     * functions field to the HashMap passed into the parameter
     * @param functions The HashMap to be assigned to the calling objects
     *                  functions field
     */
    public void set_functions(HashMap<String,String> functions){
        
        this.functions = functions;
        
    }
    
    /**
     * Getter method for the functions field.
     * @return The calling object's functions field
     */
    public HashMap<String,String> get_functions(){
        
        return functions;
        
    }
    
    /**
     * Setter method for the vector_functions field. Sets the calling object's
     * vector_functions field to the HashMap passed into the parameter.
     * @param vector_functions The HashMap to be assigned to the calling
     *                         object's vector_functions field.
     */
    public void set_vector_functions(HashMap<String,String> vector_functions){
        
        this.vector_functions = vector_functions;
        
    }
    
    /**
     * Getter method for the vector_functions field
     * @return The calling object's vector_functions field
     */
    public HashMap<String,String> get_vector_functions(){
        
        return vector_functions;
        
    }
    
    /**
     * Setter method for the matrices field. Sets the calling object's matrices
     * field to the HashMap passed into the parameter
     * @param matrices The HashMap to be assigned to the calling object's
     *                 matrices field
     */
    public void set_matrices(HashMap<String,String> matrices){
        
        this.matrices = matrices;
        
    }
    
    /**
     * Getter method for the matrices field.
     * @return The calling object's matrices field.
     */
    public HashMap<String,String> get_matrices(){
        
        return matrices;
        
    }
    
    /**
     * Setter method for the commands field. Sets the calling object's commands
     * field to the HashSet passed into the parameter.
     * @param commands The HashSet to be assigned to the calling object's
     *                 commands field.
     */
    public void set_commands(HashSet<String> commands){
        
        this.commands = commands;
        
    }
    
    /**
     * Getter method for the commands field. 
     * @return The calling object's commands field.
     */
    public HashSet<String> get_commands(){
        
        return commands;
        
    }
    
    /**
     * Setter method for the history field. Sets the calling object's history
     * field to the ArrayList passed into the parameter
     * @param history The ArrayList to be assigned to the calling object's
     *                history field.
     */
    public void set_history(ArrayList<String> history){
        
        this.history = history;
        
    }
    
    /**
     * Getter method for the history field.
     * @return The calling object's history field.
     */
    public ArrayList<String> get_history(){
        
        return history;
        
    }
    
    /**
     * Setter method for the history_index field. Sets the calling object's
     * history_index field to the integer value passed into the parameter.
     * @param history_index The integer value to be assigned to the calling
     *                      object's history_index field.
     */
    public void set_history_index(int history_index){
        
        this.history_index = history_index;
        
    }
    
    /**
     * Getter method for the history_index field.
     * @return The calling object's history_index field
     */
    public int get_history_index(){
        
        return history_index;
        
    }
    
    /**
     * Setter method for the alive field. Sets the calling object's alive field
     * to the boolean value passed into the parameter.
     * @param alive The boolean value to be assigned to the calling object's
     *              alive field.
     */
    public void set_alive(boolean alive){
        
        this.alive = alive;
        
    }
    
    /**
     * Getter method for the alive field.
     * @return The calling object's alive field.
     */
    public boolean get_alive(){
        
        return alive;
        
    }
    
    public String get_history_item(int offset){
        
        if(history.size()-1 - offset >=0 && !history.isEmpty()){
            return history.get((history.size()-1) - offset);
        }
        else{
                return "$";
        }
        
    }
    
    public HashSet<String> fill_commands(){
        
        HashSet commands = new HashSet();
        commands.add("define");
        commands.add("differentiate");
        commands.add("integrate");
        commands.add("print");
        commands.add("invert");
        commands.add("clear");
        commands.add("exit");
        
        return commands;
        
    }
    
    public String input(String input){
        
        String command = get_command(input);
        
        switch(command){

            case "define":
                define(input);
                break;
            
            case "differentiate": 
                 return differentiates(input);

            case "integrate":
                 return integrate(input);
                    
            case "print":
                return ("    " + functions.toString());
            
            case "invert":
                return invert(input);
             
            case "clear":
                return clear();
                
            case "exit":
                 set_alive(false);
                 break;
                
            default:
                return "    Invalid command";

        }
        
        return null;
        
    }
 
    public void define(String input){
        
        String name = clean(get_name(input));
        String function = clean(get_function(input));
        if(function.charAt(0) == '['){
            make_matrix(function);
            matrices.put(name,function);
        }

        functions.put(name,function);
        
    }
    
    public String differentiates(String input){
        
        String name = get_name(input);
        
        if(functions.containsKey(name))
            return  Derivative.differentiate(functions.get(name));
        //new Derivative(functions.get(name)).fPrime;
        
        else
            return null;
        
    }
    
    public String integrate(String input){
        
        return null;
        
    }
    
    public String invert(String input){
        
        String name = get_name(input);
        Matrix a = make_matrix(matrices.get(name));
        a = a.matrix_inverse(a);
        
        return a.to_string();
        
    }
    
    public String get_command(String input_line){
        
        String command = "";
        for(int i = 0; i < input_line.length() && input_line.charAt(i) != ' ';i++){
            command += input_line.charAt(i);
        }
        
        return command;
        
    }
    
    public String get_name(String input_line){
        
        String token = "";
        int index = 0;
        
        while(input_line.charAt(index) != ' '){
            index ++;
        }
        for(int j = index + 1; j < input_line.length() && 
                                  input_line.charAt(j) != ' '; j++){
            token += input_line.charAt(j);
        }
        
        return token;
        
    }
    
    public String get_function(String input_line){
        
        String function = "";
        int count = 0;
        
        while(input_line.charAt(count) != '='){
            count++;
        }
        for(int i = count + 1; i < input_line.length() ; i++){
            function += input_line.charAt(i);
        }
        
        return function;
        
    }
    
    public Matrix make_matrix(String input_line){
        
        Matrix a;
        int rows = 0;
        int columns = 0;
        Queue<String> entries = new LinkedList<>();
        String entry = "";
        
        // Determine the number of columns in the matrix
        input_line = input_line.substring(1,input_line.length()-1);
        for(int i = 0; input_line.charAt(i) != ';'; i++){
            if(input_line.charAt(i) == ',')
                columns++;
        }
        columns++;
        
        // Determine the number of rows and the entries in each slot
        for(int i = 0; i < input_line.length(); i++){
            if(input_line.charAt(i) == ';')
                rows++;
            if(input_line.charAt(i) != ',' && input_line.charAt(i) != ';'){
                entry += input_line.charAt(i);
            }
            else{
                entries.add(clean(entry));
                entry = "";
            }
        }
        
        // Create and fill a new matrix
        a = new Matrix(rows,columns);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                String element = entries.remove();
                a.set_element(Float.parseFloat(element),i,j);
            }
        }
        
        return a;
        
    }
    
    public boolean end_session(){
        
        return get_alive() == false;
        
    }
    
    public String clean(String original){
        
        String modified = "";
        int start = 0;
        int end = original.length()-1;
        
        while(original.charAt(start) == ' '){
            start++;
        }
        while(original.charAt(end) == ' '){
            end--;
        }
        
        for(int i = start; i <= end; i++){
            modified += original.charAt(i);
        }
        
        return modified;
        
    }
    
    public String clear(){
        
        return "clear";
        
    }
    
    public void print(){
        
       System.out.println("------------------Symbolica Terminal------------------");
        
    }
    
}
