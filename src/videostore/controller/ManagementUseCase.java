/* ManagementUseCase.java */

package videostore.controller;

import displayablecollections.DisplayableTreeMap;
import java.util.Iterator;
import java.util.Set;
import videostore.model.Customer;
import videostore.model.StoreDatabase;
import videostore.model.Title;

/** The controller that handles all use cases involving a manager
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class ManagementUseCase
{
     /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static ManagementUseCase getInstance()
    {
        if (theManagementUseCase == null)
            theManagementUseCase = new ManagementUseCase();
        return theManagementUseCase;
    }
    
    /** Mutator to set the object keeping track of a manager to true
     *
     * @param inputPassword the password entered by a user
     * @throws IllegalArgumentException
     */
    public void startActivateManager(char[] inputPassword) throws IllegalArgumentException
    {
        database = StoreDatabase.getInstance();
        if(database.checkManagerPassword(inputPassword) == true)
            database.activateManager();
        else
            throw new IllegalArgumentException("Invalid password");
            
    }
    
    /** Changes the system so a manager is no longer logged in
     * 
     */
    public void startDeactiveManager()
    {
        database = StoreDatabase.getInstance();
        database.deactivateManager();
    }
    
    /** Accessor for the activity of a manager
     * 
     * @return whether the manager is logged in (true) or not (false)
     */
    public boolean checkManagerActivity()
    {
        database = StoreDatabase.getInstance();
        managerActivity = database.managerActivity();
        return managerActivity;
    }
    
    /** Starts the add movie use case
     * 
     * @param name the name of the movie
     * @param director the director of the movie
     * @param year the year the movie was made
     */
    public void startAddMovie(String name, String director, String year) throws IllegalArgumentException
    {
        database = StoreDatabase.getInstance();
        if(database.getTitle(name) == null)
            database.createMovie(name, director, year);
        else
            throw new IllegalArgumentException("Title already exists");
    }
    
    /** Starts the add game use case
     * 
     * @param name the name of the game
     * @param company the company that made the game
     * @param year the year the game was made
     */
    public void startAddGame(String name, String company, String year) throws IllegalArgumentException
    {
        database = StoreDatabase.getInstance();
        if(database.getTitle(name) == null)
            database.createGame(name, company, year); 
        else
            throw new IllegalArgumentException("Title already exists");
    }
    
    /** Starts the add item use case
     * 
     * @param name the name of the title being copied
     */
    public void startAddRentableItem(String name)
    {
        database = StoreDatabase.getInstance();
        Title title = database.getTitle(name);
        database.createRentableItem(title);
    }
    
    /** Starts the title report use case
     * 
     */
    public void startTitleReport()
    {
        database = StoreDatabase.getInstance();
        DisplayableTreeMap titles = database.getTitlesMap();
        Set titleNames = titles.keySet();
        Iterator titleIterator = titleNames.iterator();
        System.out.println("\n" + "Title Report:");
        while(titleIterator.hasNext())
        {
            String name = titleIterator.next().toString();
            Title title = database.getTitle(name);
            System.out.println("Title: " + name + ", Director: " + title.getCreator() + ", Year: " + title.getYear() + ", Number of copies: " + title.getCopies());
        }
    }
    
    /** Starts the customer report use case
     * 
     */
    public void startCustomerReport()
    {
        database = StoreDatabase.getInstance();
        DisplayableTreeMap customers = database.getCustomerMap();
        Set ids = customers.keySet();
        Iterator customerIterator = ids.iterator();
        System.out.println("\n" + "Customer Report:");
        while(customerIterator.hasNext())
        {
            String customerId = customerIterator.next().toString();
            Customer customer = database.getCustomer(customerId);
            String name = customer.getName();
            String phone = customer.getPhone();
            String address = customer.getAddress();
            String state = customer.getState();
            String city = customer.getCity();
            String zip = customer.getZip();
            String email = customer.getEmail();
            String rentals = "" + customer.getRentals();
            System.out.println("Name: " + name + "; Phone: " + phone + "; Address: " + address + ", " + city + ", " + state + " " + zip + "; Email: " + email + "; Number of rentals: " + rentals);
        }
    }
    
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor - other classes should access through the singleton
    // pattern
    private ManagementUseCase()
    { }
    
    // Variable used for the singleton pattern
    private static ManagementUseCase theManagementUseCase;
    
    // Variable declaration
    StoreDatabase database;
    private boolean managerActivity;
}
