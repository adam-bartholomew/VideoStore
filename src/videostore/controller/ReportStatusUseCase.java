/* ReportStatusUseCase.java */

package videostore.controller;

import videostore.model.RentableItem;
import videostore.model.StoreDatabase;

/** This is the controller for the use case that manages the status of all items.
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class ReportStatusUseCase
{
    /** Accessor for the one and only object of this class (singleton
     *  pattern)
     *
     *  @return the one and only object of this class
     */
    public static ReportStatusUseCase getInstance()
    {
        if (theReportStatusUseCase == null)
            theReportStatusUseCase = new ReportStatusUseCase();
        return theReportStatusUseCase;
    }
    
    /** Perform the use case 
     *
     *  @param itemId the ID of the item being reported
     *	@return the title and checkout status of an item
     *
     *  @exception IllegalArgumentException if the id is invalid
     */
    public String doStatus(String itemId) throws IllegalArgumentException
    {
        database = StoreDatabase.getInstance();
        if(database.getItem(itemId) != null && database.getItem(itemId).getRentedTo() != null)
        {
            String customerName = database.getItem(itemId).getRentedTo().getName();
            RentableItem item = database.getItem(itemId);
            return ("Title: " + item.getTitleName() + "\n" + "Status: Rented to " + customerName + "\n" + "Due: " + item.getRentalDueDate());
        }
        else if(database.getItem(itemId) != null && database.getItem(itemId).getRentedTo() == null)
            return ("Title: " + database.getItem(itemId).getTitleName() + "\n" + "Status: On shelf");
        else
            throw new IllegalArgumentException("Item ID is not valid.");
    }
    
    /***************************************************************************
     * PRIVATE METHOD AND VARIABLES
     **************************************************************************/
    
    // Private constructor - other classes should access through the singleton
    // pattern
    private ReportStatusUseCase()
    { }
    
    // Variable used for the singleton pattern
    private static ReportStatusUseCase theReportStatusUseCase;
    
    // Variable declaration
    private StoreDatabase database;
}
