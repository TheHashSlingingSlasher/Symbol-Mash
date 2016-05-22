package util.datastructures.tree;

/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Binary Tree Interface
 */

public interface BinaryTreeInterface<T> extends TreeInterface<T>,
                                                  TreeIterator<T>{
    
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
    public void set_tree(T rood_data,BinaryTreeInterface<T> left,
                         BinaryTreeInterface<T> right);
    
}
