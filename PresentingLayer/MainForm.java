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

        while (Mainoption !=5) 
        {
        ////////////////////////////////////Main Menu//////////////////////////////////////
        System.out.println("---------MAIN MENU---------");
        System.out.println("1. Display Bookings");
        System.out.println("2. Display Clients");
        System.out.println("3. Add new booking");
        System.out.println("4. Add new client");
        System.out.println("5. Exit");
        Mainoption = scn.nextInt();
        System.out.println("\n\n");

        switch(Mainoption) {
            case 1:
            System.out.println("----------------------------------------------------------------------DISPLAY BOOKINGS--------------------------------------------------------------------------------\n");
            System.out.format("%1s%20s%30s%20s%20s%30s%20s%15s%15s", "Client ID", "Event Type", "DateAndTime", "City","Area","Street","Theme","Adutls", "Children\n");
            System.out.println("__________________________________________________________________________________________________________________________________________________________________________________\n");
            for (Events event : events) {
                System.out.format("%1s%28s%30s%21s%20s%30s%20s%15s%15s", event.getClientNum(), event.getEventType(), event.getEventDateandTime(), 
                event.getEventCity(),event.getEventArea(),event.getEventStreet(),event.getEventTheme(),event.getNumberOfAdults(), event.getNumberOfChildren() + "\n");
            
                
                System.out.println("-------Menu-----------");
                System.out.format("%1s%20s%20s%20s%20s", "Client ID", "Menu Item", "Description", "Type","Costs\n\n");
                int countb = 1;
                for (MenuItem menuItem : event.getMenu()) {
                    System.out.format("%1s%28s%20s%20s%20s", countb, menuItem.getMenuItem(), menuItem.getDescription(),menuItem.getMealType(),menuItem.getCost()+"\n");
                    countb++;
                }
                System.out.println("----------------------\n");
            }
            System.out.println("__________________________________________________________________________________________________________________________________________________________________________________\n");
            break;

            case 2:
            System.out.println("--------------------------------------DISPLAY CLIENTS------------------------------------------\n");
            System.out.format("%1s%20s%20s%20s", "Client ID", "Name", "Surname", "Phone\n");
            System.out.println("_______________________________________________________________________________________________\n");
            //System.out.println("Client ID\t\t\tName\t\t\tSurname\t\t\tPhone");
            for (Client client : clients) {
                System.out.format("%1s%28s%20s%22s", client.getClientNum(),client.getName(), client.getSurname(), client.getCellNumber() +"\n");
            }
            System.out.println("_______________________________________________________________________________________________\n");

            break;

            case 3:
            System.out.println("---------ADD NEW BOOKING---------\n");
            System.out.println("\n-----------CLIENTS\n");
            System.out.format("%1s%20s%20s%20s", "Client ID", "Name", "Surname", "Phone\n");
            System.out.println("_______________________________________________________________________________________________\n");
            //System.out.println("Client ID\t\t\tName\t\t\tSurname\t\t\tPhone");
            for (Client client : clients) {
                System.out.format("%1s%28s%20s%22s", client.getClientNum(),client.getName(), client.getSurname(), client.getCellNumber() +"\n");
            }
            System.out.println("_______________________________________________________________________________________________\n");

            System.out.print("Enter Client Number (0 = cancel): ");
            int clientNum = scn.nextInt();

            if (clientNum != 0) {
                System.out.println("\n-----------EVENT TYPE\n");
                int option;
                System.out.println("What Type of event is this?");
                System.out.println("1. Baby Shower");
                System.out.println("2. Baptism");
                System.out.println("3. Birthday");
                System.out.println("4. Wedding");
                System.out.println("5. Year End Function");
                System.out.println("6. Cancel\n");
                System.out.print("Enter Menu Option: ");
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

                if (option != 6 && option < 7) {
                        System.out.println("\n-----------DATE AND TIME\n");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
                        String eventTime = "08:00";

                        while (!correct) {
                            try {
                                eventTime = scn.nextLine();
                                System.out.println("Enter Date. Example (2020-01-01)");
                                String eventDateStr = scn.nextLine();
                                System.out.println("Enter Time. Example (07:00)");
                                eventTime = scn.nextLine();
                                SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
                                Date timeTest = parser.parse(eventTime);
                                Date toleratedTime = parser.parse("18:01");
                                Date eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(eventDateStr);
                                Date toleratedDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDate);

                                if(eventDate.compareTo(toleratedDate) >= 0 && timeTest.before(toleratedTime))
                                {
                                    newDate = eventDate.toString();
                                    System.out.println("Event date sucessfully set.");
                                    correct = true;
                                }
                                else
                                {
                                    System.out.println("\nWe do not have the date you selected available");
                                    System.out.println("Note: Booking must be 15 days in advance and we only accept booking from 18:00\n");
                                }
                            }
                            catch (Exception ex) {
                                System.out.println("\n[!]You have entered the date incorrectly");
                            }
                        }
            
                        System.out.println("\n-----------ADDRESS\n");
                        //Get adress Details
                        System.out.println("City: ");
                        String eventCity = scn.nextLine();
                        System.out.println("Area: ");
                        String eventArea = scn.nextLine();      
                        System.out.println("Street: ");
                        String eventStreet = scn.nextLine();
                        //Compress adress
                    
                        System.out.println("\n-----------PARTICIPANTS\n");
                        System.out.println("How many adults?");
                        int numberOfAdults = scn.nextInt();

                        System.out.println("How many children?");
                        int numberOfChildren = scn.nextInt();

                        System.out.println("\n-----------THEME\n");
                        System.out.println("Do you want a theme?(y/n)");
                        String answer = scn.nextLine();
                        String eventTheme = "None";
                        if (answer == "y") {
                            System.out.println("Enter your theme:");
                            eventTheme = scn.nextLine();
                        }

                        System.out.println("\n-----------MENU\n");
                        if (option == 1) 
                        {
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
                        events.add(new Events(clientNum,eventType,newDate + " " + eventTime,eventCity,eventArea,eventStreet,eventTheme,numberOfAdults,numberOfChildren,menu));         
                        dataHandler.saveBookings(events);
                        System.out.println("Booking Added");
                    }
                else {
                    System.out.println("[!]This operation has been stopped");
                }
                }
                break;

                case 4: 
                System.out.println("--------------------------------------ADD CLIENT------------------------------------------\n");
                String name = scn.nextLine();
                System.out.println("Enter Client Name");
                name = scn.nextLine();
                System.out.println("Enter Client Surname");
                String surname = scn.nextLine();
                System.out.println("Enter Client Phone Number");
                String phone = scn.nextLine();

                int previous = 0;
                clientNum = clients.size()+1;

                //Search for client number gab
                for (Client client : clients) {
                    if ((client.getClientNum() - previous) == 2) {
                        clientNum = client.getClientNum()-1;
                    }
                    if (previous != client.getClientNum()) {
                        previous = client.getClientNum();
                    }
                }

                clients.add(new Client(clientNum, name, surname, phone));
                dataHandler.saveClients(clients);
                break;

            }
        }
        scn.close();//commit 
    }

    public static String MenuOption() {
        
        String type = "";
        Scanner scn = new Scanner(System.in);
        while (type == "") {
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
        }
        scn.close();
        return type;    
    }
}
