import menuItems.Dessert;
import menuItems.Drink;
import menuItems.Food;
import menuItems.MenuItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Report {
    private ArrayList<Order> orders;
    private MenuItem bestSold;
    private int numberOfUnitsSold;
    private int numberOfBestUnitSold;
    public Report(ArrayList<Order> orders) {
        this.orders = orders;
        this.numberOfBestUnitSold = 0;
        this.numberOfUnitsSold = 0;
    }

    public void generateReport(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        FileService fileService =  new FileService("src/"+formatter.format(date) + ".txt");
        this.calculateNumbers();
        fileService.createReportFile();
        fileService.writeToReportFile(this, formatter.format(date));
    }

    private void calculateNumbers(){
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                this.numberOfUnitsSold++;
                if(map.containsKey(menuItem)){
                    map.put(menuItem, map.get(menuItem)+1);
                }else{
                    map.put(menuItem, 1);

                }
            }
        }
        int max = -1;
        for (MenuItem key : map.keySet()) {
            if(map.get(key) > max){
                max = map.get(key);
                this.numberOfBestUnitSold = map.get(key);
                this.bestSold = key;
            }
        }
    }

    public float getAverageNumberOfOrderedItems(){
        float numberOfOrederedItems = 0;
        for(Order order : orders){
            numberOfOrederedItems += order.getAllMenuItems().size();
        }
        return numberOfOrederedItems/this.orders.size();

    }

    public MenuItem getMostSoldDrink(){
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                if(menuItem instanceof Drink) {
                    if (map.containsKey(menuItem)) {
                        map.put(menuItem, map.get(menuItem)+1);
                    } else {
                        map.put(menuItem, 1);
                    }
                }
            }
        }
       return this.getMostSold(map);
    }

    public MenuItem getMostSoldFood(){
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                if(menuItem instanceof Food) {
                    if (map.containsKey(menuItem)) {
                        map.put(menuItem, map.get(menuItem)+1);
                    } else {
                        map.put(menuItem, 1);
                    }
                }
            }
        }
        return this.getMostSold(map);
    }


    public MenuItem getMostSoldDessert(){
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                if(menuItem instanceof Dessert) {
                    if (map.containsKey(menuItem)) {
                        map.put(menuItem, map.get(menuItem)+1);
                    } else {
                        map.put(menuItem, 1);
                    }
                }
            }
        }
        return this.getMostSold(map);
    }

    private MenuItem getMostSold(HashMap<MenuItem, Integer> map){
        int max = 0;
        MenuItem best = null;
        for (MenuItem key : map.keySet()) {
            if(map.get(key) > max){
                max = map.get(key);
                best = key;
            }
        }
        return best;
    }

    public MenuItem getBestSold() {
        return bestSold;
    }

    public int getNumberOfUnitsSold() {
        return numberOfUnitsSold;
    }

    public int getNumberOfBestUnitSold() {
        return numberOfBestUnitSold;
    }

    public int getNumberOfDrinksSold() {
        int numberOfDrinksSold = 0;
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                if(menuItem instanceof Drink) {
                    numberOfDrinksSold++;
                }
            }
        }
        return numberOfDrinksSold;
    }

    public int getNumberOfFoodItemsSold() {
        int numberOfFoodItemsSold = 0;
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                if(menuItem instanceof Food) {
                    numberOfFoodItemsSold++;
                }
            }
        }

        return numberOfFoodItemsSold;
    }

    public int getNumberOfDessertsSold() {
        int numberOfDessertsSold = 0;
        HashMap<MenuItem, Integer> map = new HashMap<>();
        for(Order order : orders){
            for(MenuItem menuItem : order.getAllMenuItems()){
                if(menuItem instanceof Dessert) {
                    numberOfDessertsSold++;
                }
            }
        }
        return numberOfDessertsSold;
    }

}
