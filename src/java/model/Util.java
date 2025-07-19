package model;

/**
 *
 * @author Ravishka
 */
public class Util {
    
    public static String generateCode(){
    
        int r = (int)(Math.random()*1000000);
        return String.format("%06d", r);
        
    }
    
}
