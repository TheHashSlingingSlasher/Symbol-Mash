package Data_Structures.Stack;

/*
 * Name: Alec Farfan
 * Date: 05/09/2015
 * Purpose: Stack Interface
 */


public interface Stack_Interface<T> {
    
    /**
     * Adds a new entry to the top of the stack
     * @param value The object to be added to top of the stack
     */
    public void push(T value);
    
    /**
     * Removes and returns the object at the top of the stack
     * @return The object at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T pop();
    
    /**
     * Retrieves the object at the top of the stack
     * @return The object at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T peek();
    
    /**
     * Determines whether the stack is empty
     * @return True if the stack is empty, false otherwise
     */
    public boolean is_empty();
    
    /**
     * Removes all entries from the stack
     */
    public void clear();
    
    
}
