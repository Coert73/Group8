import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;
import BusinessLogicLayer.*;

public class ReadData 
{
    public List<Client> clients = new ArrayList<Client>();
    public List<Object> events = new ArrayList<Object>();

    public ReadData() {
      try {
        File myObj = new File("Booking.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine(); 
          //Split the fields up into an array     
          String[] seperatedData = data.split(",");
          //Create Client Object
          clients.add(new Client(seperatedData[0],seperatedData[1],seperatedData[2])); //Name, Surname, Phone Number

          List<String> menu = new ArrayList<String>(); //A list that will contain all the menu options                           //This is to help determine where the next field is after the list of menu items
          
          //Find the menu items in the string and add them to the menu list
          for (String string : seperatedData) {
              if(string.contains("#")) {
                  menu.add(string.substring(1, string.length()));
              }
          }

          //Determine what class this is
          switch(seperatedData[3]) {
            case "Baby Shower":
              events.add(new BabyShower(seperatedData[3], seperatedData[4], seperatedData[5], seperatedData[6], seperatedData[7], seperatedData[8], seperatedData[9], seperatedData[10], Integer.parseInt(seperatedData[11]), Integer.parseInt(seperatedData[12]), menu));
              break;
            
            case "Baptism":
              events.add(new Baptism(seperatedData[3], seperatedData[4], seperatedData[5], seperatedData[6], seperatedData[7], seperatedData[8], seperatedData[9], seperatedData[10], Integer.parseInt(seperatedData[11]), Integer.parseInt(seperatedData[12]), menu));  
              break;

            case "BirthdayDay":
              events.add(new BirthDay(seperatedData[3], seperatedData[4], seperatedData[5], seperatedData[6], seperatedData[7], seperatedData[8], seperatedData[9], seperatedData[10], Integer.parseInt(seperatedData[11]), Integer.parseInt(seperatedData[12]), menu));  
              break;

            case "Wedding":
              events.add(new Wedding(seperatedData[3], seperatedData[4], seperatedData[5], seperatedData[6], seperatedData[7], seperatedData[8], seperatedData[9], Integer.parseInt(seperatedData[11]), Integer.parseInt(seperatedData[12]), menu));  
              break;

            case "Year-End Function":  
              events.add(new YearEndFunction(seperatedData[3], seperatedData[4], seperatedData[5], seperatedData[6], seperatedData[7], seperatedData[8], seperatedData[9], seperatedData[10], Integer.parseInt(seperatedData[11]), Integer.parseInt(seperatedData[12]), menu));
              break;
          }

          System.out.println(data);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();

        //This is a test
      }
    }

    public List<Object> getEvents() {
      return events;
    }

    public List<Client> getClients() {
      return clients;
    }
}
