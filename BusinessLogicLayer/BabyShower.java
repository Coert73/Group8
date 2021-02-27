package BusinessLogicLayer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class BabyShower implements Events{
    
    String eventType = "Baby Shower";
    String eventDateandTime;
    String eventCity;
    String eventArea;
    String eventStreet;
    String eventAdress;
    String eventHuoseNumber;
    String eventTheme;
    int numberOfAdults;
    int numberOfChildren;
    
    
    //menu
    String BabyShowerBouquet;

	@Override
	public void getdetails() 
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Date & Time: ");
        eventDateandTime = scn.nextLine();

        //Get todays Date
        Date date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                String strDate = dateFormat.format(date);  
                
        

        //Given Date in String format	  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar c = Calendar.getInstance();
	    try
        {
	    //Setting the date to the given date
	       c.setTime(sdf.parse(strDate));
	    }catch(ParseException e)
        {
		    e.printStackTrace();
	    }
	    //Number of Days to add
	    c.add(Calendar.DAY_OF_MONTH, 15);  
    	//Date after adding the days to the given date
	    String newDate = sdf.format(c.getTime());  
	    //Displaying the new Date after addition of Days
	    System.out.println("This will be the next available slot: "+ newDate);

        if(eventDateandTime => newDate,"Available","Not available")
        {
            System.out.println("Booking date available.");
            System.out.println("Event date sucessfully set.");
        }
        else
        {
            System.out.println("We do not have the date you selected available, please note that you have to place the booking 15 days in advance");
        }
        
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

        System.out.println("Do you want a theme?(Yes/No)");
        eventTheme = scn.nextLine();


        System.out.println("==================================Menu:=============================");
        System.out.println("Do you want the preset Baby shower snack menu? ");
        BabyShowerBouquet = scn.nextLine();

        scn.close();//commit 
    }

    @Override
    public void getDetails() {
        // TODO Auto-generated method stub

    }
}