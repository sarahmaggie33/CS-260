import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

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
        HenryDAO theDAO = new HenryDAO();
		ArrayList<String> bookStores = null;
		Object [] bookStoresObject = new Object[100000000];
		ArrayList<String> nBooks = null;
		Object [] nBooksObject = new Object[100000000];
        
		// Make book dropdown
				ArrayList<String> bookList = theDAO.getBookData();
				String[] book =  bookList.toArray(new String[bookList.size()]);
				JComboBox bookDropDown = new JComboBox(book);
				bookDropDown.setFont(new Font("Serif", Font.PLAIN, 14));

		//Initializes the price field
		JTextField price = new JTextField("$" + "              ");
		
				// Add a listener to the button so we can do something when it is clicked
		        bookDropDown.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // This will be called when the button is clicked
		                JComboBox combo = (JComboBox)e.getSource();
		                String selection = (String)combo.getSelectedItem();
//		                System.out.println("Book selected: " + selection);
		                
//		            	Print out the cost of the book in a text box
						double bookPrice = theDAO.getPrice((String)bookDropDown.getSelectedItem());
//						System.out.println((String)bookDropDown.getSelectedItem());

						price.setText("$" + Double.toString(bookPrice));
						price.setFont(new Font("Serif", Font.PLAIN, 14));
						
						//Creates an array of the bookstores that this author's book is at
						
						Vector<String> bookStores = new Vector<>();
						bookStores.addElement("Book stores: ");
						bookStores= theDAO.authorBookstore((String)bookDropDown.getSelectedItem());
						System.out.println(bookStores);


						//Creates an array of the number of books of this book available at this book store
						 Vector<String> nBooks = new Vector<>();

						nBooks.addElement("# of books: ");
						nBooks = theDAO.authorNBooks((String)bookDropDown.getSelectedItem());
						System.out.println(nBooks);
						
						//Create the table
							Vector<String> columnNames = new Vector<>();
							columnNames.addElement("1st bookstore");
							columnNames.addElement("2nd bookstore");
							columnNames.addElement("3rd bookstore");
							columnNames.addElement("4th bookstore");
							Vector<Vector> data = new Vector<>();
							data.insertElementAt(bookStores, 0);
							data.insertElementAt(nBooks, 1);
							JTable table = new JTable(data, columnNames);					
							table.revalidate();
							table.setEnabled(false);
							table.setFont(new Font("Serif", Font.PLAIN, 14));
							JScrollPane scrollPane = new JScrollPane(table);
							scrollPane.invalidate();
							scrollPane.revalidate();
							scrollPane.repaint();
							mainPanel.add(scrollPane, BorderLayout.SOUTH);
					}
		           
		        });
		
		
        // Make publisher dropdown
		        ArrayList<String> publisherList = theDAO.getPublisherData();
		        publisherList.toString();
	     		String[] publisher = publisherList.toArray(new String[publisherList.size()]);
	     		JComboBox publisherDropDown = new JComboBox(publisher);
	     		publisherDropDown.setFont(new Font("Serif", Font.PLAIN, 14));


	     	// Add a listener to the button so we can do something when it is clicked
	     		publisherDropDown.addActionListener(new ActionListener() {
	     			@Override
	     			public void actionPerformed(ActionEvent e) {
	     				// This will be called when the button is clicked
	     				JComboBox combo = (JComboBox)e.getSource();
	     				String selection = (String)combo.getSelectedItem();
//	     				System.out.println("Publisher selected: " + selection);

	     				//Creates a new array of the category's books
	     				ArrayList<String> bookList2 = theDAO.getPublisherBookData((String)publisherDropDown.getSelectedItem());
	     				String [] book2 = bookList2.toArray(new String[bookList2.size()]);

	     				bookDropDown.removeAllItems();
	     				//bookDropDown.addItem("sss");
	     				for (int i = 0; i< book2.length; i++) {
	     					bookDropDown.addItem(book2[i]);
	     				}
	     			}
	     			
	     		}); 	


//		Layout of the JPanel
        BorderLayout layout = new BorderLayout(5, 5);
        mainPanel.setLayout(layout);

// 		Add the components to the JPanel
        mainPanel.add(publisherDropDown, BorderLayout.WEST);
        mainPanel.add(bookDropDown, BorderLayout.CENTER);
        mainPanel.add(price, BorderLayout.EAST);

        // Make the frame visible and layout the components on it
        mainPanel.setVisible(true);
        return mainPanel;
    }

}
