/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Binary Tree Class
 */

package Data_Structures.Tree;

// Import libraries
import Data_Structures.Stack.Stack_Interface;
import Data_Structures.Stack.Linked_Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Binary_Tree<T> implements Binary_Tree_Interface<T> {
    
    private Binary_Node<T> root;
    
    private class Inorder_Iterator implements Iterator<T>{
        
        private Stack_Interface<Binary_Node<T>> node_stack;
        private Binary_Node<T> current_node;
        
        public Inorder_Iterator(){
            
            node_stack = new Linked_Stack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            Binary_Node<T> next_node = null;
            
            while(current_node != null){
                node_stack.push(current_node);
                current_node = current_node.get_left();
            }
            
            if(!node_stack.is_empty()){
                next_node = node_stack.pop();
                assert next_node != null;
                current_node = next_node.get_right();
            }
            else{
                throw new NoSuchElementException();
            }
            
            return next_node.get_data();
            
        }
        
        @Override
        public void remove(){
            
            throw new UnsupportedOperationException();
            
        } 
        
    }
    
    private class Preorder_Iterator implements Iterator<T>{
        
        private Stack_Interface<Binary_Node<T>> node_stack;
        private Binary_Node<T> current_node;
        
        public Preorder_Iterator(){
            
            node_stack = new Linked_Stack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            Binary_Node<T> next_node = null;
            
            while(current_node != null){
                node_stack.push(current_node);
                current_node = current_node.get_left();
            }
            
            if(!node_stack.is_empty()){
                next_node = node_stack.pop();
                assert next_node != null;
                current_node = next_node.get_right();
            }
            else{
                throw new NoSuchElementException();
            }
            
            return next_node.get_data();
            
        }
        
        @Override
        public void remove(){
            
            throw new UnsupportedOperationException();
            
        } 
        
    }
    
    private class Postorder_Iterator implements Iterator<T>{
        
        private Stack_Interface<Binary_Node<T>> node_stack;
        private Binary_Node<T> current_node;
        
        public Postorder_Iterator(){
            
            node_stack = new Linked_Stack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            Binary_Node<T> next_node = null;
            
            while(current_node != null){
                node_stack.push(current_node);
                current_node = current_node.get_left();
            }
            
            if(!node_stack.is_empty()){
                next_node = node_stack.pop();
                assert next_node != null;
                current_node = next_node.get_right();
            }
            else{
                throw new NoSuchElementException();
            }
            
            return next_node.get_data();
            
        }
        
        @Override
        public void remove(){
            
            throw new UnsupportedOperationException();
            
        } 
        
    }
    
    private class Levelorder_Iterator implements Iterator<T>{
        
        private Stack_Interface<Binary_Node<T>> node_stack;
        private Binary_Node<T> current_node;
        
        public Levelorder_Iterator(){
            
            node_stack = new Linked_Stack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            Binary_Node<T> next_node = null;
            
            while(current_node != null){
                node_stack.push(current_node);
                current_node = current_node.get_left();
            }
            
            if(!node_stack.is_empty()){
                next_node = node_stack.pop();
                assert next_node != null;
                current_node = next_node.get_right();
            }
            else{
                throw new NoSuchElementException();
            }
            
            return next_node.get_data();
            
        }
        
        @Override
        public void remove(){
            
            throw new UnsupportedOperationException();
            
        } 
        
    }
    
    public Binary_Tree(){
        
        root = null;
        
    }
    
    public Binary_Tree(T root){
        
        this.root = new Binary_Node<>(root);
        
    }
    
    public Binary_Tree(T root, Binary_Tree<T> left, Binary_Tree<T> right){
        
        private_set_tree(root,left,right);
        
    }
    
    @Override
    public void set_tree(T root_data){
        
        this.root = new Binary_Node<>(root_data);
        
    }
    
    @Override
    public void set_tree(T root, Binary_Tree_Interface<T> left, 
                         Binary_Tree_Interface<T> right){
        
        private_set_tree(root,(Binary_Tree<T>)left,(Binary_Tree<T>)right);
        
    }
    
    private void private_set_tree(T root, Binary_Tree<T> left, 
                                  Binary_Tree<T> right){
        
        this.root = new Binary_Node<>(root);
        
        if(left != null && !left.is_empty())
            this.root.set_left(left.root.copy());
        
        if(right != null && !right.is_empty())
            this.root.set_right(right.root.copy());
    
    }
    
    @Override
    public T get_root_data(){
        
        if(!is_empty())
            return root.get_data();
        else
            return null;
        
    }
    
    protected void set_root_data(T root_data){
        
        root.set_data(root_data);
        
    }
    
    protected void set_root_node(Binary_Node<T> root){
        
        this.root = root;
        
    }
    
    public Binary_Node<T> get_root(){
        
        return root;
        
    }
    
    @Override
    public void clear(){
        
        root = null;
        
    }
    
    @Override
    public boolean is_empty(){
        
        return root == null;
        
    }
    
    @Override
    public int get_height(){
        
        return root.get_height();
        
    }
    
    @Override
    public int get_num_nodes(){
        
        return root.get_num_nodes();
        
    }
    
    @Override
    public Iterator<T> get_inorder(){
     
        return new Inorder_Iterator();
        
    }
    
    @Override
    public Iterator<T> get_preorder(){
        
        return new Preorder_Iterator(); 
        
    }
    
    @Override
    public Iterator<T> get_postorder(){
        
        return new Postorder_Iterator();
        
    }
    
    @Override
    public Iterator<T> get_levelorder(){
        
        return new Levelorder_Iterator();
        
    }
    
    public void print(Iterator<T> order){
        
        Iterator<T> pointer = order;
        while(pointer.hasNext()){
            T output = pointer.next();
            System.out.println(output);
        }
        
    }
}
