package menuItems;

import java.awt.*;
import java.util.Arrays;

public class Drink extends MenuItem {

    private int idD;
    public Drink(String name, float price, boolean isGlutenFree) {
        super(name,  price, isGlutenFree);
        this.idD = this.id;
    }

    @Override
    public int getId() {
        return this.idD;
    }

    @Override
    public void printMenuItem() {
        String print = this.idD +". " + "Name: " + this.getName() + "\n" +
                "   Price: " + this.getPrice() + "\n" +
                "   Has gluten: " +this.isGlutenFree() + "\n";
        System.out.println(print);
    }

    @Override
    public String[] getIngredients() {
        return null;
    }

}
