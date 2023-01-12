/* Customer.java */

package videostore.model;

import java.util.HashSet;
import java.util.Iterator;

/** Represents a customer for the store
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class Customer implements java.io.Serializable
{    
    /** Constructor for a customer object
     * 
     * @param customerId the ID for a new customer
     * @param customerName the name of the new customer
     */
    public Customer(String customerId, String customerName, String customerPhone, String customerAddress, String customerCity, String customerState, String customerZip, String customerEmail)
    {
        this.id = customerId;
        this.name = customerName;
        this.address = customerAddress;
        this.city = customerCity;
        this.phone = customerPhone;
        this.state = customerState;
        this.email = customerEmail;
        this.zip = customerZip;
        rentals = new HashSet<RentableItem>();
    }
    
    /** Accessor for the name variable of this customer
     * 
     * @return the name for this customer 
     */
    public String getName()
    {
        return name;
    }
    
    /** Accessor for the ID object of a customer
     * 
     * @return the ID for a customer
     */
    public String getId()
    {
        return id;
    }
    
    /** Mutator to change the name variable of a customer
     * 
     * @param newName the new name for a customer
     */
    public void setName(String newName)
    {
        this.name = newName;
    }
    
    /** Accessor for the phone number variable of the customer
     * 
     * @return the phone number of a customer in the form of a string
     */
    public String getPhone()
    {
        return phone;
    }
    
    /** Mutator that changes the phone number variable of a customer
     * 
     * @param phone the new phone number of this customer 
     */
    public void setPhone(String newPhone)
    {
        this.phone = newPhone;
    }
    
    /** Accessor for the address variable of a customer
     * 
     * @return the address of a customer as a string
     */
    public String getAddress()
    {
        return address;
    }
    
    /** Mutator to change the address variable of a customer
     * 
     * @param newAddress the new address for a customer
     */
    public void setAddress(String newAddress)
    {
        this.address = newAddress;
    }
    
    /** Accessor for the city variable for a customer
     * 
     * @return the city of a customer in the form of a string
     */
    public String getCity()
    {
        return city;
    }
    
    /** Mutator that changes the city variable for a customer
     * 
     * @param newCity the new city for a customer
     */
    public void setCity(String newCity)
    {
        this.city = newCity;
    }
    
    /** Accessor for the state variable for a customer
     * 
     * @return the state of a customer in the form of a string
     */
    public String getState()
    {
        return state;
    }
    
    /** Mutator that changes the state variable for a customer
     * 
     * @param newState the new state for a customer
     */
    public void setState(String newState)
    {
        this.state = newState;
    }
    
    /** Accessor for the email variable for a customer
     * 
     * @return the email of a customer in the form of a string
     */
    public String getEmail()
    {
        return email;
    }
    
    /** Mutator that changes the email address variable for a customer
     * 
     * @param newEmail the new email address for a customer
     */
    public void setEmail(String newEmail)
    {
        this.email = newEmail;
    }
    
    /** Accessor for the zip variable for a customer
     * 
     * @return the zip of a customer in the form of a string
     */
    public String getZip()
    {
        return zip;
    }
    
    /** Mutator that changes the zip variable for a customer
     * 
     * @param newZip  the new zip for a customer
     */
    public void setZip(String newZip)
    {
        this.zip = newZip;
    }
    
    /** Adds an item to the set of items rented to this customer
     * 
     * @param item the item rented to this customer
     */
    public void addRental(RentableItem item)
    {
        rentals.add(item);
    }
    
    /** Removes an item from the list of rented items to this customer
     * 
     * @param item the item to be removed
     */
    public void endRental(RentableItem item)
    {
        rentals.remove(item);
    }
    
    /** Accessor for the number of rentals this customer has
     * 
     * @return the number of rentals as an integer
     */
    public int getRentals()
    {
        return rentals.size();
    }
    
    /** Represents a customer object as a string
     * 
     * @return the name of the customer
     */
    public String toString()
    {
        return name;
    }
    
    // Variable declaration
    private String id;
    private String name;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String email;
    private String zip;
    private HashSet<RentableItem> rentals;
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
}
