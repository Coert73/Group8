package DataAccessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import BusinessLogicLayer.*;


public class WriteData {

   public void SaveBooking(List<Events> events)
   {
    try {
        FileWriter myWriter = new FileWriter("Group8\\DataAccessLayer\\Booking.txt");
        for (Events event : events) {
          String menuItems = "";
          for (MenuItem menuItem : event.getMenu()) {
            menuItems += "," + menuItem.getMenuItem() + "#" + menuItem.getMealType() + "@" + menuItem.getDescription() + "$" + menuItem.getCost();
          }
          String eventStr = event.getClientNum() + "," + event.getEventType() + "," + event.getEventDateandTime() 
          + "," + event.getEventCity() + "," + event.getEventArea() + "," + event.getEventStreet() 
          + "," + event.getEventTheme() + "," + event.getNumberOfAdults() + "," + event.getNumberOfChildren()
          + menuItems;
          System.out.println(eventStr);
          myWriter.write(eventStr);
        }
        myWriter.close();

      } 
      catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
   }

   public void SaveClients(List<Client> clients)
   {
    try {
        FileWriter myWriter = new FileWriter("Group8\\DataAccessLayer\\Client.txt");
        for (Client client : clients) {
          String clientStr = client.getClientNum() + "," + client.getName() + "," + client.getSurname() + "," + client.getCellNumber() + "\n";
          myWriter.write(clientStr);
        }
        myWriter.close();
      } 
      catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
   }
}
