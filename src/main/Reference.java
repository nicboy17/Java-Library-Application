package main;

/**
* <h1>Reference Class</h1>
* The Reference class is the super class
* of the book and journal classes. It holds
* all of the common values of both classes
* as well as methods.
*
* @author  Nick Major
* @since   12-02-15
*/
public abstract class Reference
{
    private String callNumber;
    private int Year;
    private String Title;
    private String Type;

/**
 * @param type book or journal
 * @param callNum Call Number
 * @param year Year
 * @param  tit Title
*/
    public Reference (String type, String callNum, int year, String tit)
    {
        Type = type;
        callNumber = callNum;
        Year = year;
        Title = tit;
    }
    
/**
 * Shows the content of a book or journal in a string
 * @return String of object values
*/
    @Override
    public abstract String toString();

/**
 * Check for the equality of two books or two journals
 * @param other objects being compared
 * @return true if the objects are equal
*/
    public abstract boolean equals(Reference other);
    
/**
 * Method that returns type
 * @return type of object
*/
    public String getType ()
    {
        return new String(Type);
    }
    
/**
 * Method that returns call number
 * @return Call Number of the object
*/    
    public String getCallNumber()
    {
        return new String(callNumber);
    }

/**
 * Method that returns year
 * @return Year of the object
*/
    public int getYear()
    {
        return new Integer(Year);
    }

/**
 * Method that returns title
 * @return Title of the object
*/    
    public String getTitle()
    {
        return new String(Title);
    }
}
