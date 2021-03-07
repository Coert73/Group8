package DataAccessLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    public List getMenuItems() {
        List menu = new ArrayList<>();

        try {
            File myObj = new File("DAL\\Menu.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              menu.add(data);
              
            }
            
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

          return menu;
    }
}
