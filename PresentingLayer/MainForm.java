package PresentingLayer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import java.util.List;
import BusinessLogicLayer.*;

public class MainForm {

    
    public static List<Events> events = new ArrayList<Events>();
    public static List<Client> clients = new ArrayList<Client>();
    public static void main(String[] args) throws IOException{
        Scanner scn = new Scanner(System.in);
        int Mainoption = 0;
        DataHandler dataHandler = new DataHandler();

        ////////////////////////////////////Grab Data from Reader//////////////////////////////////////
        
        events = dataHandler.getEvents();
        clients = dataHandler.getClient();

        while (Mainoption !=4) 
        {
        ////////////////////////////////////Main Menu//////////////////////////////////////
        System.out.println("---------MAIN MENU---------");
        System.out.println("1. Display Bookings");
        System.out.println("2. Display Clients");
        System.out.println("3. Add new booking");
        System.out.println("4. Exit");
        Mainoption = scn.nextInt();
        System.out.println("\n\n");

        switch(Mainoption) {
            case 1:
            System.out.println("---------DISPLAY BOOKINGS---------\n");
            System.out.println("Client ID\tEvent Type\t\tDateAndTime\t\tCity\t\tArea\t\tStreet\t\tTheme\t\t# of Adults\t\t# of Children");
            
            for (Events event : events) {
                System.out.println(event.getClientNum() + "\t" + event.getEventType() + "\t\t" + event.getEventDateandTime() + "\t\t" + 
                event.getEventCity() + "\t\t" + event.getEventArea() + "\t\t" + event.getEventStreet() + "\t\t" + event.getEventTheme() + "\t\t" + 
                event.getNumberOfAdults() + "\t\t" + event.getNumberOfChildren());
                
                System.out.println("-------Menu");
                System.out.println("Client ID\t\t\tMenu Item\t\t\tDescription\t\t\tType\t\t\tCosts"); 
                int countb = 1;
                for (MenuItem menuItem : event.getMenu()) {
                    System.out.println(countb + "\t\t\t" + menuItem.getMenuItem() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getMealType() + "\t\t\t" + menuItem.getCost()); 
                    countb++;
                }
            }
            break;

            case 2:
            System.out.println("---------DISPLAY CLIENTS---------\n");
            System.out.println("Which client? \n");
            System.out.println("Client ID\t\t\tName\t\t\tSurname\t\t\tPhone");
            for (Client client : clients) {
                System.out.println(client.getClientNum() + "\t\t\t" + client.getName() + "\t\t\t" + client.getSurname() + "\t\t\t" + client.getCellNumber());
            }

            break;

            case 3:
            System.out.println("---------ADD NEW BOOKING---------");
            System.out.println("Which client? \n");
            System.out.println("Client ID\t\t\tName\t\t\tSurname\t\t\tPhone");
            for (Client client : clients) {
                System.out.println(client.getClientNum() + "\t" + client.getName() + "\t\t" + client.getSurname() + "\t\t" + client.getCellNumber());
            }
            int clientNum = scn.nextInt();

            int option;
            System.out.println("What Type of event is this?");
            System.out.println("1. Baby Shower");
            System.out.println("2. Baptism");
            System.out.println("3. Birthday");
            System.out.println("4. Wedding");
            System.out.println("5. Year End Function");
            System.out.println("6. Cancel");
            option = scn.nextInt();
            List<MenuItem> menu = new ArrayList<MenuItem>();

            String eventType = "Not Specified";
            switch(option) {
                case 1:
                eventType = "BabyShower";
                break;

                case 2:
                eventType = "Baptism";
                break;

                case 3:
                eventType = "Birthday";
                break;

                case 4:
                eventType = "Wedding";
                break;

                case 5:
                eventType = "Year_End_Function";
                break;

                default:
                eventType = "Not Specified";
                break;
                
                }

            if (option != 6) {
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    String todaysdate = dateFormat.format(date);
                     System.out.println("Today's date : " + todaysdate);

                    //Get todays Date
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

                    boolean correct = false;
                    String eventTime = "08:00:00";

                    while (!correct) {
                        try {
                            System.out.println("Enter Date. Example (2020-01-01)");
                            String eventDateStr = scn.nextLine();
                            System.out.println("Enter Time. Example (07:00)");
                            eventTime = scn.nextLine();
                            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
                            Date timeTest = parser.parse(eventTime);
                            Date toleratedTime = parser.parse("18:01");
                            Date eventDate = new SimpleDateFormat("yyyy/MM/dd").parse(eventDateStr);
                            Date toleratedDate = new SimpleDateFormat("yyyy/MM/dd").parse(newDate);

                            if(eventDate.compareTo(toleratedDate) >= 0 && timeTest.before(toleratedTime))
                            {
                                newDate = eventDate.toString();
                                System.out.println("Event date sucessfully set.");
                            }
                            else
                            {
                                System.out.println("We do not have the date you selected available");
                                System.out.println("Note: Booking must be 15 days in advance and we only accept booking from 18:00\n");
                            }
                        }
                        catch (Exception ex) {

                        }
                    }
         
                    //Get adress Details
                    System.out.println("City: ");
                    String eventCity = scn.nextLine();
                    System.out.println("Area: ");
                    String eventArea = scn.nextLine();      
                    System.out.println("Street: ");
                    String eventStreet = scn.nextLine();
                    //Compress adress
                   
                    System.out.println("How many adults?");
                    int numberOfAdults = scn.nextInt();

                    System.out.println("How many children?");
                    int numberOfChildren = scn.nextInt();

                    System.out.println("Do you want a theme?(y/n)");
                    String eventTheme = "None";
                    if (scn.nextLine() == "y") {
                        System.out.println("Enter your theme:");
                        eventTheme = scn.nextLine();
                    }

                    if (option == 1) 
                    {
                        System.out.println("==================================Menu:=============================");
                        System.out.println("Do you want the preset Baby shower snack menu? (y/n) ");
                        if (scn.nextLine() == "y"){
                            System.out.println("Write Description:");
                            String description = scn.nextLine();
                            System.out.println("Cost per unit:");
                            double costPerUnit = scn.nextDouble();
                            double totalCost = (numberOfAdults + numberOfChildren) * costPerUnit;

                            String type = MenuOption();
                            String[] types = type.split(",");
                            for (String string : types) {
                                menu.add(new MenuItem("Snack",string,description,totalCost));
                                System.out.println("Menu item added");
                            }
                        }
                         
                    }
                    else 
                    {
                        System.out.println("==================================Menu:=============================");
                        System.out.println("Do you want starters? (yes/no) ");
                        if (scn.nextLine() == "yes"){
                            System.out.println("Write Description:");
                            String description = scn.nextLine();
                            System.out.println("Cost per unit:");
                            double costPerUnit = scn.nextDouble();
                            double totalCost = (numberOfAdults + numberOfChildren) * costPerUnit;

                            String type = MenuOption();
                            String[] types = type.split(",");
                            for (String string : types) {
                                menu.add(new MenuItem("Starter",string,description,totalCost));
                                System.out.println("Menu item added");
                            }
                        }

                        System.out.println("===========");

                        System.out.println("Do you want deserts? (yes/no) ");
                        if (scn.nextLine() == "yes"){
                            System.out.println("Write Description:");
                            String description = scn.nextLine();
                            System.out.println("Cost per unit:");
                            double costPerUnit = scn.nextDouble();
                            double totalCost = (numberOfAdults + numberOfChildren) * costPerUnit;

                            String type = MenuOption();
                            String[] types = type.split(",");
                            for (String string : types) {
                                menu.add(new MenuItem("Desert",string,description,totalCost));
                                System.out.println("Menu item added");
                            }
                        }
                    }
                    events.add(new Events(eventType,newDate + " " + eventTime,eventCity,eventArea,eventStreet,eventTheme,numberOfAdults,numberOfChildren,clientNum,menu));         
                }
                //Save all info
                dataHandler.saveBookings(events);
                dataHandler.saveClients(clients);
                break;
            }
        }
        scn.close();//commit 
    }

    public static String MenuOption() {
        
        String type = "";
        while (type == "") {
        Scanner scn = new Scanner(System.in);
        System.out.println("\nWhat type of menu?");
        System.out.println("1. Adult");
        System.out.println("2. Kids");
        System.out.println("3. Both");
        int decision = scn.nextInt();

        switch(decision) {
            case 1:
            type = "Adult";
            break;

            case 2:
            type = "Kids";
            break;

            case 3:
            type = "Kids,Adult";
            break;
            }

            scn.close();
        }
        return type;
        
        
    }
}
