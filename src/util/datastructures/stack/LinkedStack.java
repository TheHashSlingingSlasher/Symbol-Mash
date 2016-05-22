package util.datastructures.stack;


import util.datastructures.stack.StackInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alec
 */
public class LinkedStack<T> implements StackInterface<T> {
    
    private class Node{
        Node next;
        T data;
    }
    
    Node top;
    int size;
    
    /**
     * Constructor for Linked_Stack. Initializes an empty stack.
     */
    public LinkedStack(){
        
        top = null;
        
    }
    
    /**
     * Adds a new entry to the top of the stack
     * @param value The object to be added to top of the stack
     */
    public void push(T value){
        
        Node new_node = new Node();
        new_node.data = value;
        new_node.next = null;
        
        if(top == null){
            top = new_node;
        }
        else{
            new_node.next = top;
            top = new_node;
        }
        
        size++;
        
    }  
    
    /**
     * Removes and returns the object at the top of the stack
     * @return The object at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T pop(){
        
        T popped = peek();
        top = top.next;
        size--;
        return popped;
        
    }
    
    /**
     * Retrieves the object at the top of the stack
     * @return The object at the top of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T peek(){
        
       if(!is_empty()){
           return top.data;
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
        
        if(top == null){
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
        
        while(!is_empty()){
            pop();
            size--;
        }
        
    }
    
    public T[] to_array(){
          
        T array[] = (T[])new Object[size];
        Node node_ptr = top;
        for(int i=size-1;i>=0;i--){
            array[i] = node_ptr.data;
            node_ptr = node_ptr.next;
        }
        
        return array;
        
    }
    
}
