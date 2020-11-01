import menuItems.*;

import java.util.ArrayList;

public class Menu {
    public static Menu INSTANCE = null;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    public Menu() {
        readMenuFile();
    }

    public static Menu getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new Menu();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void addMenuItem(MenuItem menuItem){
        this.menuItems.add(menuItem);
    }

    public MenuItem getMenuItem(int position){
        try{
            return this.menuItems.get(position);
        }catch(IndexOutOfBoundsException iobe){
            return null;
        }

    }
    private void readMenuFile(){
       try{
           FileService fileReader = new FileService("src/menu.txt");
          this.menuItems.addAll(fileReader.readFile());
       }catch(Exception e){
           System.err.println(e.getMessage());
       }

    }

    public ArrayList<MenuItem> getAllMenu(){
        return this.menuItems;
    }

    public ArrayList<MenuItem> getAllDrinks(){
        ArrayList<MenuItem> drinks = new ArrayList<>();
        for (MenuItem item: this.menuItems){
            if(item instanceof Drink){
                drinks.add(item);
            }
        }
        return drinks;
    }

    public ArrayList<MenuItem> getAllFood(){
        ArrayList<MenuItem> food = new ArrayList<>();
        for (MenuItem item: this.menuItems){
            if(item instanceof Food){
                food.add(item);
            }
        }
        return food;
    }

    public ArrayList<MenuItem> getAllDessert(){
        ArrayList<MenuItem> dessert = new ArrayList<>();
        for (MenuItem item: this.menuItems){
            if(item instanceof Dessert){
                dessert.add(item);
            }
        }
        return dessert;
    }
}
