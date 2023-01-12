/*
 *  StoreDatabase.java
 */

package videostore.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import displayablecollections.DisplayableTreeMap;
import displayablecollections.DisplayableTreeSet;
import java.util.TreeMap;

/** A singleton object of this class represents the video store's database
 *
 * @author  Russell C. Bjork
 * 
 * MODIFIED BY: Adam Bartholomew and Steve Smith
 */
public class StoreDatabase implements java.io.Serializable
{
    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static StoreDatabase getInstance()
    {
        if (theStoreDatabase == null)
        {
            try {
                ObjectInputStream stream = new ObjectInputStream(
                    new FileInputStream(SAVED_DATABASE_NAME));
                theStoreDatabase = (StoreDatabase) stream.readObject();
                stream.close();
            }
            catch(FileNotFoundException e) {
                new StoreDatabase();
            }
            catch(Throwable e) {
                System.err.println("Unexpected exception " + e);
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
        return theStoreDatabase;
    }
    
    /** Save the store database
     *
     *  @exception Exception any Exception thrown during the save is propagated
     */
    public void save() throws Exception
    {
        ObjectOutputStream stream = new ObjectOutputStream(
                    new FileOutputStream(SAVED_DATABASE_NAME));
        stream.writeObject(theStoreDatabase);
        stream.close();
    }
      
    // Private constructor.  Other classes should access through the singleton
    // pattern
    private StoreDatabase() 
    {
        this.MANAGER_PASSWORD = "code";
        this.managerActivity = false;
        this.nextAvailableCustomerId = 1;
        this.nextAvailableRentableItemId = 1;
        theStoreDatabase = this;
        
        
        // Constructors for collections
        customers = new DisplayableTreeMap<String, Customer>();
        titles = new DisplayableTreeMap<String, Title>();
        items = new DisplayableTreeMap<String, RentableItem>();
        customerNameKey = new TreeMap<String, String>();
    }
    
    /** Accessor for a customer object via ID number
     * 
     * @param customerId the ID number entered into the user interface
     * @return the customer object for a given ID number
     */
    public Customer getCustomer(String customerId)
    {
        if(customers.containsKey(customerId))
            return customers.get(customerId);
        else
            return null;
    }
    
    /** Accessor for a title object via name
     * 
     * @param name the name of the title
     * @return the title object
     */
    public Title getTitle(String name)
    {
        if(titles.containsKey(name))
            return titles.get(name);
        else
            return null;
    }
    
    /** Accessor for the map associated with customers and their ID's
     * 
     * @return a map of customers
     */
    public DisplayableTreeMap getCustomerMap()
    {
        return customers;
    }
    
    /** Accessor for the map associated with titles and their names
     * 
     * @return  a map of titles
     */
    public DisplayableTreeMap getTitlesMap()
    {
        return titles;
    }
 
    /** Accessor for an item via ID number
     * 
     * @param itemId the ID number entered into the user interface
     * @return the item object for a given ID number
     */
    public RentableItem getItem(String itemId)
    {
        if(items.containsKey(itemId))
            return items.get(itemId);
        else
            return null;
    }
    
    /** Creates a new customer in the database
     * 
     * @param name the name of the customer
     * @param phone the phone number of the customer
     * @param address the address of the customer
     * @param city the city of the customer
     * @param state the state of the customer
     * @param zip the zip code of the customer
     * @param email the email address of the customer
     */
    public void createCustomer(String name, String phone, String address, String city, String state, String zip, String email)
    {
        String customerId = "" + nextAvailableCustomerId;
        Customer newCustomer = new Customer(customerId, name, phone, address, city, state, zip, email);
        customers.put(customerId, newCustomer);
        customerNameKey.put(name, customerId);
        nextAvailableCustomerId += 1;
    }
    
    /** Changes the given information associated with the given customer
     * 
     * @param customer the customer being updated
     * @param name the name of the customer
     * @param phone the phone number of the customer
     * @param address the address of the customer
     * @param city the city of the customer
     * @param state the state of the customer
     * @param zip the zip code of the customer
     * @param email the email address of the customer
     */
    public void updateCustomerInfo(Customer customer, String name, String phone, String address, String city, String state, String zip, String email)
    {
        customerNameKey.remove(customer.getName());
        customer.setName(name);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setState(state);
        customer.setZip(zip);
        customerNameKey.put(name, customer.getId());
    }
    
    /** Mutator to create a new rentable item in the database
     * 
     * @param title the title that this item will be a copy of
     */
    public void createRentableItem(Title title)
    {
        String itemId = "" + nextAvailableRentableItemId;
        RentableItem newRentable = new RentableItem(itemId, title);
        items.put(itemId, newRentable);
        nextAvailableRentableItemId += 1;
        title.addCopy();
    }
    
    /** Mutator to create a new movie in the database
     * 
     * @param name the name of the movie to be added
     */
    public void createMovie(String name, String director, String year)
    {
        Movie newMovie = new Movie(name, director, year);
        titles.put(name, newMovie);
    }
    
    /** Mutator to create a new game in the database
     * 
     * @param name the name of the game to be added
     */
    public void createGame(String name, String company, String year)
    {
        Game newGame = new Game(name, company, year);
        titles.put(name, newGame);
    }
    
    /** Checks the given input against the password given to a manager
     * 
     * @param inputPassword the password to be verified
     * @return a boolean if the password was correct (true), or incorrect (false)
     */
    public boolean checkManagerPassword(char[] inputPassword)
    {
        if((new String(inputPassword)).equals(MANAGER_PASSWORD))
            return true;
        else
           return false;
    }
    
    /** Changes the manager activity variable so that the system knows a manager is logged on
     * 
     */
    public void activateManager()
    {
        managerActivity = true;
    }
    
    /** Changes the manager activity variable so that the system knows a manager is not logged on
     * 
     */
    public void deactivateManager()
    {
        managerActivity = false;
    }
    
    /** Accessor to see if a manager is logged into the system
     * 
     * @return a boolean representing if a manager is logged in (true), or not (false)
     */
    public boolean managerActivity()
    {
        return managerActivity;
    }
    
    /** Accessor for the next available item ID number
     * 
     * @return the next available ID number
     */
    public int getNextItemId()
    {
        return nextAvailableRentableItemId;
    }
    
    /** Accessor for a map of customer names by ID number
     * 
     * @param name the name of a customer
     * @return the customer object associated with the given name
     */
    public Customer getCustomerByName(String name)
    {
        String id = customerNameKey.get(name);
        Customer customer = customers.get(id);
        return customer;
    }
    
    /** Checks to see if a customer exists with a given name
     * 
     * @param name the name to be checked
     * @return true if the name exists or false if it does not exist
     */
    public boolean checkCustomerName(String name)
    {
        if(customerNameKey.containsKey(name))
            return true;
        else
            return false;
    }
    
    // The one and only instance of this class
    private static StoreDatabase theStoreDatabase;
        
    // Name to use for saved database
    private static final String SAVED_DATABASE_NAME = "video.database";
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
    
    //Variable declaration
    private DisplayableTreeMap<String, Customer> customers;
    private DisplayableTreeMap<String, RentableItem> items;
    private DisplayableTreeMap<String, Title> titles;
    private TreeMap<String, String> customerNameKey;
    private int nextAvailableCustomerId;
    private int nextAvailableRentableItemId ;
    private boolean managerActivity;
    private String MANAGER_PASSWORD;
}
