package main;

/**
* <h1>Journal Class</h1>
* The journal class is a sub class of the
* Reference class. It stores the unique values
* for the journal type.
*
* @author  Nick Major
* @since   12-02-15
*/
public class Journal extends Reference
{
    private String Organization;
    
 /**
 * @param type book or journal
 * @param  callNumber the call number of the journal
 * @param  Year the year of the journal
 * @param  org the organization of the journal
 * @param  title the title of the journal
*/
    public Journal (String type, String title, String callNumber, int Year, String org)
    {
        super(type, callNumber, Year, title);
        Organization = org;
    }
    
/**
 * Method returns the Organization
 * @return Organization of journal
*/
    public String getOrganization()
    {
        return new String(Organization);
    }
    
/**
 * Checks for the equality of two journals
 * @param other the journal object being compared
 * @return true if the journals are equal
*/
    @Override
    public boolean equals(Reference other)
    {
        if (other == null || other instanceof Book)
        {
            return false;
        }
        else
        {
            Journal temp = (Journal)other;
            return super.getCallNumber().equalsIgnoreCase(other.getCallNumber()) &&
		super.getTitle().equalsIgnoreCase(other.getTitle()) &&
		Organization.equalsIgnoreCase(temp.getOrganization()) &&
		super.getYear() == other.getYear();
	}
    }
	
/**
 * Shows the content of a journal in a string
 * @return String of journal values
*/
    @Override
    public String toString() 
    {
        return "Journal: " + super.getCallNumber() + ". \"" +
            super.getTitle() + "\". " +
            Organization + ", " + super.getYear() + ".\n";
    }
}
