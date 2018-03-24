import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
--                Publisher Tab              --
-- box 1 --
select unique publisher_name
from henry_publisher;

-- box 2, replace tor books with publisher from box 1 --
select title
from henry_book
where publisher_code =
(select publisher_code
from henry_publisher
where publisher_name = 'Tor Books');

-- box 3, replace second wind with title from box 2 --
select price
from henry_book
where title = 'Second Wind';
 */






public class SearchByPublisherPanel extends JPanel{
    @SuppressWarnings("unchecked")
    public static JPanel publisher() {

        JPanel mainPanel = new JPanel();

        // Make author dropdown
        String[] publisher = {"--Select A Publisher--", "pub2", "pub3"};
        JComboBox publisherDropDown = new JComboBox(publisher);
        publisherDropDown.setFont(new Font("Serif", Font.PLAIN, 14));


        // Add a listener to the button so we can do something when it is clicked
        publisherDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This will be called when the button is clicked
                JComboBox combo = (JComboBox)e.getSource();
                String selection = (String)combo.getSelectedItem();
                System.out.println("Publisher selected: " + selection);
            }

        });

        // Make bookstore dropdown
        String[] book = {"--Select A Publisher--", "pub2", "pub3"};
        JComboBox bookDropDown = new JComboBox(book);
        bookDropDown.setFont(new Font("Serif", Font.PLAIN, 14));


        // Add a listener to the button so we can do something when it is clicked
        bookDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This will be called when the button is clicked
                JComboBox combo = (JComboBox)e.getSource();
                String selection = (String)combo.getSelectedItem();
                System.out.println("Book selected: " + selection);
            }
        });

//		Print out the cost of the book in a text box
        double bookPrice = 12.75;
        JTextField price = new JTextField("$" + "              "+ bookPrice);
        price.setFont(new Font("Serif", Font.PLAIN, 14));



//		Print out the bookstore and number of books available
        String[] columnNames = {"Bookstore", "# Books Available"};
        Object[][] data = {{"Store1", new Integer(1)},{"Store2",new Integer(2)}, {"Store3", new Integer(3)}};
        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Serif", Font.PLAIN, 14));


//		Layout of the JPanel
        BorderLayout layout = new BorderLayout(5, 5);
        mainPanel.setLayout(layout);

// 		Add the components to the JPanel
        mainPanel.add(publisherDropDown, BorderLayout.WEST);
        mainPanel.add(bookDropDown, BorderLayout.CENTER);
        mainPanel.add(price, BorderLayout.EAST);
        mainPanel.add(table, BorderLayout.SOUTH);

        // Make the frame visible and layout the components on it
        mainPanel.setVisible(true);
        return mainPanel;
    }

}
