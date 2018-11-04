package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * <h1>Search Panel Class</h1>
 * The Search Panel class handles the GUI interface for search 
 * references in the library.
 * 
 * @author Nick Major
 * @since   12-02-15
 */
public class SearchPanel extends JPanel
{
    //text fields
    private static JTextField Tcall = new JTextField (15);
    private static JTextField Tstart = new JTextField (10);
    private static JTextField Tend = new JTextField (10);
    private static JTextArea result = new JTextArea(9,0);
    private static JTextField title = new JTextField (18);

    public SearchPanel ()
    {
        //panels
        JPanel Pcall = new JPanel();
        JPanel Ptitle = new JPanel();
        JPanel Pend = new JPanel();
        JPanel Pstart = new JPanel();
        JPanel Pmessage = new JPanel();
        
        JPanel top = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JPanel bottom = new JPanel();
        
        //labels
        JLabel Ladd = new JLabel ("<html><b>Searching references<html>");
        JLabel Lcall = new JLabel ("<html>Call No:<html>");
        JLabel Ltitle = new JLabel ("<html>Title:<html>");
        JLabel Lmessage = new JLabel ("<html>Results<html>");
        JLabel Lstart = new JLabel ("<html>Start Year:<html>");
        JLabel Lend = new JLabel ("<html>End Year:<html>");
        
        //buttons
        JButton resetButton = new JButton ("Reset");
        JButton searchButton = new JButton ("Search");
        //button listeners
        searchButton.addActionListener(new A3.searchAction());
        resetButton.addActionListener(new resetAction());
        
        //scroll bars for result box
        JScrollPane scrollPane = new JScrollPane (result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        result.setEditable(false);
 
        setLayout(new BorderLayout());
            
        //shrinks text fields
        Tcall.setMaximumSize(Tcall.getPreferredSize());
        title.setMaximumSize(title.getPreferredSize());
        Tend.setMaximumSize(Tend.getPreferredSize());
        Tstart.setMaximumSize(Tstart.getPreferredSize());
        
        //Hot keys for users
        Tcall.addKeyListener(new A3.searchKey());
        title.addKeyListener(new A3.searchKey());
        Tend.addKeyListener(new A3.searchKey());
        Tstart.addKeyListener(new A3.searchKey());

        //sets panel layouts
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        Pmessage.setLayout(new BoxLayout(Pmessage, BoxLayout.X_AXIS));

        Pcall.setLayout(new BoxLayout(Pcall, BoxLayout.X_AXIS));
        Ptitle.setLayout(new BoxLayout(Ptitle, BoxLayout.X_AXIS));
        Pend.setLayout(new BoxLayout(Pend, BoxLayout.X_AXIS));
        Pstart.setLayout(new BoxLayout(Pstart, BoxLayout.X_AXIS));
        Pmessage.setLayout(new BoxLayout(Pmessage, BoxLayout.X_AXIS));

        //sets format for panel edges
        Pcall.setBorder(new EmptyBorder(0, 0, 0, 55));
        Pmessage.setBorder(new EmptyBorder(0, 0, 10, 0));
        Pstart.setBorder(new EmptyBorder(0, 0, 0, 110));
        Pend.setBorder(new EmptyBorder(0, 0, 0, 110));
        top.setBorder(new EmptyBorder(10, 10, 10, 0));
        left.setBorder(new EmptyBorder(0, 20, 10, 10));
        right.setBorder(new EmptyBorder(0, 0, 0, 20));
        bottom.setBorder(new EmptyBorder(0, 20, 20, 20));

        //adds components to panels
        top.add(Ladd);

        Pcall.add(Lcall);
        Pcall.add(Tcall);

        Ptitle.add(Ltitle);
        Ptitle.add(Box.createRigidArea(new Dimension(10,0)));
        Ptitle.add(title);

        Pstart.add(Lstart);
        Pstart.add(Tstart);

        Pend.add(Lend);
        Pend.add(Tend);

        right.add(resetButton);
        right.add(Box.createRigidArea(new Dimension(5,30)));
        right.add(searchButton);

        Pmessage.add(Lmessage);
        bottom.add(Pmessage);
        bottom.add(scrollPane);

        left.add(Pcall);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Ptitle);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Pstart);
        left.add(Box.createRigidArea(new Dimension(0,5)));
        left.add(Pend);

        this.add(top, BorderLayout.NORTH);
        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);
        this.add(bottom, BorderLayout.SOUTH);     
    }
    
    /**
     * Appends string to results box
     * @param results String to add to results box
     */
    public static void appendResult(String results)
    {
        result.append(results);
    }
    
    /**
     * Resets text in results box
     */
    public static void resetResult()
    {
        result.setText("");
    }
    
    /**
     * @return Text in title text field
     */
    public String getKeywords()
    {
        return new String(title.getText());
    }
    
    /**
     * @return Text in call number text field
     */
    public String getCall()
    {
        return new String(Tcall.getText());
    }
    
    /** 
     * @return Text in start year text field
     */
    public String getStart()
    {
        return new String(Tstart.getText());
    }
    
    /**
     * @return Text in end year text field
     */
    public String getEnd()
    {
        return new String(Tend.getText());
    }
    
    /**
     * resets all of the text fields
     */
    public static void reset()
    {
        Tcall.setText("");
        title.setText("");
        Tstart.setText("");
        Tend.setText("");
    }
    
    /**
     * reset button
     */
    private static class resetAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            reset();
            resetResult();
        }
    } 
}
