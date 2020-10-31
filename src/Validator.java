import exceptions.InvalidMenuTypeException;
import exceptions.InvalidPriceException;
import menuItems.MenuItem;
import menuItems.MenuItemTypes;

import java.security.spec.ECField;

public class Validator {

    public static Validator INSTANCE = null;

    private Validator(){

    }

    public static boolean validateMenuItemType(String menuItemType) {
        try {
            if(menuItemType == MenuItemTypes.DRINKS.toString() ||
                    menuItemType == MenuItemTypes.FOOD.toString() ||
                    menuItemType == MenuItemTypes.DESSERTS.toString()){
                return true;
            }else {
                throw new InvalidMenuTypeException("Selected menu type is invalid");
            }
        } catch (InvalidMenuTypeException e) {
            System.out.println( e.getMessage());
            return false;
        }
    }

    public static boolean validatePrice(String price){
        try{
           float priceF = Float.parseFloat(price);
           if(priceF < 0) throw new InvalidPriceException("Price can't be negative");
           return true;
        }catch (NumberFormatException ex ){
            System.out.println("Price is incorrect");
            return false;
        }catch(InvalidPriceException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean validateHasGluten(String hasGluten){
        try{
            boolean hasGlutenBool = Boolean.parseBoolean(hasGluten);
            return true;
        }catch(Exception ex){
            System.out.println("Not a boolean value!");
            return false;
        }
    }

    public static boolean validateName(String name){
        return name.trim().isEmpty();
    }

    public static Validator getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new Validator();
            return INSTANCE;
        }
        return INSTANCE;
    }
}
