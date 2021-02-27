
import java.io.FileWriter;
import java.io.IOException;


public class WriteData {

   public void SaveBooking(String name, String surname, String phone,String eventType, String eventDateandTime, String eventCity, String eventArea, String eventStreet,
   String eventTheme, int numberOfAdults, int numberOfChildren, String menu )
   {
    try {
        FileWriter myWriter = new FileWriter("Booking.txt");
        myWriter.write(name+","+surname+","+phone+","+eventType+","+eventDateandTime+","+eventCity+","+eventArea+","+eventStreet+","
        +eventTheme+","+ numberOfAdults+","+numberOfChildren+","+menu);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
   }
}
