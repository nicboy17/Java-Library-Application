package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Library Search Class</h1>
 * The library search class adds, checks, prints, and searches 
 * records in the library.
 *
 * @author  Nick Major
 * @since   12-02-15
*/
public class LibrarySearch 
{
    private ArrayList <Reference> library = new ArrayList();
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();

/**
 * adds book to list and adds keywords to hash map
 * @param book Book to be added  
*/        
    public void addBook (Book book)
    {
        library.add (book);
        addToHashMap(book.getTitle().toLowerCase().split("[ ,\n]+"));
    }
    
/**
 * adds journal to list and adds keywords to hash map
 * @param journal Journal to be added  
*/      
    public void addJournal (Journal journal)
    {
        library.add (journal);
        addToHashMap(journal.getTitle().toLowerCase().split("[ ,\n]+"));
    }
    
/**
 * adds library to specified text file
 * @param fileName Filename for the library to be saved 
*/      
    public void catalogRecords (String fileName) 
    {
       PrintWriter catalog;
        try 
        {
            catalog = new PrintWriter(new FileOutputStream(fileName));

            for (int i = 0; i < library.size(); i++)
            {
                catalog.println("Type: " + library.get(i).getType());
                catalog.println("Title: " + library.get(i).getTitle());
                catalog.println("callNumber: " + library.get(i).getCallNumber());
                catalog.println("Year: " + library.get(i).getYear());
                
                //Checks to see wether it is a book or journal object
                Reference ref = library.get(i);
                if (ref instanceof Book)
                {
                    Book book = (Book)ref;
                    catalog.println("Authors: " + book.getAuthors());
                    catalog.println("Publisher: " + book.getPublisher());
                }
            
                ref = library.get(i);
                if (ref instanceof Journal)
                {
                    Journal journal = (Journal)ref;
                    catalog.println("Organization: " + journal.getOrganization());
                }
            }
            System.out.println("Records saved");
            catalog.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Journal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/**
* checks to see if the call number can be added to the object
* @param callNum Call Number 
* @param year Year 
* @return true if call number can't be added
*/        
    public boolean checkAdd (String callNum, int year)
    {
        int i;
        boolean add = true;
        for (i = 0; i < library.size(); i ++)
        {
            if (library.get(i).getCallNumber().equals(callNum) && library.get(i).getYear() == year)
            {
                add = false;
                break;
            }   
        }
        return add;
    }

/**
 * Prints all of the books and journals from the library
 * @param lib List of objects to be printed
*/        
    public void print (ArrayList<Reference> lib)
    {
        int i;
        if (lib.size() > 0)
        {
            for (i = 0; i < lib.size(); i ++)
            {             
                Reference ref = lib.get(i);
                if (ref instanceof Book)
                {
                    Book book = (Book)ref;
                    SearchPanel.appendResult(book.toString());
                }
            
                ref = lib.get(i);
                if (ref instanceof Journal)
                {
                    Journal journal = (Journal)ref;
                    SearchPanel.appendResult(journal.toString());
                }
            }
        }
        else
        {
            SearchPanel.appendResult("*Sorry, 0 records were found*");
        }
    }   

/**
 * Adds keywords to Hash Map. If the map doesn't contain one of the words,
 * the word is added to the Hash map along with a new array to keep
 * track of the objects that contain the keyword. Then adds the index of the 
 * last added object to each of its keywords.
 * @param keywords Keywords 
*/
    public void addToHashMap (String keywords[])
    {
        for (String key : keywords) 
        {
            if(!map.containsKey(key))
            {
                map.put(key, new ArrayList<>());
            } 
            map.get(key).add(library.size() - 1);
        }
    }
    
/**
 * Searches Hash Map for keywords and returns library of results
 * @param keywords Keywords to search
 * @return list of objects found
*/    
    public ArrayList<Reference> searchHashMap (String keywords[])
    {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList <Reference> temp = new ArrayList<>();
        
        for (String key : keywords) 
        {
            if(map.containsKey(key))
            {
                result.addAll(map.get(key));
            } 
        }
        for (Integer place : result) 
        { 
            temp.add(library.get(place));
        }
        
        return temp;
    }

/**
* searches the library for the specified fields and adds results to a temporary
* library which is returned
* @param lib temporary library of results
* @param callNum Call Number
* @param start Start Year
* @param end End Year
* @return list of objects found
*/          
    public ArrayList<Reference> searchRecords (ArrayList<Reference> lib, String callNum, int start, int end)
    {
        int i;
        ArrayList<Reference> temp = new ArrayList<>();
        
        for (i = 0; i < lib.size(); i ++)
        {
            if (lib.get(i).getCallNumber().equalsIgnoreCase(callNum))
            {
                temp.add(lib.get(i));
            }
            else if (!lib.get(i).getCallNumber().equalsIgnoreCase(callNum)&& !callNum.isEmpty())
            {
                lib.remove(lib.get(i));
            }
            else if (lib.get(i).getYear() >= start && lib.get(i).getYear() <= end && (start != 1000 || end != 9999))
            {
                temp.add(lib.get(i));
            }
        }

        return temp;
    }

/**
* searches in the references list for the book and journal lists for the specified fields
* and prints results
* @param keywords Keywords to search
* @param callNumber Call number to search
* @param start Start year to search
* @param end End year to search
*/        
    public void search (String keywords, String callNumber, int start, int end)
    {        
        ArrayList<Reference> result;
        //if everything is empty
        if (callNumber.trim().isEmpty() && keywords.trim().isEmpty() && start == 1000 && end == 9999)
        {  
            print(library);
        }
        //searches only hash map
        else if (callNumber.trim().isEmpty() && !keywords.trim().isEmpty() && start == 1000 && end == 9999)
        {
            result = searchHashMap(keywords.split("[ ,\n]+"));
            print(result);
        }
        //search for the call number and time period
        else if (keywords.trim().isEmpty())      
        {
            result = searchRecords(new ArrayList(library), callNumber, start, end);
            print(result);
        }
        //search all parameters
        else 
        {
            result = searchHashMap(keywords.split("[ ,\n]+"));
            result = searchRecords(result, callNumber, start, end);
            print(result);
        }
    }
}
