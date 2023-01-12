/* Game.java */
package videostore.model;

/** Represents all the information for a game title
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class Game extends Title implements java.io.Serializable
{

    /** Constructor for a game title
     * 
     * @param gameName the title of this game
     * @param gameCompany the company that made the game
     * @param gameYear the year the game came out
     */
    public Game(String gameName, String gameCompany, String gameYear)
    {
        super(gameName, gameCompany, gameYear);
        this.name = gameName;
        this.company = gameCompany;
        this.year = gameYear;
        rentalFee = 2.5;
        rentalPeriod = 2;
        numberOfCopies = 0;
    }
    
    /** Accessor for the title of this game
     * 
     * @return the title of the game
     */
    public String getName()
    {
        return name;
    }
    
    /** Accessor for the title of this game
     * 
     * @return the title of the game
     */
    public int getRentalPeriod()
    {
        return rentalPeriod;
    }
    
    /** Accessor for the title of this game
     * 
     * @return the title of the game
     */
    public double getRentalCharge()
    {
        return rentalFee;
    }
    
    /** Accessor for the company variable for a game
     * 
     * @return the company that made the game in the form of a string
     */
    public String getCreator()
    {
        return company;
    }
    
    /** Accessor for the year variable for a game
     * 
     * @return they year of a game
     */
    public String getYear()
    {
        return year;
    }
    
    /** Mutator that changes the number of copies associate with a game
     * 
     */
    public void addCopy()
    {
        numberOfCopies += 1;
    }
    
    /** Accessor for the number of copies associated with a game
     * 
     * @return the number of copies of a game
     */
    public int getCopies()
    {
        return numberOfCopies;
    }
    
    /** Represents a game object in the form of a string
     * 
     * @return the name of the game
     */
    public String toString()
    {
        return name;
    }

    // Variable declaration
    private String company;
    private double rentalFee;
    private int rentalPeriod;
    private int numberOfCopies;
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
}
