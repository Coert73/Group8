package BusinessLogicLayer;

import java.util.Scanner;

public class Client {

    String[] getClientDetails()
    {
        String name;
        String surname;
        String cellNumber;
        String[] clientList = new String[0];

        Scanner nameScanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        name = nameScanner.nextLine();
        clientList [0] = name;

        System.out.println("Enter Surname: ");
        surname = nameScanner.nextLine();
        clientList [1] = surname;

        System.out.println("Enter cell number: ");
        cellNumber = nameScanner.nextLine();
        clientList [2] = cellNumber;

        nameScanner.close();

        return clientList;

    }
    
}
