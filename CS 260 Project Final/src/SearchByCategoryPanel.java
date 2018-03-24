import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
/*
--                Category Tab              --
--box 1--
select unique type
from henry_book;

-- box2 , replace mys with the selection from box 1--
select title
from henry_book
where book_code in
( select book_code
  from henry_wrote
  where type = 'MYS');

-- box 3, replace 'Second wind with title from box 2'--
select price
from henry_book
where title = 'Second Wind';
 */

public class SearchByCategoryPanel extends JPanel
{
    @SuppressWarnings("unchecked")
    public static JPanel category() {

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
                System.out.println("Book selected: " + selection);
                
//            	Print out the cost of the book in a text box
				double bookPrice = theDAO.getPrice((String)bookDropDown.getSelectedItem());
				System.out.println((String)bookDropDown.getSelectedItem());

				price.setText("$" + Double.toString(bookPrice));
				price.setFont(new Font("Serif", Font.PLAIN, 14));
			}
           
        });
        
		
       // Make category dropdown
     		ArrayList<String> categoryList = theDAO.getCategoryData();
     		categoryList.toString();
     		String[] category = categoryList.toArray(new String[categoryList.size()]);
     		JComboBox categoryDropDown = new JComboBox(category);
     		categoryDropDown.setFont(new Font("Serif", Font.PLAIN, 14));

     	// Add a listener to the button so we can do something when it is clicked
     		categoryDropDown.addActionListener(new ActionListener() {
     			@Override
     			public void actionPerformed(ActionEvent e) {
     				// This will be called when the button is clicked
     				JComboBox combo = (JComboBox)e.getSource();
     				String selection = (String)combo.getSelectedItem();
     				System.out.println("Category selected: " + selection);

     				//Creates a new array of the authors books
     				ArrayList<String> bookList2 = theDAO.getCategoryBookData((String)categoryDropDown.getSelectedItem());
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
        mainPanel.add(categoryDropDown, BorderLayout.WEST);
        mainPanel.add(bookDropDown, BorderLayout.CENTER);
        mainPanel.add(price, BorderLayout.EAST);
//        mainPanel.add(table, BorderLayout.SOUTH);

        // Make the frame visible and layout the components on it
        mainPanel.setVisible(true);
        return mainPanel;
    }


}
