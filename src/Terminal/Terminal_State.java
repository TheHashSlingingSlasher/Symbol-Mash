/*
 * Name: Alec Farfan
 * Date: 06/10/15
 * Purpose: Terminal_State class used to control the Terminal_UI
 */

package Terminal;

/**
 *
 * @author Alec
 */
public class Terminal_State {
    
    private int cursor_position;
    
    public Terminal_State(){
        
        cursor_position = 72;
        
    }
    
    public void set_cursor_position(int cursor_position){
        
        this.cursor_position = cursor_position;
        
    }
    
    public int get_cursor_position(){
        
        return cursor_position;
        
    }
    
}
