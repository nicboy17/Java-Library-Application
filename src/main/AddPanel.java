package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * <h1>Add Panel Class</h1>
 * The Add Panel class handles the GUI interface for adding 
 * reference to the library.
 * 
 * @author Nick Major
 * @since   12-02-15
 */
public class AddPanel extends JPanel
{
    //All of the text tields
    private static JTextField Tcall = new JTextField (15);
    private static JTextField Tauthors = new JTextField (25);
    private static JTextField Ttitle = new JTextField (25);
    private static JTextField Tpublisher = new JTextField (25);
    private static JTextField Tyear = new JTextField (10);
    private static JTextField Torganization = new JTextField (23);
    private static JTextArea result = new JTextArea(6,0);
    private static JComboBox reference;

    public AddPanel ()
    { 
        //Panels
        JPanel Ptype = new JPanel();
        JPanel Pcall = new JPanel();
        JPanel Pauthors = new JPanel();
        JPanel Ptitle = new JPanel();
        JPanel Ppublisher = new JPanel();
        JPanel Pyear = new JPanel();
        JPanel Porganization = new JPanel();
        JPanel Pmessage = new JPanel();

        JPanel top = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JPanel bottom = new JPanel();

        //Labels for text fields
        JLabel Ladd = new JLabel ("<html><b>Adding a reference<html>");
        JLabel Ltype = new JLabel ("<html>Type:<html>");
        JLabel Lcall = new JLabel ("<html>Call No:<html>");
        JLabel Lauthors = new JLabel ("<html>Authors:<html>");
        JLabel Ltitle = new JLabel ("<html>Title:<html>");
        JLabel Lpublisher = new JLabel ("<html>Publisher:<html>");
        JLabel Lyear = new JLabel ("<html>Year:<html>");
        JLabel Lmessage = new JLabel ("<html>Messages<html>");
        JLabel Lorganization = new JLabel ("<html>Organization:<html>");

        //buttons
        JButton resetButton = new JButton ("Reset");
        JButton addButton = new JButton ("Add");
        
        //scrolls for messages text area
        JScrollPane scrollPane = new JScrollPane (result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        result.setEditable(false); //read only
        
        setLayout(new BorderLayout());
        
        //combo box options
        String ref[] = {"book", "journal"};
        reference = new JComboBox (ref);
        reference.setSelectedIndex(0);
        reference.setMaximumSize(reference.getPreferredSize()); //shrinks combobox

        //shrinks text fields
        Tcall.setMaximumSize(Tcall.getPreferredSize());
        Tauthors.setMaximumSize(Tauthors.getPreferredSize());
        Ttitle.setMaximumSize(Ttitle.getPreferredSize());
        Tpublisher.setMaximumSize(Tpublisher.getPreferredSize());
        Tyear.setMaximumSize(Tyear.getPreferredSize());
        Torganization.setMaximumSize(Torganization.getPreferredSize());
        
        //adds hot key for user
        Tcall.addKeyListener(new A3.addKey());
        Tauthors.addKeyListener(new A3.addKey());
        Ttitle.addKeyListener(new A3.addKey());
        Tpublisher.addKeyListener(new A3.addKey());
        Tyear.addKeyListener(new A3.addKey());
        Torganization.addKeyListener(new A3.addKey());
        
        //adds listener for buttons
        addButton.addActionListener(new A3.addAction());
        resetButton.addActionListener(new resetAction());

        //sets all of the panels layouts
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        Ptype.setLayout(new BoxLayout(Ptype, BoxLayout.X_AXIS));
        Pcall.setLayout(new BoxLayout(Pcall, BoxLayout.X_AXIS));
        Pauthors.setLayout(new BoxLayout(Pauthors, BoxLayout.X_AXIS));
        Ptitle.setLayout(new BoxLayout(Ptitle, BoxLayout.X_AXIS));
        Ppublisher.setLayout(new BoxLayout(Ppublisher, BoxLayout.X_AXIS));
        Porganization.setLayout(new BoxLayout(Porganization, BoxLayout.X_AXIS));
        Pyear.setLayout(new BoxLayout(Pyear, BoxLayout.X_AXIS));
        Pmessage.setLayout(new BoxLayout(Pmessage, BoxLayout.X_AXIS));

        //formats all of the edges of panels
        Ptype.setBorder(new EmptyBorder(0, 0, 0, 213));
        Pcall.setBorder(new EmptyBorder(0, 0, 0, 110));
        Pmessage.setBorder(new EmptyBorder(0, 0, 10, 0));
        Porganization.setBorder(new EmptyBorder(0, 0, 0, 0));
        Pyear.setBorder(new EmptyBorder(0, 0, 0, 165));
        top.setBorder(new EmptyBorder(10, 10, 10, 0));
        left.setBorder(new EmptyBorder(0, 20, 10, 10));
        right.setBorder(new EmptyBorder(0, 0, 0, 20));
        bottom.setBorder(new EmptyBorder(0, 20, 20, 20));

        //adds objects to panels
        top.add(Ladd);

        Ptype.add(Ltype);
        Ptype.add(reference);

        Pcall.add(Lcall);
        Pcall.add(Tcall);

        Pauthors.add(Lauthors);
        Pauthors.add(Box.createRigidArea(new Dimension(10,0))); //spacing
        Pauthors.add(Tauthors);

        Ptitle.add(Ltitle);
        Ptitle.add(Box.createRigidArea(new Dimension(10,0)));
        Ptitle.add(Ttitle);

        Porganization.add(Lorganization);
        Porganization.add(Box.createRigidArea(new Dimension(10,0)));
        Porganization.add(Torganization);

        Ppublisher.add(Lpublisher);
        Ppublisher.add(Box.createRigidArea(new Dimension(10,0)));
        Ppublisher.add(Tpublisher);

        Pyear.add(Lyear);
        Pyear.add(Tyear);

        right.add(Box.createRigidArea(new Dimension(5,10)));
        right.add(resetButton);
        right.add(Box.createRigidArea(new Dimension(5,55)));
        right.add(addButton);

        Pmessage.add(Lmessage);
        bottom.add(Pmessage);
        bottom.add(scrollPane);
        Lorganization.setVisible(false); 
        Torganization.setVisible(false);

        left.add(Ptype);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Pcall);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Pauthors);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Ptitle);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Ppublisher);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Pyear);

        //formats window based on combo box
        reference.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(e.getItem().toString().equals("book"))
                {
                    reset();
                    resetResult();
                    Lorganization.setVisible(false); 
                    Torganization.setVisible(false); 
                    Lauthors.setVisible(true);
                    Tauthors.setVisible(true);
                    Lpublisher.setVisible(true);
                    Tpublisher.setVisible(true);
                    left.removeAll();
                    
                    left.add(Ptype);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Pcall);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Pauthors);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Ptitle);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Ppublisher);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Pyear);
                }
                else if(e.getItem().toString().equals("journal"))
                {
                    reset();
                    resetResult();
                    Lauthors.setVisible(false);
                    Tauthors.setVisible(false);
                    Lpublisher.setVisible(false);
                    Tpublisher.setVisible(false);
                    Lorganization.setVisible(true); 
                    Torganization.setVisible(true);
                    left.removeAll();
                    
                    left.add(Ptype);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Pcall);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Ptitle);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Porganization);
                    left.add(Box.createRigidArea(new Dimension(0,5)));
                    left.add(Pyear);
                }
            }});
        this.add(top, BorderLayout.NORTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(bottom, BorderLayout.SOUTH);
    }
    
/**
 * Appends string to message box
 * @param results message to add to box
 */
    public static void appendResult(String results)
    {
        result.append(results);
    }
    
/**
 * resets the text in message box
 */
    public static void resetResult()
    {
        result.setText("");
    }
    
/**
 * @return Text from title text field
 */
    public String getTitle()
    {
        return new String(Ttitle.getText());
    }
    
/**
 * @return Text from call number text field
 */
    public String getCall()
    {
        return new String(Tcall.getText());
    }
    
/**
 * @return Text from year text field
 */
    public String getYear()
    {
        return new String(Tyear.getText());
    }
    
/**
 * @return Text from authors text field
 */
    public String getAuthors()
    {
        return new String(Tauthors.getText());
    }
    
/**
 * @return Text from publisher text field
 */
    public String getPublisher()
    {
        return new String(Tpublisher.getText());
    }
    
/**
 * @return Text from organization text field
 */
    public String getOrganization()
    {
        return new String(Torganization.getText());
    }
    
/**
 * Returns the state of the combo box 
 * @return the current record type
 */
    public static String referenceState()
    {
        if (AddPanel.reference.getSelectedIndex() == 0)
        {
            return "book";
        }
        return "journal";
    }
    
/**
 * Resets all of the text fields
 */
    public static void reset()
    {
        Tcall.setText("");
        Tauthors.setText("");
        Ttitle.setText("");
        Tpublisher.setText("");
        Tyear.setText("");
        Torganization.setText("");
    }

/**
 * Reset button listener
 */
    private static class resetAction implements ActionListener
     {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            reset();
            reference.setSelectedIndex(0);
            result.setText("");
        }
    }
}
