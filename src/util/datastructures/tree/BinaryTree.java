/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Binary Tree Class
 */

package util.datastructures.tree;

// Import libraries
import util.datastructures.stack.StackInterface;
import util.datastructures.stack.LinkedStack;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements BinaryTreeInterface<T> {
    
    private BinaryNode<T> root;
    
    private class Inorder_Iterator implements Iterator<T>{
        
        private StackInterface<BinaryNode<T>> node_stack;
        private BinaryNode<T> current_node;
        
        public Inorder_Iterator(){
            
            node_stack = new LinkedStack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            BinaryNode<T> next_node = null;
            
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
        
        private StackInterface<BinaryNode<T>> node_stack;
        private BinaryNode<T> current_node;
        
        public Preorder_Iterator(){
            
            node_stack = new LinkedStack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            BinaryNode<T> next_node = null;
            
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
        
        private StackInterface<BinaryNode<T>> node_stack;
        private BinaryNode<T> current_node;
        
        public Postorder_Iterator(){
            
            node_stack = new LinkedStack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            BinaryNode<T> next_node = null;
            
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
        
        private StackInterface<BinaryNode<T>> node_stack;
        private BinaryNode<T> current_node;
        
        public Levelorder_Iterator(){
            
            node_stack = new LinkedStack<>();
            current_node = root;
            
        }
        
        @Override
        public boolean hasNext(){
            
            return !node_stack.is_empty() || current_node != null;
            
        }
        
        @Override
        public T next(){
            
            BinaryNode<T> next_node = null;
            
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
    
    public BinaryTree(){
        
        root = null;
        
    }
    
    public BinaryTree(T root){
        
        this.root = new BinaryNode<>(root);
        
    }
    
    public BinaryTree(T root, BinaryTree<T> left, BinaryTree<T> right){
        
        private_set_tree(root,left,right);
        
    }
    
    @Override
    public void set_tree(T root_data){
        
        this.root = new BinaryNode<>(root_data);
        
    }
    
    @Override
    public void set_tree(T root, BinaryTreeInterface<T> left, 
                         BinaryTreeInterface<T> right){
        
        private_set_tree(root,(BinaryTree<T>)left,(BinaryTree<T>)right);
        
    }
    
    private void private_set_tree(T root, BinaryTree<T> left, 
                                  BinaryTree<T> right){
        
        this.root = new BinaryNode<>(root);
        
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
    
    protected void set_root_node(BinaryNode<T> root){
        
        this.root = root;
        
    }
    
    public BinaryNode<T> get_root(){
        
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
