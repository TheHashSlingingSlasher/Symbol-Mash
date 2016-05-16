/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Binary Node Class
 */

package Data_Structures.Tree;

public class Binary_Node<T> {
    
    // Data members
    private T data;
    private Binary_Node<T> left;
    private Binary_Node<T> right;
    
    public Binary_Node(){
        
        this(null);
        
    }
    
    public Binary_Node(T data){
        
        this(data,null,null);
    }
    
    public Binary_Node(T data,Binary_Node<T> left,Binary_Node<T> right){
        
        this.data = data;
        this.left = left;
        this.right = right;
        
    }
    
    public T get_data(){
        
        return data;
        
    }
    
    public void set_data(T data){
        
        this.data = data;
        
    }
    
    public Binary_Node<T> get_left(){
        
        return left;
        
    }
    
    public void set_left(Binary_Node<T> left){
        
        this.left = left;
        
    }
    
    public Binary_Node<T> get_right(){
        
        return right;
        
    }
    
    public void set_right(Binary_Node<T> right){
        
        this.right = right;
        
    }
    
    public int get_num_nodes(){
        
       int num_left = 0;
       int num_right = 0;
       
       if(left != null)
           num_left = left.get_num_nodes();
       if(right != null)
           num_right = right.get_num_nodes();
       
       return num_left + num_right + 1;
        
    }
    
    private int get_height(Binary_Node<T> node){
        
        int height = 0;
        if(node != null)
            height = Math.max(get_height(node.get_left()),
                              get_height(node.get_right()));
        
        return height;
        
    }
    
    public int get_height(){
        
       return get_height(this);
        
    }
    
    public boolean has_left(){
        
        return left != null;
        
    }
    
    public boolean has_right(){
        
        return right != null;
        
    }
    
    public boolean is_leaf(){
        
        return (left == null && right == null);
        
    }
    
    public Binary_Node<T> copy(){
        
        Binary_Node<T> new_root = new Binary_Node<>(data);
        
        if(left != null)
            new_root.set_left(left.copy());
        
        if(right != null)
            new_root.set_right(right.copy());
        
        return new_root;
        
    }
}
