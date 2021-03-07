package BusinessLogicLayer;
import DataAccessLayer.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String menuItem; // Either Starter, Main, Dessert or Drinks.
    private String description; // Describes the meal e.g Fennel salad with blue cheese dressing
    private String mealType; // Could be changed to a boolean, this is just to specify if the meal is for a child or adult
    private Double cost; // The cost of the meal per person

    public String getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(String MenuItem) {
        this.menuItem=MenuItem;
    }

    public Double getCost() {
        return cost;
    }
    public void setCost(Double Cost) {
        this.cost =Cost;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String Description) {
        this.description=Description;
    }

    public String getMealType() {
        return mealType;
    }
    public void setCost(String MealType) {
        this.mealType =MealType;
    }

    public  MenuItem() {
    
    }

    public MenuItem(String MenuItem,String Description,String MealType, Double Cost) {
        this.menuItem =MenuItem;
        this.description =Description;
        this.mealType =MealType;
        this.cost=Cost;
    }
    public List ShowMenu() {

        List<MenuItem> item = new ArrayList<>();
        List temp = new ArrayList<>();
        Read menu = new Read();
        temp = menu.getMenuItems();
    
        for (Object mi : temp) {
            String menuItem = mi.toString();
            String [] data = menuItem.split(",");
            Double c =Double.parseDouble(data[3]);
    
            item.add(new MenuItem(data[0], data[1], data[2], c));
        }
        return item;
    }
    @Override
     public String toString()
    {
       return String.format("%8s\t%8s\t\t%8s\t\t%8s", getMenuItem(),getDescription(),getMealType(),getCost());
    }
    
}

