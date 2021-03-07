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
        int numberOfAdults = 0;
        int numberOfChildren = 0;
        
        String eventCity = "";
        String eventArea = "";     
        String eventStreet = "";
        String eventTheme = "None";
        String eventType="";
        String newDate="";
        String eventTime="";
       
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

                eventType = "Not Specified";
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
                        newDate = sdf.format(c.getTime());  
                        //Displaying the new Date after addition of Days
                        System.out.println("This will be the next available slot: "+ newDate);

                        boolean correct = false;
                        eventTime = "08:00";

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
                        eventCity = scn.nextLine();
                        System.out.println("Area: ");
                        eventArea = scn.nextLine();      
                        System.out.println("Street: ");
                        eventStreet = scn.nextLine();
                        //Compress adress
                    
                        System.out.println("\n-----------PARTICIPANTS\n");
                        System.out.println("How many adults?");
                        numberOfAdults = scn.nextInt();

                        System.out.println("How many children?");
                        numberOfChildren = scn.nextInt();

                        System.out.println("\n-----------THEME\n");
                        System.out.println("Do you want a theme?(y/n)");
                        String answer = scn.nextLine();
                        eventTheme = "None";
                        if (answer == "y") {
                            System.out.println("Enter your theme:");
                            eventTheme = scn.nextLine();
                        }
                }
         
                    //Get adress Details
                    System.out.println("City: ");
                    eventCity = scn.nextLine();
                    System.out.println("Area: ");
                    eventArea = scn.nextLine();      
                    System.out.println("Street: ");
                    eventStreet = scn.nextLine();
                    //Compress adress
                   
                    System.out.println("How many adults?");
                    numberOfAdults = scn.nextInt();

                    System.out.println("How many children?");
                    numberOfChildren = scn.nextInt();

                    System.out.println("Do you want a theme?(y/n)");
                    eventTheme = "None";
                    if (scn.nextLine() == "y") {
                        System.out.println("Enter your theme:");
                        eventTheme = scn.nextLine();
                    }
                    
                    MenuItem m = new MenuItem(); 
                    List<MenuItem> menuu = new ArrayList<>();
                    menuu = m.ShowMenu();
                    Menu SelectedMenu = new Menu();
                    int SelectedItem=0;

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
                         
                    }
                    
                    else 
                    {
                        MenuItem m = new MenuItem();    
                        List<MenuItem> menu = new ArrayList<>();
                        menu = m.ShowMenu();
                        Menu SelectedMenu = new Menu();
                        int SelectedItem=0; 

                        System.out.println("==================================Menu:=============================");
                        System.out.println("Do you want starters? (yes/no) ");
                        if (scn.nextLine() == "yes"){
                            System.out.println("\n Available Menu Options for Adults");
                            List<MenuItem> adult = new ArrayList<>();
                
                            System.out.println("\n");
                            System.out.println("Starter Options \n");
                                for (MenuItem menuItem : menu) {
                                    if (menuItem.getMealType().equalsIgnoreCase("Adult") & menuItem.getMenuItem().equalsIgnoreCase("Starter")) {
                                        adult.add(menuItem);
                                    }
                                }
                                System.out.println("Description"+"\t"+"Price Per Person");
                                for (MenuItem menuItemA : adult) {
                                    System.out.println(adult.indexOf(menuItemA)+1+". "+menuItemA.getDescription()+"\t"+"R"+menuItemA.getCost());
                                }
                                System.out.println("\n");
                
                                System.out.print("Enter choice:");
                                SelectedItem = scn.nextInt();
                                if (SelectedItem==1) {
                                    MenuItem starter = new MenuItem(adult.get(0).getMenuItem(), adult.get(0).getDescription(), adult.get(0).getMealType(),adult.get(0).getCost());
                                    menu.add(new MenuItem(adult.get(0).getMenuItem(),adult.get(0).getDescription(),adult.get(0).getMealType(),adult.get(0).getCost()));
                                    SelectedMenu.addMenuItem(starter);
                                }
                                else if (SelectedItem==2) {
                                    MenuItem starter = new MenuItem(adult.get(1).getMenuItem(), adult.get(1).getDescription(), adult.get(1).getMealType(),adult.get(1).getCost());
                                    menu.add(new MenuItem(adult.get(1).getMenuItem(),adult.get(1).getDescription(),adult.get(1).getMealType(),adult.get(1).getCost()));
                                    SelectedMenu.addMenuItem(starter);
                                } 
                            
                                Double adultTotal =SelectedMenu.MenuTotal(numberOfAdults);
                                System.out.println("Total for adult menu: R"+adultTotal);

                                
                                System.out.println("\n Available Menu Options for Kids");
                                List<MenuItem> kids = new ArrayList<>();

                                System.out.println("\n");
                                System.out.println("Starter Options \n");
                                for (MenuItem menuItem : menu) {
                                if (menuItem.getMealType().equalsIgnoreCase("Kids")& menuItem.getMenuItem().equalsIgnoreCase("Starter")) {
                                kids.add(menuItem);
                                }
                                }
                                System.out.println("Description"+"\t"+"Price Per Person");
                                for (MenuItem menuItemK : kids) {
                                System.out.println(kids.indexOf(menuItemK)+1+". "+menuItemK.getDescription()+"\t"+"R"+menuItemK.getCost());
                                }

                                System.out.print("Enter choice:");
                                SelectedItem = scn.nextInt();
                                if (SelectedItem==1) {
                                MenuItem starter = new MenuItem(kids.get(0).getMenuItem(), kids.get(0).getDescription(), kids.get(0).getMealType(),kids.get(0).getCost());
                                menu.add(new MenuItem(kids.get(0).getMenuItem(),kids.get(0).getDescription(),kids.get(0).getMealType(),kids.get(0).getCost()));
                                SelectedMenu.addMenuItem(starter);
                                }
                                else if (SelectedItem==2) {
                                MenuItem starter = new MenuItem(kids.get(1).getMenuItem(), kids.get(1).getDescription(), kids.get(1).getMealType(),kids.get(1).getCost());
                                menu.add(new MenuItem(kids.get(1).getMenuItem(),kids.get(1).getDescription(),kids.get(1).getMealType(),kids.get(1).getCost()));
                                SelectedMenu.addMenuItem(starter);
                                } 

                                Double kidsTotal =SelectedMenu.MenuTotal(numberOfChildren);
                                System.out.println("Total for adult menu: R"+kidsTotal);


                            String type = MenuOption();
                            String[] types = type.split(",");
                            for (String string : types) {
                              //  menu.add(new MenuItem("Starter",string,description,totalCost)); intergrated into the above code
                                System.out.println("Menu item added");
                            }

                        System.out.println("===========");

                        System.out.println("Do you want deserts? (yes/no) ");
                        if (scn.nextLine() == "yes"){
                            
                            SelectedItem=0;
                            System.out.println("\n Available Menu Options for Adults");
                           
                            adult.clear();
                            System.out.println("\n");
                            System.out.println("Dessert Options \n");
                                for (MenuItem menuItem : menu) {
                                    if (menuItem.getMealType().equalsIgnoreCase("Adult") & menuItem.getMenuItem().equalsIgnoreCase("Dessert")) {
                                        adult.add(menuItem);
                                    }
                                }
                                System.out.println("Description"+"\t"+"Price Per Person");
                                for (MenuItem menuItemA : adult) {
                                    System.out.println(adult.indexOf(menuItemA)+1+". "+menuItemA.getDescription()+"\t"+"R"+menuItemA.getCost());
                                }
                                System.out.println("\n");
                
                                System.out.print("Enter choice:");
                                SelectedItem = scn.nextInt();
                                if (SelectedItem==1) {
                                    MenuItem dessert = new MenuItem(adult.get(0).getMenuItem(), adult.get(0).getDescription(), adult.get(0).getMealType(),adult.get(0).getCost());
                                    menu.add(new MenuItem(adult.get(0).getMenuItem(),adult.get(0).getDescription(),adult.get(0).getMealType(),adult.get(0).getCost()));
                                    SelectedMenu.addMenuItem(dessert);
                                }
                                else if (SelectedItem==2) {
                                    MenuItem dessert = new MenuItem(adult.get(1).getMenuItem(), adult.get(1).getDescription(), adult.get(1).getMealType(),adult.get(1).getCost());
                                    menu.add(new MenuItem(adult.get(1).getMenuItem(),adult.get(1).getDescription(),adult.get(1).getMealType(),adult.get(1).getCost()));
                                    SelectedMenu.addMenuItem(dessert);
                                } 
                                Double adultsTotal =SelectedMenu.MenuTotal(numberOfAdults);
                                System.out.println("Total for adult menu: R"+adultsTotal);


                                System.out.println("\n Available Menu Options for Kids");
                               
                                SelectedItem=0;
                                kids.clear();
                                System.out.println("\n");
                                System.out.println("Dessert Options \n");
                                for (MenuItem menuItem : menu) {
                                if (menuItem.getMealType().equalsIgnoreCase("Kids")& menuItem.getMenuItem().equalsIgnoreCase("Dessert")) {
                                kids.add(menuItem);
                                }
                                }
                                System.out.println("Description"+"\t"+"Price Per Person");
                                for (MenuItem menuItemK : kids) {
                                System.out.println(kids.indexOf(menuItemK)+1+". "+menuItemK.getDescription()+"\t"+"R"+menuItemK.getCost());
                                }

                                System.out.print("Enter choice:");
                                SelectedItem = scn.nextInt();
                                if (SelectedItem==1) {
                                MenuItem dessert = new MenuItem(kids.get(0).getMenuItem(), kids.get(0).getDescription(), kids.get(0).getMealType(),kids.get(0).getCost());
                                menu.add(new MenuItem(kids.get(0).getMenuItem(),kids.get(0).getDescription(),kids.get(0).getMealType(),kids.get(0).getCost()));
                                SelectedMenu.addMenuItem(dessert);
                                }
                                else if (SelectedItem==2) {
                                MenuItem dessert = new MenuItem(kids.get(1).getMenuItem(), kids.get(1).getDescription(), kids.get(1).getMealType(),kids.get(1).getCost());
                                menu.add(new MenuItem(kids.get(1).getMenuItem(),kids.get(1).getDescription(),kids.get(1).getMealType(),kids.get(1).getCost()));
                                SelectedMenu.addMenuItem(dessert);
                                }

                                Double kidsTotalDessert =SelectedMenu.MenuTotal(numberOfChildren);
                                System.out.println("Total for kids menu: R"+kidsTotalDessert);


                             type = MenuOption();
                             types = type.split(",");
                            for (String string : types) {
                               // menu.add(new MenuItem("Desert",string,description,totalCost)); Intergated into the above code
                               System.out.println("Menu item added");
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
        MenuItem m = new MenuItem();    
        List<MenuItem> menu = new ArrayList<>();
        menu = m.ShowMenu();
        Menu SelectedMenu = new Menu();
        int SelectedItem=0; 

        System.out.println("\nWhat type of menu?");
        System.out.println("1. Adult");
        System.out.println("2. Kids");
        System.out.println("3. Both");
        
        System.out.print("Enter choice:");
        int decision = scn.nextInt();
        switch(decision) {
            case 1:
            type = "Adult";
            System.out.println("\n Available Menu Options for Adults");
            List<MenuItem> adult = new ArrayList<>();

                System.out.println("Main Options \n");
                adult.clear();
                SelectedItem=0;

                for (MenuItem menuItem : menu) {
                    if (menuItem.getMealType().equalsIgnoreCase("Adult") & menuItem.getMenuItem().equalsIgnoreCase("Main")) {
                        adult.add(menuItem);
                    }
                }
                System.out.println("Description"+"\t"+"Price Per Person");
                for (MenuItem menuItemA : adult) {
                    System.out.println(adult.indexOf(menuItemA)+1+". "+menuItemA.getDescription()+"\t"+"R"+menuItemA.getCost());
                }
                System.out.println("\n");

                System.out.print("Enter choice:");
                SelectedItem = scn.nextInt();
                if (SelectedItem==1) {
                    MenuItem mainCourse = new MenuItem(adult.get(0).getMenuItem(), adult.get(0).getDescription(), adult.get(0).getMealType(),adult.get(0).getCost());
                    SelectedMenu.addMenuItem(mainCourse);
                }
                else if (SelectedItem==2) {
                    MenuItem mainCourse = new MenuItem(adult.get(1).getMenuItem(), adult.get(1).getDescription(), adult.get(1).getMealType(),adult.get(1).getCost());
                    SelectedMenu.addMenuItem(mainCourse);
                } 
                
                System.out.println("Total for adult menu: "+SelectedMenu.MenuTotal(20));//Enter the amount of adults
               

            break;

            case 2:
            type = "Kids";
            System.out.println("\n Available Menu Options for Kids");
            List<MenuItem> kids = new ArrayList<>(); 

                System.out.println("Main Options \n");
                kids.clear();
                SelectedItem=0;
                for (MenuItem menuItem : menu) {
                    if (menuItem.getMealType().equalsIgnoreCase("Kids")& menuItem.getMenuItem().equalsIgnoreCase("Main")) {
                        kids.add(menuItem);
                    }
                }
                System.out.println("Description"+"\t"+"Price Per Person");
                for (MenuItem menuItemK : kids) {
                    System.out.println(kids.indexOf(menuItemK)+1+". "+menuItemK.getDescription()+"\t"+menuItemK.getCost());
                }

                System.out.print("Enter choice:");
                SelectedItem = scn.nextInt();
                if (SelectedItem==1) {
                    MenuItem mainCourse = new MenuItem(kids.get(0).getMenuItem(), kids.get(0).getDescription(), kids.get(0).getMealType(),kids.get(0).getCost());
                    SelectedMenu.addMenuItem(mainCourse);
                }
                else if (SelectedItem==2) {
                    MenuItem mainCourse = new MenuItem(kids.get(1).getMenuItem(), kids.get(1).getDescription(), kids.get(1).getMealType(),kids.get(1).getCost());
                    SelectedMenu.addMenuItem(mainCourse);
                } 
 

                System.out.println("Total for kids menu: R"+SelectedMenu.MenuTotal(20));//Enter the amount of kids

            break;

            case 3:
            type = "Kids,Adult";
            
            break;
            }
            scn.close();
            return type;   
        }
        
         
    }

