package Data_Structures.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alec
 */
public class Array_Stack<T> implements Stack_Interface<T> {
   
    T array[];
    int size;
    int capacity;
    
    /**
     * Constructor for Array_Stack with no arguments. Initializes array to a
     * capacity of 0.
     */
    public Array_Stack(){
        
        array = (T[])new Object[0];
        size = 0;
        capacity = 0;
        
    }
    
    /**
     * Constructor for Array_Stack with a single argument. Initializes array to
     * capacity given in the parameter.
     * @param capacity Capacity of the array.
     */
    public Array_Stack(int capacity){
        
        array = (T[])new Object[capacity];
        size = 0;
        this.capacity = capacity;
        
    }
    
    /**
     * Checks to see if the array is full up to the current capacity. If the 
     * array is full then the array is doubled in size.
     */
    public void check_capacity(){
        
        if(size == capacity){
            this.resize(size*2);
        }
        
    }
    
    /**
     * Resizes the array to a given capacity.
     * @param new_capacity The new capacity of the array.
     */
    public void resize(int new_capacity){
        
        T[] new_array = (T[])new Object[new_capacity];
        for(int i=0;i<size;i++){
            new_array[i] = array[i];
        }
        
        array = new_array;
        capacity = new_capacity;
        
    }
    
    /**
     * Adds a new entry to the top of the stack
     * @param value The object to be added to top of the stack
     * @Override
     */
    public void push(T value){
        
        this.check_capacity();
        array[size] = value;
        size++;
        
    }
    
    /**
     * Removes and returns the object at the top of the stack
     * @return The object at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T pop(){
        
        T top = peek();
        size--;
        return top;
        
    }
    
    /**
     * Retrieves the object at the top of the stack
     * @return The object at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T peek(){
        
        if(!is_empty()){
            return array[size-1];
        }
        else{
            return null;
        }
        
    }
    
    /**
     * Determines whether the stack is empty
     * @return True if the stack is empty, false otherwise
     */
    public boolean is_empty(){
        
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /**
     * Removes all entries from the stack
     */
    public void clear(){
        
        for(int i=0;i<size;i++){
            array[i] = null;
        }
        
    }
    
}
