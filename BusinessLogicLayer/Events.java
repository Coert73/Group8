package BusinessLogicLayer;

import java.util.List;

import javax.xml.namespace.QName;

import java.util.ArrayList;

public class Events {

      int clientNum;
      String eventType;
      String eventDateandTime;
      String eventCity;
      String eventArea;
      String eventStreet;
      //The address = city + area + street thus it is redundant
      //String eventAdress;
      //House Number is specified in the street ex. 29 Carnival Lane Unless the place is within a complex
      //String eventHouseNumber;
      String eventTheme;
      int numberOfAdults;
      int numberOfChildren;

    //This is a lot more flexible for menu option
      List<MenuItem> menu = new ArrayList<MenuItem>();

      

      public String getEventType() {
            return eventType;
      }

      public void setEventType(String eventType) {
            this.eventType = eventType;
      }

      public String getEventDateandTime() {
            return eventDateandTime;
      }

      public void setEventDateandTime(String eventDateandTime) {
            this.eventDateandTime = eventDateandTime;
      }

      public String getEventCity() {
            return eventCity;
      }

      public void setEventCity(String eventCity) {
            this.eventCity = eventCity;
      }

      public String getEventArea() {
            return eventArea;
      }

      public void setEventArea(String eventArea) {
            this.eventArea = eventArea;
      }

      public String getEventStreet() {
            return eventStreet;
      }

      public void setEventStreet(String eventStreet) {
            this.eventStreet = eventStreet;
      }

      public String getEventTheme() {
            return eventTheme;
      }

      public void setEventTheme(String eventTheme) {
            this.eventTheme = eventTheme;
      }

      public int getNumberOfAdults() {
            return numberOfAdults;
      }

      public void setNumberOfAdults(int numberOfAdults) {
            this.numberOfAdults = numberOfAdults;
      }

      public int getNumberOfChildren() {
            return numberOfChildren;
      }

      public void setNumberOfChildren(int numberOfChildren) {
            this.numberOfChildren = numberOfChildren;
      }

      public List<MenuItem> getMenu() {
            return menu;
      }

      public void setMenu(List<MenuItem> menu) {
            this.menu = menu;
      }

      public int getClientNum() {
            return clientNum;
      }

      public void setClientNum(int clientNum) {
            this.clientNum = clientNum;
      }


      public Events(String eventType, String eventDateandTime, String eventCity, String eventArea, String eventStreet,
                  String eventTheme, int numberOfAdults, int numberOfChildren, int clientNum, List<MenuItem> menu) {
            this.eventType = eventType;
            this.eventDateandTime = eventDateandTime;
            this.eventCity = eventCity;
            this.eventArea = eventArea;
            this.eventStreet = eventStreet;
            this.eventTheme = eventTheme;
            this.numberOfAdults = numberOfAdults;
            this.numberOfChildren = numberOfChildren;
            this.clientNum = clientNum;
            this.menu = menu;
      }
        
}
    

