import menuItems.MenuItem;

import java.util.ArrayList;

public class Order {
    private Menu menu;
    private ArrayList<MenuItem> order = new ArrayList<>();


    public Order(Menu menu) {
        this.menu = menu;
    }

    public void orderItem(MenuItem menuItem){
        this.order.add(menuItem);
    }

    public float getTotalOfOrder(){
        float sum = 0;
        for(MenuItem menuItem : this.order){
            sum += menuItem.getPrice();
        }
        return sum;
    }

    public ArrayList<MenuItem> getAllMenuItems(){
        return this.order;
    }
}
