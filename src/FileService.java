import menuItems.Food;
import menuItems.MenuItem;
import menuItems.MenuItemFactory;
import menuItems.MenuItemTypes;

import java.io.*;
import java.util.ArrayList;

public class FileService {
    private String fileName;
    MenuItemFactory factory =  MenuItemFactory.getInstance();

    public FileService(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<MenuItem> readFile() throws Exception {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        MenuItem item;
        String line;
        FileInputStream fluxIn = new FileInputStream(fileName);
        BufferedReader bufferIn = new BufferedReader(new InputStreamReader(fluxIn));

        while ((line = bufferIn.readLine()) != null){
            MenuItemTypes type = MenuItemTypes.valueOf(line);
            String name = bufferIn.readLine();
            float price = Float.parseFloat(bufferIn.readLine());
            boolean isGlutenFree = Boolean.valueOf(bufferIn.readLine());
            if(type == MenuItemTypes.DRINKS){
                 item = this.factory.getMenuItem(type, name,null,price,isGlutenFree);
            }else{
                String[] ingredients = bufferIn.readLine().split(",");
                item = this.factory.getMenuItem(type, name,ingredients,price,isGlutenFree);
            }
            menuItems.add(item);
        }
        return menuItems;
    }

    public void addMenuItem(MenuItem item) throws IOException {

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName, true));
        if(String.valueOf(item.getClass()).contains("Drink")){
            writer.write("DRINKS");
        }
        if(String.valueOf(item.getClass()).contains("Food")){
            writer.write("FOOD");
        }

        if(String.valueOf(item.getClass()).contains("Dessert")){
            writer.write("DESSERTS");
        }

        writer.newLine();
        writer.write(item.getName());
        writer.newLine();
        writer.write(String.valueOf(item.getPrice()));
        writer.newLine();
        writer.write(String.valueOf(item.isGlutenFree()));
        writer.newLine();

        this.writeIngredients(item, writer);

        writer.close();
    }

    private void writeIngredients(MenuItem menuItem, BufferedWriter writer) throws IOException {
        if(menuItem.getIngredients() != null){
            StringBuilder builder = new StringBuilder();
            for(String ingredient: menuItem.getIngredients()){
                builder.append(ingredient);
                builder.append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            String ingredients = builder.toString();

            writer.write(ingredients);
        }
    }
}
