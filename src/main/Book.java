package main;

/**
* <h1>Book Class</h1>
* The book class is a sub class of the
* Reference class. It stores the unique values
* for the book type.
*
* @author  Nick Major
* @since   12-02-15
*/
public class Book extends Reference
{
    private String Authors;
    private String Publisher;
    
/**
 * @param type book or journal
 * @param title Title of the book
 * @param callNumber Call number of the book
 * @param Year Year of the book
 * @param  auth Authors of the book
 * @param  pub Publisher of the book
*/
    public Book (String type, String title, String callNumber, int Year, String auth, String pub)
    {
        super(type, callNumber, Year, title);
        Authors = auth;
        Publisher = pub;
    }
    
/**
 * Method that returns Authors
 * @return Authors of the book
*/
    public String getAuthors()
    {
        return new String(Authors);
    }
    
/**
 * Method that returns the Publisher
 * @return Publisher of the book
*/
    public String getPublisher()
    {
        return new String(Publisher);
    }
    
/**
 * Check for the equality of two books
 * @param other Book being compared
 * @return true if the books are equal
*/
    @Override
    public boolean equals(Reference other)
    {
        if (other == null || other instanceof Journal)
        {
            return false;
        }
	else 
        {
            Book temp = (Book)other;
            return super.getCallNumber().equalsIgnoreCase(other.getCallNumber()) &&
		super.getTitle().equalsIgnoreCase(other.getTitle()) &&
		Authors.equalsIgnoreCase(temp.getAuthors()) &&
                super.getYear() == other.getYear() && Publisher.equalsIgnoreCase(temp.getPublisher());
	}
    }
	
/**
 * Shows the content of a book in a string
 * @return String of book values
*/
    @Override
    public String toString() 
    {
        return "Book: " + super.getCallNumber() + ". " +
                        Authors + ". \"" +
			super.getTitle() + "\". " +
			Publisher + ", " + super.getYear() + ".\n";
    }
}
