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
    
    public static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }
    
    public static boolean isPasswordValid(String password){
        return password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    }
    
}
