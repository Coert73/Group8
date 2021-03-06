package PresentingLayer;

import java.lang.ProcessBuilder.Redirect.Type;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import javax.xml.crypto.Data;

import DataAccessLayer.ReadData;
import DataAccessLayer.WriteData;

import java.util.List;
import BusinessLogicLayer.*;

public class MainForm {

    ////////////////TODO Test everything out please and fix the bugs///////////////////////////
    public static List<Events> events = new ArrayList<Events>();
    public static List<Client> clients = new ArrayList<Client>();
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int Mainoption = 0;
        ReadData reader = new ReadData();

        ////////////////////////////////////Grab Data from Reader//////////////////////////////////////
        reader.Read();
        events = reader.getEvents();
        clients = reader.getClients();

        while (Mainoption !=4)
        ////////////////////////////////////Main Menu//////////////////////////////////////
        System.out.println("---------MAIN MENU---------");
        System.out.println("1. Display Bookings");
        System.out.println("2. Display Clients");
        System.out.println("3. Add new booking");
        System.out.println("4. Edit booking");
        System.out.println("5. Edit client");
        System.out.println("6. Edit menu items");
        System.out.println("7. Exit");
        Mainoption = scn.nextInt();
        System.out.println("\n\n");

        switch(Mainoption) {
            case 1:
            System.out.println("---------DISPLAY BOOKINGS---------\n");
            System.out.println("Client ID\t\t\tEvent Type\t\t\tDateAndTime\t\t\tCity\t\t\tArea\t\t\tStreet\t\t\tTheme\t\t\t# of Adults\t\t\t# of Children");
            
            for (Events event : events) {
                System.out.println(event.getClientNum() + "\t\t\t" + event.getEventType() + "\t\t\t" + event.getEventDateandTime() + "\t\t\t" + event.getEventCity() + "\t\t\t" + event.getEventArea() + "\t\t\t" + event.getEventStreet() + "\t\t\t" + event.getEventTheme() + "\t\t\t" + event.getNumberOfAdults() + event.getNumberOfChildren());
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
                System.out.println(client.getClientNum() + "\t\t\t" + client.getName() + "\t\t\t" + client.getSurname() + "\t\t\t" + client.getCellNumber());
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

            String eventType;
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
                
                }

            if (option != 6) {
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    String todaysdate = dateFormat.format(date);
                     System.out.println("Today's date : " + todaysdate);

                    //Get todays Date
                    Date date1 = Calendar.getInstance().getTime();  //changed

                    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
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

                    //if(eventDateandTime => newDate,"Available","Not available")
                    //{
                        System.out.println("Booking date available.");
                        System.out.println("Event date sucessfully set.");
                    //}
                    else
                    {
                        System.out.println("We do not have the date you selected available, please note that you have to place the booking 15 days in advance");
                    }
                
                    //Get adress Details
                    System.out.println("City: ");
                    String eventCity = scn.nextLine();
                    System.out.println("Area: ");
                    String eventArea = scn.nextLine();      
                    System.out.println("Street: ");
                    String eventStreet = scn.nextLine();
                    System.out.println("House Number: ");
                    String eventHuoseNumber = scn.nextLine();
                    //Compress adress
                   
                    System.out.println("How many adults?");
                    int numberOfAdults = scn.nextInt();

                    System.out.println("How many children?");
                    int numberOfChildren = scn.nextInt();

                    System.out.println("Do you want a theme?(Yes/No)");
                    String eventTheme = scn.nextLine();

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
                    events.add(new Events(eventType,eventDateandTime,eventCity,eventArea,eventStreet,eventTheme,numberOfAdults,numberOfChildren,clientNum,menu));         
                }}
            break;


                case 4:
                System.out.println("---------EDIT BOOKING---------\n");
                System.out.println("Which booking? \n");
                System.out.println("Client ID\t\t\tEvent Type\t\t\tDateAndTime\t\t\tCity\t\t\tArea\t\t\tStreet\t\t\tTheme\t\t\t# of Adults\t\t\t# of Children");
                for (Events event : events) {
                    System.out.println(event.getClientNum() + "\t\t\t" + event.getEventType() + "\t\t\t" + event.getEventDateandTime() + "\t\t\t" + event.getEventCity() + "\t\t\t" + event.getEventArea() + "\t\t\t" + event.getEventStreet() + "\t\t\t" + event.getEventTheme() + "\t\t\t" + event.getNumberOfAdults() + event.getNumberOfChildren());
                
                }
                int bookingNum = scn.nextInt();
                System.out.println("\nWhich field?\n");
                Field[] fields = Events.class.getFields();
                int itemNum = 1;

                for (Field field : fields) {
                    System.out.println(itemNum + ". " + field);
                    itemNum++;
                }
                int fieldNum = scn.nextInt();

                System.out.println("\nEnter new value. Note: Date format (2000-01-01)\n");
                String newValue = scn.nextLine();

                switch (fieldNum) 
                {
                    case 1:
                    ValidateValue(newValue,"Integer");
                    events.get(itemNum).setClientNum(newValue);
                    break;
                    case 2:
                    ValidateValue(newValue,"String");
                    events.get(itemNum).setEventType(newValue);
                    break;
                    case 3:
                    ValidateValue(newValue,"Date");
                    events.get(itemNum).setEventDateandTime(newValue);
                    break;
                    case 4:
                    ValidateValue(newValue,"String");
                    events.get(itemNum).setEventCity(newValue);
                    break;
                    case 5:
                    ValidateValue(newValue,"String");
                    events.get(itemNum).setEventArea(newValue);
                    break;
                    case 6:
                    ValidateValue(newValue,"String");
                    events.get(itemNum).setEventStreet(newValue);
                    break;
                    case 7:
                    ValidateValue(newValue,"String");
                    events.get(itemNum).setEventTheme(newValue);
                    break;
                    case 8:
                    ValidateValue(newValue,"Integer");
                    events.get(itemNum).setNumberOfAdults(newValue);
                    break;
                    case 9:
                    ValidateValue(newValue,"Integer");
                    events.get(itemNum).setNumberOfChildren(newValue);
                    break;
                }
                System.out.println("\nField Edited\n");
                break;

                case 5:
                System.out.println("---------EDIT CLIENTS---------\n");
                System.out.println("Which client? \n");
                System.out.println("Client ID\t\t\tName\t\t\tSurname\t\t\tPhone");
                for (Client client : clients) {
                    System.out.println(client.getClientNum() + "\t\t\t" + client.getName() + "\t\t\t" + client.getSurname() + "\t\t\t" + client.getCellNumber());
                }

//TODO: code an edit function for client. Ask them which client and then what field and then the change value
    
                break;

                case 6:
                System.out.println("---------EDIT MENU ITEMS---------\n");
                System.out.println("ID\t\t\tMenu Item\t\t\tDescription\t\t\tType\t\t\tCosts"); 
                for (MenuItem menuItem : event.getMenu()) {
                    System.out.println(countb + "\t\t\t" + menuItem.getMenuItem() + "\t\t\t" + menuItem.getDescription() + "\t\t\t" + menuItem.getMealType() + "\t\t\t" + menuItem.getCost()); 
                    count++;
                }

//TODO: code an edit function for menu items. Ask them which menu item and then what field and then the change value
    
                break;
                WriteData write = new WriteData();
                //Save all info
                write.SaveBooking(events);
                write.SaveClients(clients);

                default:
                
                break;
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

    public void ValidateValue(String value, String excpectedType) throws Exception {
        boolean correct = false;
        Scanner scn = new Scanner(System.in);

        while (!correct) {
            try {
                if (excpectedType == "Integer") {
                    Integer.parseInt(value);
                    correct = true;
                }
                else if (excpectedType == "String") {
                    char[] c = value.toCharArray();
                    int letters = 0;
                    for (char character : c) {
                        if (Character.isLetter(character)) {
                            letters++;
                            break;
                        }
                    }

                    if (letters == 0) {
                        System.out.println("Your value only consists of numbers. Are you sure you want to enter this value? (y/n)");
                        if (scn.nextLine() == "y") {
                            correct = true;
                        }
                    }

                }
                else if (excpectedType == "Double") {
                    Double.parseDouble(value);
                }
                else if (excpectedType == "Date") {
                    Date.parse(value);
                }
            }
            catch (Exception ex) {
                System.out.println("\nThe value you have entered is not the correct type. Please try again.");
            }
           
        }
    }
}
