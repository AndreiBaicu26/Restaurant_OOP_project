package menuItems;


import java.util.Arrays;


public class Dessert extends MenuItem {

    private String ingredients[];
    private int idDessert;
    public Dessert(String name, String[] ingredients, float price, boolean isGlutenFree) {
        super(name, price, isGlutenFree);
        this.ingredients = ingredients;
        this.idDessert = this.id;
    }



    @Override
    public int getId() {
        return this.idDessert;
    }

    @Override
    public void printMenuItem() {
        String print = this.idDessert +". " + "Name: " + this.getName() + "\n" +
                "   Price: " + this.getPrice() + "\n" +
                "   Has gluten: " +this.isGlutenFree() + "\n" +
                "   Ingredients: " + Arrays.toString(this.ingredients) + "\n";
        System.out.println(print);
    }

    @Override
    public String[] getIngredients() {
        return this.ingredients;
    }
}
