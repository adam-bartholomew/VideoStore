/* CustomersUseCase.java */

package videostore.controller;

import videostore.model.StoreDatabase;
import videostore.model.Customer;

/** This is the controller for the use cases involved with customers
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class CustomersUseCase
{
    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static CustomersUseCase getInstance()
    {
        if (theCustomersUseCase == null)
            theCustomersUseCase = new CustomersUseCase();
        return theCustomersUseCase;
    }
    
    
    /** Mutator that adds a customer into the database by input from a user
     * 
     * @param name the new name of the customer
     * @param phone the new phone of the customer
     * @param address the new address of the customer
     * @param city the new city of the customer
     * @param state the new state of the customer
     * @param zip the new ZIP of the customer
     * @param email the new email address of the customer
     */
    public void startAddCustomer(String name, String phone, String address, String city, String state, String zip, String email) throws IllegalArgumentException
    {
        this.database = StoreDatabase.getInstance();
        if(database.checkCustomerName(name) == false)
            database.createCustomer(name, phone, address, city, state, zip, email);
        else
            throw new IllegalArgumentException("Customer by that name already exists");
    }
    
    /** Start the edit a customer use case
     * 
     * @param name the name of the customer being edited
     */
    public void startEdit(String name)
    {
        Customer customer = database.getCustomerByName(name);
        this.selectedCustomerToEdit = customer;
    }
    
    /** Accessor for the object associated with the customer being edited
     * 
     * @return the customer selected to edit
     */
    public Customer getSelectedCustomerToEdit()
    {
        return selectedCustomerToEdit;
    }
    
    /** Mutator that edits the information of a customer by input from a user
     * 
     * @param customer the customer being edited
     * @param name the new name of the customer
     * @param phone the new phone of the customer
     * @param address the new address of the customer
     * @param city the new city of the customer
     * @param state the new state of the customer
     * @param zip the new ZIP of the customer
     * @param email the new email address of the customer
     */
    public void editCustomer(String name, String phone, String address, String city, String state, String zip, String email) throws IllegalArgumentException
    {
        this.database = StoreDatabase.getInstance();
        if(database.checkCustomerName(name) == false)
            database.updateCustomerInfo(selectedCustomerToEdit, name, phone, address, city, state, zip, email);
        else if(database.checkCustomerName(name) == true && (selectedCustomerToEdit.getName() == null ? name == null : selectedCustomerToEdit.getName().equals(name)))
            database.updateCustomerInfo(selectedCustomerToEdit, name, phone, address, city, state, zip, email);
        else
            throw new IllegalArgumentException("Customer by that name already exists");
    }
    
    
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor - other classes should access through the singleton
    // pattern
    private CustomersUseCase()
    { }
    
    // Variable used for the singleton pattern
    private static CustomersUseCase theCustomersUseCase;
    
    // Variable declaration
    private StoreDatabase database;
    private Customer selectedCustomerToEdit;
}
