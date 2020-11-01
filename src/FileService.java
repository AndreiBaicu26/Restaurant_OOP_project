import menuItems.Food;
import menuItems.MenuItem;
import menuItems.MenuItemFactory;
import menuItems.MenuItemTypes;

import java.io.*;
import java.nio.Buffer;
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

    public void addMenuItem(MenuItem item, BufferedWriter writer) throws IOException {

//        BufferedWriter writer = new BufferedWriter(
//                new FileWriter(fileName, true));
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


    }
    public void rewriteMenu() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        Menu menu = Menu.getINSTANCE();

        for(MenuItem menuItem: menu.getAllMenu()){
            this.addMenuItem(menuItem, writer);
        }
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
            writer.newLine();

        }
    }

    public void createReportFile(){
        File reportFile = new File(this.fileName);
        try {
            if (reportFile.createNewFile()) {
                System.out.println("File created: " + reportFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void writeToReportFile(Report report, String date) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("================ Report for " + date+ " ================");
            writer.newLine();
            writer.write("================ Summary ================");
            writer.newLine();
            this.writeSummary(report, writer);
            writer.newLine();
            writer.write("================ Food ================");
            writer.newLine();
            this.writeSummaryFood(report,writer);
            writer.newLine();
            writer.write("================ Drinks ================");
            writer.newLine();
            this.writeSummaryDrinks(report,writer);
            writer.newLine();
            writer.write("================ Desserts ================");
            writer.newLine();
            this.writeSummaryDesserts(report,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSummary(Report report, BufferedWriter writer) throws IOException {
        writer.write("Number of items sold: " + report.getNumberOfUnitsSold());
        writer.newLine();
        writer.write("Best sold product: " + report.getBestSold().getName());
        writer.newLine();
        writer.write("Units sold: "+ report.getNumberOfBestUnitSold());
        writer.newLine();
        writer.write("Average items ordered: " + report.getAverageNumberOfOrderedItems());
    }

    private void writeSummaryFood(Report report, BufferedWriter writer) throws IOException {
        writer.write("Number of items sold: " + report.getNumberOfFoodItemsSold());
        writer.newLine();
        if(report.getMostSoldFood() != null){
            writer.write("Best sold food item: " + report.getMostSoldFood().getName());
        }else{
            writer.write("No food ordered");
        }

    }

    private void writeSummaryDrinks(Report report, BufferedWriter writer) throws IOException {
        writer.write("Number of drinks sold: " + report.getNumberOfDrinksSold());
        writer.newLine();
        if(report.getMostSoldDrink() != null){
            writer.write("Best sold drink: " + report.getMostSoldDrink().getName());
        }else{
            writer.write("No drinks ordered");
        }
    }

    private void writeSummaryDesserts(Report report, BufferedWriter writer) throws IOException {
        writer.write("Number of desserts sold: " + report.getNumberOfDessertsSold());
        writer.newLine();
        if(report.getMostSoldDessert() != null){
            writer.write("Best sold dessert: " + report.getMostSoldDessert().getName());
        }else{
            writer.write("No desserts ordered");
        }
    }
}
