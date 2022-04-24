package blz;

import blz.AddressBookException;
import blz.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {

    private static AddressBookDBService addressBookDBService;

    private AddressBookDBService() {

    }

    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }


    /**
     * UC1 - Method for create connection to the database.
     *
     * @return
     * @throws AddressBookException
     */
    public Connection getConnection() throws AddressBookException {
        Connection con = null;
        try {
            Class.forName("java.sql.DriverManager");
            System.out.println("Driver loaded");
            String username = "root";
            String password = "root";
            String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
            System.out.println("connecting to database : " + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("connection is successfully " + con);
        } catch (Exception e) {
            throw new AddressBookException(e.getMessage());
        }
        return con;
    }

    /**
     * UC2 - Method for retrieving data from database.
     *
     * @return : list of persons
     * @throws AddressBookException
     */
    public List<Person> readData() throws AddressBookException {
        String query = "SELECT * FROM address_book;";
        return this.getEmployeePayrollDataUsingDB(query);

    }

    /**
     * Helper or Generic method for retrieving data from database.
     *
     * @param query
     * @return
     * @throws AddressBookException
     */
    private List<Person> getEmployeePayrollDataUsingDB(String query) throws AddressBookException {
        List<Person> employeePayrollList = new ArrayList<>();

        try (Connection connection = this.getConnection();) {
            Person person = new Person();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            employeePayrollList = this.getEmployeePayrollData(result);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage());
        }
        return employeePayrollList;
    }

    /**
     * UC3 - Method for retrieving data from database. Method to process the resultSet.
     *
     * @param resultSet
     * @return
     */
    private List<Person> getEmployeePayrollData(ResultSet resultSet) {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        try {
            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setAddress(resultSet.getString("address"));
                person.setCity(resultSet.getString("city"));
                person.setState(resultSet.getString("state"));
                person.setZip(resultSet.getString("zip"));
                person.setPhone(resultSet.getString("phone"));
                person.setEmail(resultSet.getString("email"));
                personList.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }
}
