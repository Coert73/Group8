package BusinessLogicLayer;
import java.util.*;

public class Wedding implements Events{
    
    String eventType = "Wedding";
    String eventDateandTime;
    String eventCity;
    String eventArea;
    String eventStreet;
    String eventAdress;
    String eventHuoseNumber;
    int numberOfAdults;
    int numberOfChildren;
    
    //menu
    List<String> menu = new ArrayList<String>();

	public void getdetails() 
    {
		
        Scanner scn = new Scanner(System.in);
        System.out.println("Date & Time: ");
        eventDateandTime = scn.nextLine();

        //Get adress Details
        System.out.println("City: ");
        eventCity = scn.nextLine();
        System.out.println("Area: ");
        eventArea = scn.nextLine();      
        System.out.println("Street: ");
        eventStreet = scn.nextLine();
        System.out.println("House Number: ");
        eventHuoseNumber = scn.nextLine();
        //Compress adress
        eventAdress = eventCity + " , " + eventArea + " , " + eventStreet + " , " + eventHuoseNumber + ". ";

        System.out.println("How many adults?");
        numberOfAdults = scn.nextInt();

        System.out.println("How many children?");
        numberOfChildren = scn.nextInt();

        System.out.println("==================================Menu:=============================");
        System.out.println("Do you want kids preset menu? ");
        if (scn.nextLine() == "yes"){
            menu.add("#kids-meal");
        }

        System.out.println("Do you want adult preset menu? ");
        if (scn.nextLine() == "yes"){
            menu.add("#adult-meal");
        }
        
        System.out.println("Do you want desert? ");
        if (scn.nextLine() == "yes"){
            menu.add("#deserts");
        }

        scn.close();
    }

    public Wedding(String eventType, String eventDateandTime, String eventCity, String eventArea, String eventStreet,
            String eventAdress, String eventHuoseNumber, int numberOfAdults, int numberOfChildren, List<String> menu) {
        this.eventType = eventType;
        this.eventDateandTime = eventDateandTime;
        this.eventCity = eventCity;
        this.eventArea = eventArea;
        this.eventStreet = eventStreet;
        this.eventAdress = eventAdress;
        this.eventHuoseNumber = eventHuoseNumber;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.menu = menu;
    }
}