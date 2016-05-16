package Data_Structures.Tree;

/*
 * Name: Alec Farfan
 * Date: 05/07/2015
 * Purpose: Tree Iterator Interface
 */

// Import libraries
import java.util.Iterator;

public interface Tree_Iterator<T> {
    
    /**
     * 
     * @return 
     */
    public Iterator<T> get_preorder();
    
    /**
     * 
     * @return 
     */
    public Iterator<T> get_postorder();
    
    /**
     * 
     * @return 
     */
    public Iterator<T> get_inorder();
    
    /**
     * 
     * @return 
     */
    public Iterator<T> get_levelorder();
    
}
