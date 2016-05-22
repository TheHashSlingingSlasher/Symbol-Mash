/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.datastructures.pair;

/**
 *
 * @author alec
 */
public class Pair {
    
    String key;
    String value;
    
    public Pair(String key, String value){
        this.key = key;
        this.value = value;
    }
    
    public void setKey(String key){
        
        this.key = key;
        
    }
    
    public String getKey() {
        
        return key;
        
    }
    
    public void setValue(String value){
        
        this.value = value;
        
    }
    
    public String getValue() {
        
        return value;
        
    }
    
}
