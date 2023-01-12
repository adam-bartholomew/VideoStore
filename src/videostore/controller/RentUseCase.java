/*
 * RentUseCase.java
 */

package videostore.controller;

import java.util.ArrayList;
import java.util.Iterator;
import videostore.model.StoreDatabase;
import videostore.model.Customer;
import videostore.model.RentableItem;
import videostore.model.SimpleDate;

/** This is the controller for the use case that manages renting items to a
 *  customer.
 *
 * @author Russell C. Bjork
 *
 * MODIFIED BY: Adam Bartholomew and Steve Smith
 */
public class RentUseCase {
    
    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static RentUseCase getInstance()
    {
        if (theRentUseCase == null)
            theRentUseCase = new RentUseCase();
        return theRentUseCase;
    }
    
   /** Start the use case 
     *
     *  @param customerId the ID of the customer to rent to
     *
     *  @exception IllegalArgumentException if the customerId is not valid
     */
    public void start(String customerId) throws IllegalArgumentException
    {
        rentalItems = new ArrayList<String>();
        database = StoreDatabase.getInstance();
        if(database.getCustomer(customerId) != null)
            this.customer = database.getCustomer(customerId);
        else
            throw new IllegalArgumentException("Customer ID does not exist.");
    }
    
    /** Accessor for the name of the customer being rented to
     *
     *  @return the name of the customer being rented to
     */
    public String getCustomerName()
    {
        return customer.getName();
    }
    /** Add an item to the list of items to rent
     *
     *  @param itemId the ID of the item
     *  @return a description of the item to display in the GUI
     *  
     *  @exception IllegalArgumentException if the itemId is not valid or
     *             this item is already checked out
     */
    public String addItem(String itemId) throws IllegalArgumentException
    {
        database = StoreDatabase.getInstance();
        RentableItem item = database.getItem(itemId);
        if(item != null && rentalItems.contains(itemId) == false && item.getRentedTo() == null)
        {
            rentalItems.add(itemId);
            String title = database.getItem(itemId).getTitleName();
            double fee = database.getItem(itemId).getRentalCharge();
            int period = database.getItem(itemId).getRentalPeriod();
            SimpleDate dueDate = SimpleDate.getToday().daysLater(period);
            return "Item # " + itemId + ", Title: " + title + ", Charge: " + java.text.NumberFormat.getCurrencyInstance().format(fee) + ", Due: " + dueDate;  
        }
        else if(item != null && item.getRentedTo() != null)
            throw new IllegalArgumentException("Item is already checked out");
        else if(item != null && rentalItems.contains(itemId) == true)
            throw new IllegalArgumentException("Item is already on the list");
        else
            throw new IllegalArgumentException("Item ID is invalid");
    }
    
    /** Remove an item from the list of items to rent
     *
     *  @param position the position of the item in the list - 0 = first, etc
     */
    public void removeItem(int position)
    {    
        rentalItems.remove(position);
    }
    
    /** Clear all items from the list of items to rent
     */
    public void clearItems()
    {
        rentalItems.clear();
    }
    
    /** Rent all items currently in the list to the customer
     */
    public void rentItems()
    {
        database = StoreDatabase.getInstance();
        int size = rentalItems.size();
        for(int x = 0; x < size; x++)
        {
            String id = rentalItems.get(x);
            RentableItem item = database.getItem(id);
            int period = database.getItem(id).getRentalPeriod();
            SimpleDate dueDate = SimpleDate.getToday().daysLater(period);
            customer.addRental(item);
            item.setRentalTo(customer, dueDate);
        }
    }
    
    /** Cancel this use case
     */
    public void cancel()
    {
        rentalItems.clear();
    }
    
    /** Get the total charges for items scheduled to be rented
     *
     *  @return the total charges for all items (as a dollar amount)
     */
    public double getTotalCharges()
    {
        Iterator rentalIterator = rentalItems.iterator();
        int size = rentalItems.size();
        double cost = 0;
        for(int x = 0; x < size; x++)
        {
            String id = rentalItems.get(x);
            cost += database.getItem(id).getRentalCharge();
        }
        return cost;
    }
    
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor - other classes should access through the singleton
    // pattern
    private RentUseCase()
    { }
    
    // Variable used for the singleton pattern
    private static RentUseCase theRentUseCase;
    
    //Variable declaration
    private StoreDatabase database;
    private Customer customer;
    private ArrayList<String> rentalItems;
}
