package blz;

import blz.AddressBookException;
import blz.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {

    AddressBookMain addressBookMain;
    @Before
    public void setUp() throws Exception {
        addressBookMain = new AddressBookMain();
    }

    @Test
    public void givenPersonsDetailsInDB_whenRetrieved_shouldMatchPersonCount() throws AddressBookException {
        List<Person> result = addressBookMain.retrieveData();
        Assert.assertEquals(5, result.size());
    }
}