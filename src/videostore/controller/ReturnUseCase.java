/*
 * ReturnUseCase.java
 */

package videostore.controller;

import videostore.model.Customer;
import videostore.model.RentableItem;
import videostore.model.SimpleDate;
import videostore.model.StoreDatabase;

/** This is the controller for the use case that manages returning items
 *  customer.
 *
 * @author Russell C. Bjork
 *
 * MODIFIED BY: Adam Bartholomew and Steve Smith
 */
public class ReturnUseCase {
    
    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static ReturnUseCase getInstance()
    {
        if (theReturnUseCase == null)
            theReturnUseCase = new ReturnUseCase();
        return theReturnUseCase;
    }
    
   /** Perform the use case 
     *
     *  @param itemId the ID of the item being returned
     *	@return the amount of the late fee, if any, assessed for the item - 0
     *          if returned on time
     *
     *  @exception IllegalArgumentException if the id is invalid (no such
     *             item or not recorded as checked out
     */
    public double perform(String itemId) throws IllegalArgumentException
    {
        database = StoreDatabase.getInstance();
        RentableItem item = database.getItem(itemId);
        if(item != null && item.getRentedTo() != null)
        {
            Customer customer = item.getRentedTo();
            SimpleDate dueDate = item.getRentalDueDate();
            item.endRentalTo(customer, dueDate);
            customer.endRental(item);
            return 0.0;
        }
        else if(item != null && item.getRentedTo() == null)
            throw new IllegalArgumentException("Item is not checked out");
        else
    	    throw new IllegalArgumentException("Item ID is not valid");
    }
    
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor - other classes should access through the singleton
    // pattern
    private ReturnUseCase()
    { }
    
    // Variable used for the singleton pattern
    private static ReturnUseCase theReturnUseCase;
    
    // Variable declaration
    private StoreDatabase database;
}
