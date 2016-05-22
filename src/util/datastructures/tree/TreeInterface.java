/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Tree Interface
 */

package util.datastructures.tree;

public interface TreeInterface<T> {
    
    /**
     * 
     * @return 
     */
    public T get_root_data();
    
    /**
     * 
     * @return 
     */
    public int get_height();
    
    /**
     * 
     * @return 
     */
    public int get_num_nodes();
    
    /**
     * 
     * @return 
     */
    
    public boolean is_empty();
    
    /**
     * 
     */
    public void clear();
    
}
