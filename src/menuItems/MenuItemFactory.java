package menuItems;

import menuItems.Dessert;
import menuItems.Drink;
import menuItems.Food;
import java.awt.*;

public class MenuItemFactory {

    public static MenuItemFactory instance = null;
    public MenuItemFactory() {
    }

    public MenuItem getMenuItem(MenuItemTypes itemType, String name, String[] ingredients, float price, boolean isGlutenFree ) throws Exception {
        switch (itemType){
            case DRINKS: return new Drink(name,price, isGlutenFree);
            case FOOD:{
                if(ingredients == null) throw new Exception("Err creating menuItems.Food:  must have ingredients");
                return new Food(name, ingredients,price,isGlutenFree);
            }
            case DESSERTS:{
                if(ingredients == null) throw new Exception("Err creating menuItems.Dessert:  must have ingredients");
                return new Dessert(name, ingredients,price,isGlutenFree);
            }
        }
        return null;
    }

    public static MenuItemFactory getInstance(){
        if(instance == null){
            instance = new MenuItemFactory();
            return instance;
        }
        return null;
    }
}
