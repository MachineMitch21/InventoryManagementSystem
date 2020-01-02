package inventorymanagementsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mitch
 */
public class InputValidater {
    
    public static String validateMinMaxInStock(int min, int max, int inStock)
    {
        String errorStr = "";
        
        if ((min > max))
        {
            errorStr += "Minimum value should not be greater than maximum value \n\n";
        }
        
        if ((max < min))
        {
            errorStr += "Maximum value should not be less than minimum value \n\n";
        }
        
        if ((inStock > max) || (inStock < min))
        {
            errorStr += "Inventory level should be less than maximum value and greater than minimum value \n\n";
        }
        
        return errorStr;
    }
    
    public static boolean isAllDigits(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            char currentChar = str.charAt(i);
            if (!Character.isDigit(currentChar)) 
                return false;
        }
        
        return true;
    }
    
    public static boolean isPrice(String str)
    {
        int numberOfDecimals = 0;
        
        for (int i = 0; i < str.length(); i++)
        {
            char currentChar = str.charAt(i);
            if (!Character.isDigit(currentChar) && currentChar != '.')
            {
               return false;
            }
            else if (currentChar == '.')
            {
                numberOfDecimals++;
            }
            
            if (numberOfDecimals > 1)
            {
                // There are more than one decimal places
                // so this is not a valid price entry
                return false;
            }
        }
        
        return true;
    }
}
