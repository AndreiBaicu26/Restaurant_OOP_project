import exceptions.InvalidMenuTypeException;
import menuItems.MenuItem;
import menuItems.MenuItemFactory;
import menuItems.MenuItemTypes;

import java.util.ArrayList;
import java.util.Scanner;

public class Printer {

    private Menu menu;
    private ArrayList<String> waiters = new ArrayList<>();
    private boolean isNewClient = true;
    Scanner scanner = new Scanner(System.in);
    public Printer() {
        this.menu = Menu.getINSTANCE();
        this.createWaiters();
    }

    public void welcome() {
        System.out.println("------------Welcome to Andrei's restaurant------------\n");
        System.out.println("What would you like to do?");
        int option = -2;
        while (option != 3) {
            if(this.isNewClient){
                this.printFirstOptions();
            }
            System.out.println("Enter your option: ");
            option = this.getOption();
            if(option != -2) {
                switch (option) {
                    case (0): {
                        System.out.println("____________________");
                        System.out.println("    Your waiter will be " + this.getRandomWaiter());
                        System.out.println("____________________");
                        this.isNewClient = false;
                        this.selectMenuItems();
                        break;
                    }
                    case(1):{

                    }
                    case (2): {
                        System.out.println("No report available");
                        break;
                    }
                    case (3): {
                        System.out.println("Exiting...");
//                    MenuItemFactory factory = new MenuItemFactory();
//                    String[] ing = {"Salad", "Tomatoes"};
//                    MenuItem item = factory.getMenuItem(MenuItemTypes.FOOD, "Salad",ing , 23,true);
//                    FileService reader = new FileService("menu.txt");
//                    reader.addMenuItem(item);
                        break;
                    }
                    default:{
                        System.out.println("Invalid input!");
                    }
                }
            }
        }
    }

    private int getOption() {
        int option;
        try{
            option = Integer.parseInt(this.scanner.nextLine());
            return option;
        }catch (NumberFormatException e){
            System.out.println("Invalid input!");
            return -2;
        }
    }

    private void createNewMenuItem(){
        System.out.println("Insert item type (FOOD, DRINKS, DESSERTS): ");

        String type = this.scanner.nextLine();
        while(!Validator.validateMenuItemType(type)){
            type = this.scanner.nextLine();
        }
        MenuItemTypes selectedType = MenuItemTypes.valueOf(type);

        String name = this.scanner.nextLine();
        while (!Validator.validatePrice(name)){
            name = this.scanner.nextLine();
        }
        String nameSelected = name;

        String price = this.scanner.nextLine();
        while (!Validator.validatePrice(price)){
            price = this.scanner.nextLine();
        }
        float priceSelected = Float.parseFloat(price);

        String hasGluten = this.scanner.nextLine();
        while (!Validator.validateHasGluten(hasGluten)){
            hasGluten = this.scanner.nextLine();
        }
        Boolean hasGlutenSelected = Boolean.valueOf(hasGluten);

        //TODO: Add method for adding ingredients to food
        MenuItemFactory factory = new MenuItemFactory();

            try {
                if(selectedType.equals(MenuItemTypes.DRINKS)) {
                    MenuItem item = factory.getMenuItem(selectedType, nameSelected, null, priceSelected, !hasGlutenSelected);
                    this.menu.addMenuItem(item);
                }else{
                    MenuItem item = factory.getMenuItem(selectedType, nameSelected, null, priceSelected, !hasGlutenSelected);
                    this.menu.addMenuItem(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }



    }
    private void printFirstOptions(){
        System.out.println("\n");
        System.out.println("0: See the menu.");
        System.out.println("1: Add new item to menu.");
        System.out.println("2: Generate orders report.");
        System.out.println("3: Exit.\n");
    }

    private void selectMenuItems(){
        System.out.println("\n-------- What would you like to have? ------------ ");
        Order order = new Order(Menu.getINSTANCE());
        int option = -1;
        while(option != -2){
            this.printMenuOptions();
            System.out.println("Your option:");
             option = this.getOption();
            if(option!= -2){
                switch (option){
                    case (0):{
                        this.displayItems(this.menu.getAllMenu());
                        this.selectMenuItem(order, this.menu.getAllMenu());
                        break;
                    }
                    case (1):{
                        this.displayItems(this.menu.getAllDrinks());
                        this.selectMenuItem(order, this.menu.getAllDrinks());
                        break;
                    }
                    case(2):{
                        this.displayItems(this.menu.getAllFood());
                        this.selectMenuItem(order, this.menu.getAllFood());
                        break;
                    }
                    case(3):{
                        this.displayItems(this.menu.getAllDessert());
                        this.selectMenuItem(order, this.menu.getAllDessert());
                        break;
                    }
                    case(4):{
                        this.printCurrentOrder(order);
                        break;
                    }
                    case(5):{
                        this.printReceipt(order);
                        option = -2;
                        break;
                    }
                    default:{
                        System.out.println("Invalid option!");
                    }
                }
            }
        }
    }

    private void displayItems(ArrayList<MenuItem> menuItems){
        for(MenuItem menuItem : menuItems){
            menuItem.printMenuItem();
        }
        System.out.println("\n0: Go Back");
    }

    private void selectMenuItem(Order order,  ArrayList<MenuItem> menuItems){
        ArrayList<Integer> menuItemsIds = new ArrayList<>();
        for(MenuItem menuItem : menuItems){
            menuItemsIds.add(menuItem.getId());
        }
        int option = -2;
        while(option != 0){
            System.out.println("Choose item: ");
            option = this.getOption();
            if(this.isOptionValid(option, menuItemsIds)){
                order.orderItem(this.menu.getMenuItem(option-1));
                System.out.println( this.menu.getMenuItem(option-1).getName()+ " added successfully!");
            }else if(option != 0){
                System.out.println("Not a valid option!");
            }
        }
    }

    private boolean isOptionValid(int option, ArrayList<Integer> menuItemsIds) {
        if(menuItemsIds.contains(option)){
            return true;
        }
        return false;
    }

    private void printMenuOptions() {
        System.out.println("\n-------- Options: --------------");
        System.out.println("0 : Show all menu");
        System.out.println("1 : Show Drinks");
        System.out.println("2 : Show Food");
        System.out.println("3 : Show Dessert");
        System.out.println("4 : Visualize ordered items.");
        System.out.println("5 : Get total order.");
    }

    private void printReceipt(Order order){
        System.out.println("_______________");
        System.out.println("================ Receipt ================");
        for(MenuItem menuItem : order.getAllMenuItems()){
            menuItem.printMenuItem();
        }
        System.out.println("===== TOTAL TO PAY: " + order.getTotalOfOrder() + " EUR =====");
        System.out.println("----- Thank you for coming! -----");
        this.isNewClient = true;
    }

    private void printCurrentOrder(Order order){
        System.out.println("_______________");
        if(order.getAllMenuItems().size() == 0){
            System.out.println("Your order is empty. ");
        }else{
            System.out.println("Your current order is: ");
            for(MenuItem menuItem : order.getAllMenuItems()){
                menuItem.printMenuItem();
            }
        }
        System.out.println("_______________");
    }

    private void createWaiters(){
        this.waiters.add("Andrei");
        this.waiters.add("Adrian");
        this.waiters.add("Bogdan");
        this.waiters.add("Ioana");
        this.waiters.add("Denisa");
    }

    private String getRandomWaiter(){
        return this.waiters.get((int)(Math.random() * (this.waiters.size() -1)));
    }

}
