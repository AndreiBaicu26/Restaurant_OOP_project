package menuItems;


import java.util.Arrays;

public class Food extends MenuItem {

    private String ingredients[];
    private int idF;
    public Food(String name, String[] ingredients, float price, boolean isGlutenFree) {
        super(name, price,isGlutenFree);
        this.ingredients = ingredients;
        this.idF = this.id;
    }


    @Override
    public int getId() {
        return this.idF;
    }

    @Override
    public void printMenuItem() {
        String print = this.idF +". " + "Name: " + this.getName() + "\n" +
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
