package BusinessLogicLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DataAccessLayer.*;

public class DataHandler {

    ReadData reader = new ReadData();
    WriteData writer = new WriteData();
    List<Client> clients = new ArrayList<Client>();
    List<Events> events = new ArrayList<Events>();

    public List<Events> getEvents() throws IOException{
        events = reader.getEvents();
        return events;
    }

    public List<Client> getClient() throws IOException{
        clients = reader.getClients();
        return clients;
    }

    public void saveBookings(List<Events> newEvents) {
        writer.SaveBooking(newEvents);
    }

    public void saveClients(List<Client> newClients) {
        writer.SaveClients(newClients);
    }

}
