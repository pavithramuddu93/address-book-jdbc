package blz;

import blz.AddressBookException;
import blz.Person;
import blz.AddressBookDBService;
import java.util.*;

public class AddressBookMain {
    private static final Scanner scanner = new Scanner(System.in);
    AddressBookDBService addressBookDBService;
    public AddressBookMain(){
        addressBookDBService = AddressBookDBService.getInstance();
    }

    /**
     * Method for retrieving data from database.
     * @return : List of persons details
     * @throws AddressBookException
     */
    public List<Person> retrieveData() throws AddressBookException {
        List<Person> list = addressBookDBService.readData();
        return list;
    }
}