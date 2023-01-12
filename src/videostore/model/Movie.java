/* Movie.java */

package videostore.model;

/** Represents all the information for a movie title
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class Movie extends Title implements java.io.Serializable
{
    /** Constructor for a movie title
     * 
     * @param movieName the title of this movie
     */
    public Movie(String movieName, String movieDirector, String movieYear)
    {
        super(movieName, movieDirector, movieYear);
        this.name = movieName;
        this.director = movieDirector;
        this.year = movieYear;
        rentalFee = 3.0;
        rentalPeriod = 1;
        numberOfCopies = 0;
    }
    
    /** Accessor for the title of this movie
     * 
     * @return the title of the movie
     */
    public String getName()
    {
        return name;
    }
    
    /** Accessor for the rental period of this movie
     * 
     * @return the rental period of the movie
     */
    public int getRentalPeriod()
    {
        return rentalPeriod;
    }
    
    /** Accessor for the rental charge of this movie
     * 
     * @return the rental charge of the movie
     */
    public double getRentalCharge()
    {
        return rentalFee;
    }
    
    /** Accessor for the director variable for a movie
     * 
     * @return the name of the director of a movie
     */
    public String getCreator()
    {
        return director;
    }
    
    /** Accessor for the year variable for a movie
     * 
     * @return the year of a movie
     */
    public String getYear()
    {
        return year;
    }
    
    /** Mutator that changes the number of copies associate with a movie
     * 
     */
    public void addCopy()
    {
        numberOfCopies += 1;
    }
    
    /** Accessor for the number of copies associated with a movie
     * 
     * @return the number of copies of a movie
     */
    public int getCopies()
    {
        return numberOfCopies;
    }
    
    /** Represents the movie object as a string
     * 
     * @return the name of a movie
     */
    public String toString()
    {
        return name;
    }

    // Variable declaration
    private String director;
    private double rentalFee;
    private int rentalPeriod;
    private int numberOfCopies;
    
    // Symbolic constant needed to prevent unnecessary ClassCastExceptions
    // when reading a serialized object created by an earlier version of this
    // class
    static final long serialVersionUID = 1;
}
