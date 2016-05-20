/*
 * Name: Alec Farfan
 * Date: 06/15/15
 * Purpose: VectorFunction class
 */

package vectorfunction;

// Import libraries

/**
 *
 * @author Alec
 */
public class VectorFunction {
    
    // Data fields
    String []component_functions; // < f(x), g(x), h(x) > ect..
    int dimension;                // Number of component functions
    
    /**
     * Default constructor for the Vector_Function class. Sets the
     * component_functions array to null, and the dimension to 0.
     */
    public VectorFunction(){
        
        component_functions = null;
        dimension = 0;
        
    }
    
    /**
     * Constructor for the Vector_Function class. Sets the dimension field and
     * initializes the component_functions array to size of dimension.
     * @param dimension // The dimension of the new vector function
     */
    public VectorFunction(int dimension){
        
        component_functions = new String[dimension];
        this.dimension = dimension;
        
    }
    
    /**
     * Setter method for the component_functions field. Sets the
     * component_functions array to the value passed into the parameter.
     * @param component_functions Array to be assigned to component_functions
     */
    public void set_component_functions(String[] component_functions){
        
        this.component_functions = component_functions;
        
    }
    
    /**
     * Getter method for the component_functions field.
     * @return The calling object's component_functions field.
     */
    public String[] get_component_functions(){
        
        return component_functions;
        
    }
    
    /**
     * Setter method for the dimensions field. Sets the calling object's 
     * dimension field to the value passed into the parameter.
     * @param dimension The value to be assigned to the calling object's
     *                  dimension field.
     */
    public void set_dimension(int dimension){
        
        this.dimension = dimension;
        
    }
    
    /**
     * Getter method for the dimension field.
     * @return The calling object's dimension field.
     */
    public int get_dimension(){
        
        return dimension;
        
    }
    
    /**
     * Assigns a function to the component_functions array at a given index.
     * @param function The component_function to be assigned
     * @param index    The index to place the function into
     */
    public void assign_component(String function, int index){
        
        component_functions[index] = function;
        
    }
    
    /**
     * Retrieves the component_function at a given position of the Vector
     * Function.
     * @param index The position of the Vector function to retrieve from
     * @return      The component function located at index
     */
    public String get_component(int index){
        
        return component_functions[index];
        
    }
    
    /**
     * Creates a string representing the vector function to be printed to the
     * terminal screen. ex "<sin(x),cos(x),ln(x)>"
     * @return A String representing the vector function
     */
    public String to_string(){
        
        String result = "<";
        
        for(int i = 0; i < dimension; i++){
            result += get_component(i);
            if(i != dimension - 1)
                result += ",";
        }
        
        result += ">";
        
        return result;
        
    }
}
