package utils;

public class Validation {
            
    public static boolean checkInput(String str, int maxLen){
        return str != null && !str.trim().isEmpty() && str.length() <= maxLen;
    }
    
}
