/* Title.java */

package videostore.model;

/** Represents each title available in the store
 *
 * @author Adam Bartholomew and Steve Smith
 */
public abstract class Title implements java.io.Serializable
{
    /** Constructor for a title object in the Store Database
     * 
     * @param itemName the name of this Movie or Game
     * @param itemCreator the creator of the title (for a game it is the company, for a movie it is the director)
     * @param itemYear the year the title came out
     */
    public Title(String itemName, String itemCreator, String itemYear)
    {
        this.name = itemName;
        this.creator = itemCreator;
        this.year = itemYear;
    }
    
    // Abstract method to be called in a movie or game
    public abstract String getName();   
    
    // Abstract method to be called in a movie or game
    public abstract String getCreator();
    
    // Abstract method to be called in a movie or game
    public abstract String getYear();
    
    // Abstract method to be called in a movie or game
    public abstract int getRentalPeriod();
    
    // Abstract method to be called in a movie or game
    public abstract double getRentalCharge();
    
    // Abstract method to be called in a movie or game
    public abstract void addCopy();
    
    // Abstract method to be called in a movie or game
    public abstract int getCopies();
    
    // Abstract method to be called in a movie or game
    public abstract String toString();
    
    //Variable declaration
    protected String name;
    protected String creator;
    protected String year;
    protected double fee;
    protected int days;
    protected int numberOfCopies;
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
}
