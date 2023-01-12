/* RentableItem.java */

package videostore.model;


/** Represents physical copies that can be rented
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class RentableItem implements java.io.Serializable
{
    /** Constructor for a rentable item
     * 
     * @param copyId the ID number for a copy
     */
    public RentableItem(String copyId, Title title)
    {
        this.id = copyId;
        this.title = title;
    }
    
    /** Accessor for the ID number of a copy
     * 
     * @return the ID number of an individual copy
     */
    public String getId()
    {
        return id;
    }
    
    /** Accessor for the name of the title this copy is associated with
     * 
     * @return the title of this item
     */
    public String getTitleName()
    {
        return title.getName();
    }
    
    /** Accessor for the rental charge of the title this copy is associated with
     * 
     * @return the charge for this item
     */
    public double getRentalCharge()
    {
        return title.getRentalCharge();
    }
    
    /** Accessor for the rental period of the title this copy is associated with
     * 
     * @return the number of days this item may be rented out for
     */
    public int getRentalPeriod()
    {
        return title.getRentalPeriod();
    }
    
    /** Mutator that sets the rental variables for this rental object
     * 
     * @param customer the customer this object is rented out to
     * @param dueDate the due date for this object to be returned by
     */
    public void setRentalTo(Customer customer, SimpleDate dueDate)
    {
        this.rentedTo = customer;
        this.dueDate = dueDate;
    }
    
    /** Accessor for the customer this object may be rented to
     * 
     * @return the customer object this object is rented to
     */
    public Customer getRentedTo()
    {
        return rentedTo;
    }
    
    /** Accessor for the rental date this object is due 
     * 
     * @return the due date this object is due
     */
    public SimpleDate getRentalDueDate()
    {
        return dueDate;
    }
    
    /** Mutator that sets the rental variables for this rental object back to null
     * 
     * @param customer the customer this object was rented out to
     * @param dueDate the date that this object was due
     */
    public void endRentalTo(Customer customer, SimpleDate dueDate)
    {
        this.rentedTo = null;
        this.dueDate = null;
    }
    
    //Variable declaration
    private String id;
    private Title title;
    private Customer rentedTo;
    private SimpleDate dueDate;
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
}
