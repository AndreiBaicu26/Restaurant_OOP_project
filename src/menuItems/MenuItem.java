package menuItems;

import java.util.Arrays;

public abstract class MenuItem {

    private String name;
    private float  price;
    private boolean isGlutenFree;
    public static int id = 0;

    public MenuItem(String name,  float price, boolean isGlutenFree) {
        this.name = name;
        this.price = price;
        this.isGlutenFree = isGlutenFree;
        this.id++;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public abstract int getId();


    public abstract void printMenuItem();

    public abstract String[] getIngredients();
}
