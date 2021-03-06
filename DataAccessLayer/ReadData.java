package DataAccessLayer;

import java.io.File;  // Import the File class
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;
import BusinessLogicLayer.*;

public class ReadData 
{
    List<Client> clients = new ArrayList<Client>();
    List<Events> events = new ArrayList<Events>();
    
    public List<Events> getEvents() throws IOException{
      try {
        /////////////////////////////////////////Grab Bookings
        File myObj = new File("Booking.txt");

        //Grab Bookings
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine(); 
          //Split the fields up into an array     
          String[] seperatedData = data.split(",");

          List<MenuItem> menu = new ArrayList<MenuItem>(); //A list that will contain all the menu options            
          
          //Find the menu items in the string and add them to the menu list
          for (String string : seperatedData) {
              if(string.contains("#")) {
                  //Grab Menu Item information
                  String item = string.substring(1, (string.indexOf("#")-1));
                  String type = string.substring(string.indexOf("#")+1, (string.indexOf("@")-1));
                  String description = string.substring(string.indexOf("@")+1, (string.indexOf("$")-1));
                  String priceString = string.substring(string.indexOf("$")+1, (string.length()));
                  double price = Double.parseDouble(priceString);
                  //Add menu item
                  System.out.println("Item: " + item + " type: " + type + " Description: " + description + " Price" + price);
                  menu.add(new MenuItem(item, description, type, price));
              }
          }
          myReader.close();
          //Add event object to list
          events.add(new Events(seperatedData[1], seperatedData[2], seperatedData[3], seperatedData[4], seperatedData[5], seperatedData[6], Integer.parseInt(seperatedData[7]), Integer.parseInt(seperatedData[8]),Integer.parseInt(seperatedData[0]), menu));
        }
      }
      catch (IOException ex) {
        System.out.println(ex);
      }
      return events;
    }

    public List<Client> getClients() throws IOException{
      try {
        File myObj = new File("Client.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {

        ////////////////////////////////////////Clients
        //Grab Bookings
        myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
          String data = myReader.nextLine(); 
          //Split the fields up into an array     
          String[] seperatedData = data.split(",");

          //Add client to list
          clients.add(new Client(Integer.parseInt(seperatedData[0]),seperatedData[1],seperatedData[2],seperatedData[3])); //Name, Surname, Phone Number
        }
        
        System.out.println("Data has been retrieved.");
        myReader.close();

      } 
    }
    catch (IOException ex) {
      System.out.println(ex);
    }
      return clients;
    }
}
