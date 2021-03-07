package BusinessLogicLayer;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<MenuItem> items;
    private int adults;
    private int kids;


    public int getAdults()
    {return adults;}
    public void setAdults(int Adults) {
        this.adults =Adults;
    }

    public int getKids()
    {return kids;}
    public void setKids(int Kids) {
        this.kids =Kids;
    }
    
    public Menu() {
        this.items= new ArrayList<MenuItem>();
    }

    public void addMenuItem(MenuItem item)
    {
        this.items.add(item);
    }

    public void removeMenuItem(MenuItem item)
    {
        this.items.remove(item);
    }

    public Double MenuTotal(int people) {
        
        Double sum = 0.00;
        Double total=0.00;
        int totalpeople=kids+adults;
        for(MenuItem item : items)
        {
            sum += item.getCost();
        }

        Double discount =sum*0.15;
        if (totalpeople>40) {
            total = (people*sum)-discount;
            return total;
        }
        else
        {
            return sum*people;
        }
        
    }   
}
