package BusinessLogicLayer;

import java.util.Scanner;

public class Client {

    String name;
    String surname;
    String cellNumber;

    String[] getClientDetails()
    {
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
//commit
    }

    public Client(String name, String surname, String cellNumber) {
        this.name = name;
        this.surname = surname;
        this.cellNumber = cellNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }
    
}
