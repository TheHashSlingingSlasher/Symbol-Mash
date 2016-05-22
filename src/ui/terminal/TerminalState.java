/*
 * Name: Alec Farfan
 * Date: 06/10/15
 * Purpose: TerminalState class used to control the Terminal_UI
 */

package ui.terminal;

/**
 *
 * @author Alec
 */
public class TerminalState {
    
    private int cursor_position;
    
    public TerminalState(){
        
        cursor_position = 72;
        
    }
    
    public void set_cursor_position(int cursor_position){
        
        this.cursor_position = cursor_position;
        
    }
    
    public int get_cursor_position(){
        
        return cursor_position;
        
    }
    
}
