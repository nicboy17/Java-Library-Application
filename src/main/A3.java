package main;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.awt.Toolkit;
//import com.apple.eawt.Application; Mac platform
import static java.awt.event.KeyEvent.VK_ENTER;
import javax.swing.border.EmptyBorder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
* <h1>A3 Class</h1>
* The A3 class is the main class that handles the GUI interface, actions from user, and 
* reading/writing to file.
*
* @author  Nick Major
* @since   12-02-15
*/
public class A3 extends JFrame
{
    /*Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png"));
    static ImageIcon img = new ImageIcon("/icon.png");*/
    
    private static JFrame frame = new JFrame("Library Search");
    private static AddPanel p1 = new AddPanel();
    private static SearchPanel p2 = new SearchPanel();
    private static JPanel message = new JPanel();
    
    private static JMenuItem add = new JMenuItem("Add");
    private static JMenuItem search = new JMenuItem("Search");
    private static JMenuItem quit = new JMenuItem("Quit", KeyEvent.VK_Q);
    
    private static JLabel initial = new JLabel ("<html>Welcome to Libary Search.<br><br>"
            + "Choose a command from the Commands menu above for<br>"
            + "adding a reference, searching references, or quitting the<br>"
            + "program<html>");
    private static LibrarySearch library = new LibrarySearch();
    private static String filename = " ";
    
/**
 * loads file from user, and runs window
 * @param args the command line arguments 
 * @throws java.lang.Exception for file loading
*/    
    public static void main(String[] args) throws Exception
    {
        Load (args, library);
e
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window();
            }
        }); 
    }
    
/**
 * Loads in file if user specified input. The user must enter in at least
 * one file for the output of the library.
 * @param args files for input/output
 * @param library Library class
 * @throws java.lang.Exception from file loading
*/     
    public static void Load (String args[], LibrarySearch library) throws Exception
    {
        String year ;
        String authors;
        String callNumber;
        String title;
        String publisher;
        String organization;
        
        if (args.length == 0) 
        {
            System.out.println("Sorry, no ouput file was specified at command line");
            System.out.println("Program ended. Please run program again with at least an output file.");
            System.exit(0);
        }

        if (args.length == 1)
        {
            filename = args[0] + "/"; 
        }
        
        if (args.length == 2)
        {
            filename = args[1] + "/";
            Scanner inputStream;
            try 
            {
                inputStream = new Scanner(new FileInputStream(args[0]));
                String type;
                while (inputStream.hasNextLine()) 
                {
                    type = inputStream.nextLine().replace("Type: ", "");
                    if (type.equalsIgnoreCase("book")) 
                    {
                        title = inputStream.nextLine().replace("Title: ", "");
                        callNumber = inputStream.nextLine().replace("callNumber: ", "");
                        year = inputStream.nextLine().replace("Year: ", "");
                        authors = inputStream.nextLine().replace("Authors: ", "");
                        publisher = inputStream.nextLine().replace("Publisher: ", "");
                        library.addBook(new Book("book", title, callNumber, Integer.parseInt(year), authors, publisher));
                    } 
                    else if (type.equalsIgnoreCase("journal")) 
                    {  
                        title = inputStream.nextLine().replace("Title: ", "");
                        callNumber = inputStream.nextLine().replace("callNumber: ", "");
                        year = inputStream.nextLine().replace("Year: ", "");
                        organization = inputStream.nextLine().replace("Organization: ", "");
                        library.addJournal(new Journal("journal", title, callNumber, Integer.parseInt(year), organization));
                    }
                }
            inputStream.close();
            System.out.println("Input file loaded successfully");
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Sorry, the input file did not have the right format");
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Sorry, the input file was not found. No books or journals were loaded");
            }
        }
    }
    
/**Sets window to initial screen, displaying message for user*/
    public void init() 
    {  
        message.setBorder(new EmptyBorder(0, 40, 10, 0));
        message.setLayout(new BoxLayout(message, BoxLayout.X_AXIS));
        initial.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.add(initial, BorderLayout.CENTER);
    }
    
/**Sets up window by adding menu bar, icon, setting size, calling init(), centering window*/
    public static void window()
    {
        JMenuBar menuBar;
        JMenu menu;
        menuBar = new JMenuBar();

        menu = new JMenu("Commands");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);
        
        frame.setJMenuBar(menuBar);
        
        menu.add(add);
        add.addActionListener(new menuAdd());
        
        menu.add(search);
        search.addActionListener(new menuSearch());
        
        menu.add(quit);
        quit.addActionListener(new menuQuit());
        
        frame.pack();
        //frame.setIconImage(img.getImage());
        A3 gui = new A3();
        gui.init();
        frame.setSize(450,300);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth()-frame.getWidth())/2);
        int y = (int) ((dimension.getHeight()-frame.getHeight())/2);
        frame.setLocation(x,y);
        //gui.changeDock(); mac platform
        
        frame.addWindowListener(exitListener);
        frame.add(message, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
 
    }
    
/**Custom exit listener, so when the user presses x it saves all the records*/
    public static WindowListener exitListener = new WindowAdapter() 
    {
        @Override
        public void windowClosing(WindowEvent e) 
        {
               library.catalogRecords(filename);
               System.exit(0);
        }
    };

/**Quit button listener in the menu bar*/
    public static class menuQuit implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            library.catalogRecords(filename);
            System.exit(0);
        }
    }
    
/**Add button listener in the menu bar*/
    public static class menuAdd implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            frame.setSize(510,425);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth()-frame.getWidth())/2);
            int y = (int) ((dimension.getHeight()-frame.getHeight())/2);
            frame.setLocation(x,y);
            frame.add(p1);
            frame.remove(p2);
            frame.remove(message);
            p2.setVisible(false);
            p1.setVisible(true);

            frame.validate();
            
            add.setEnabled(false);
            search.setEnabled(true);
            frame.removeKeyListener(new searchKey());
            AddPanel.reset();
            AddPanel.resetResult();
            SearchPanel.reset();
            SearchPanel.resetResult();
        }
    }
   
/**Search button listener in the menu bar*/
    public static class menuSearch implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            frame.setSize(510,425);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth()-frame.getWidth())/2);
            int y = (int) ((dimension.getHeight()-frame.getHeight())/2);
            frame.setLocation(x,y);
            frame.add(p2);
            frame.remove(p1);
            frame.remove(message);
            p2.setVisible(true);
            p1.setVisible(false);
            
            frame.validate();
            
            search.setEnabled(false);
            add.setEnabled(true);
            frame.addKeyListener(new searchKey());
            AddPanel.reset();
            AddPanel.resetResult();
            SearchPanel.reset();
            SearchPanel.resetResult();
        }
    }

/**Add button listener in the Add window*/
    public static class addAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            add();
        }
    }
    
/**Key listener so user can press enter within any text field to add record*/
    public static class addKey implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == VK_ENTER)
            {
                add();
            }
        }
        @Override
        public void keyTyped(KeyEvent e){}
        @Override
        public void keyReleased(KeyEvent e) {}
    }
    
/**Search button listener in the search window*/
    public static class searchAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            search();
        }
    }
    
/**Key listener so user can press enter within any text field to search record*/
    public static class searchKey implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == VK_ENTER)
            {
                search();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {
            //automatic searching
            //search();
        }
    }

/**Adds record if the title, year, and call number aren't empty*/
    public static void add ()
    {
        int year = 0;
        boolean warning = false;
        Book book;
        Journal journal;
        
        //clears messages to user
        AddPanel.resetResult();

        //Checks title
        if (p1.getTitle().trim().isEmpty())
        {
            warning = true;
            AddPanel.appendResult("**The title can not be empty\n");
        }

        //Checks call number 
        if (p1.getCall().isEmpty())
        {
           AddPanel.appendResult("**The call number can not be empty\n");
           warning = true; 
        }

        //checks year, if integer, if 4 digit number
        if (p1.getYear().isEmpty())
        {
           AddPanel.appendResult("**The year can not be empty\n");
           warning = true; 
        }
        else if (!isInteger(p1.getYear()))
        {
            AddPanel.appendResult("**The year is not an integer**\n");
            warning = true;
        }
        else if (isInteger(p1.getYear()) && !p1.getYear().isEmpty())
        {
            year = Integer.parseInt(p1.getYear()); 
        }
        if (isInteger(p1.getYear()) && (year < 1000 || year > 9999))
        {
            AddPanel.appendResult("**The year is not a valid 4 digit integer**\n");
            warning = true;
        }
        
        //Checks to make sure call number isn't already used
        if (!library.checkAdd(p1.getCall(), year) && warning == false)
        {
            AddPanel.appendResult("*Sorry, this call number has already been used");
            warning = true;
        }

        //if there aren't any warnings, add book or journal
        if (AddPanel.referenceState().equals("book") && warning == false)
        {
            book = new Book("book", p1.getTitle(), p1.getCall(), year, p1.getAuthors(), p1.getPublisher());
            library.addBook(book);
            AddPanel.appendResult("***Succesfully added: " + book.toString());
            AddPanel.reset();
        }
        else if (AddPanel.referenceState().equals("journal") && warning == false)
        {
            journal = new Journal("journal", p1.getTitle(), p1.getCall(), year, p1.getOrganization());
            library.addJournal(journal);
            AddPanel.appendResult("***Succesfully added: " + journal.toString());
            AddPanel.reset();
        }
    }
    
/**Searches records based on text fields*/
    public static void search ()
    {
        int start = 0;
        int end = 0;
        boolean warning = false;

        //clears results/messages
        SearchPanel.resetResult();

        //Start Year
        if (isInteger(p2.getStart()))
        {
            start = Integer.parseInt(p2.getStart()); 
        }
        else if (p2.getStart().isEmpty())
        {
            start = 1000;
        }
        else if (!isInteger(p2.getStart()))
        {
            SearchPanel.appendResult("**The start year is not an integer**\n");
            warning = true;
        }

        if (isInteger(p2.getStart()) && (start < 1000 || start > 9999))
        {
            SearchPanel.appendResult("**The start year is not a valid 4 digit integer**\n");
            warning = true;
        }

        //End Year
        if (isInteger(p2.getEnd()))
        {
            end = Integer.parseInt(p2.getEnd()); 
        }
        else if (p2.getEnd().isEmpty())
        {
            end = 9999;
        }
        else if (!isInteger(p2.getEnd()))
        {
            SearchPanel.appendResult("**The end year is not an integer**\n");
            warning = true;
        }

        if (isInteger(p2.getEnd()) && (end < 1000 || end > 9999))
        {
            SearchPanel.appendResult("**The start year is not a valid 4 digit integer**\n");
            warning = true;
        }

        //if the start year is less than end year, search records
        if (start <= end && warning == false)
        {
            library.search(p2.getKeywords(), p2.getCall(), start, end);
            SearchPanel.reset();
        }
        else if (start > end && warning == false)
        {
            SearchPanel.appendResult("**The start year needs to be less than the end year**\n");
        }
    }
    
/**Simple method that checks if a string is an integer
 * @param s String to checks
 * @return true if the string is an integer
 */
    public static boolean isInteger(String s) 
    {
        try 
        { 
            Integer.parseInt(s); 
        } 
        catch(NumberFormatException | NullPointerException e) 
        { 
            return false; 
        }
        return true;
    }   
    
    //mac platform
    /*public void changeDock()
    {
        Application application = Application.getApplication();
        application.setDockIconImage(image);
    }*/
}
