package Data_Structures.Tree;

/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Binary Tree Interface
 */

public interface Binary_Tree_Interface<T> extends Tree_Interface<T>,
                                                  Tree_Iterator<T>{
    
    /**
     * 
     * @param root_data 
     */
    public void set_tree(T root_data);
    
    /**
     * 
     * @param rood_data
     * @param left
     * @param right 
     */
    public void set_tree(T rood_data,Binary_Tree_Interface<T> left,
                         Binary_Tree_Interface<T> right);
    
}
