import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/*
SQL STATEMENTS

-- 1. Populates the first drop-down menu --
select author_last
from henry_author;

-- 2. Populates the second drop-down menu, Replace Francis with the name of the author from the first drop-down --
select title
from henry_book
where book_code in(
select book_code
  from henry_wrote
  where author_num =
    (select author_num
     from henry_author
     where author_last = 'Francis'));


--3. Replace second wind with the title of a book --
select price
from henry_book
where title = 'Second Wind';

*/


public class SearchByAuthorPanel extends JPanel{
	@SuppressWarnings("unchecked")
	public static JPanel author() {
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

				//	Print out the cost of the book in a text box
				double bookPrice = theDAO.getPrice((String)bookDropDown.getSelectedItem());
				System.out.println((String)bookDropDown.getSelectedItem());

				price.setText("$" + Double.toString(bookPrice));
				price.setFont(new Font("Serif", Font.PLAIN, 14));
			}
		});

		// Make author dropdown
		ArrayList<String> authorList = theDAO.getAuthorData();
		authorList.toString();
		String[] author = authorList.toArray(new String[authorList.size()]);
		JComboBox authorDropDown = new JComboBox(author);
		authorDropDown.setFont(new Font("Serif", Font.PLAIN, 14));

		// Add a listener to the button so we can do something when it is clicked
		authorDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This will be called when the button is clicked
				JComboBox combo = (JComboBox)e.getSource();
				String selection = (String)combo.getSelectedItem();
				System.out.println("author selected: " + selection);

				//Creates a new array of the authors books
				ArrayList<String> bookList2 = theDAO.getAuthorsBookData((String)authorDropDown.getSelectedItem());
				String [] book2 = bookList2.toArray(new String[bookList2.size()]);

				bookDropDown.removeAllItems();
				//bookDropDown.addItem("sss");
				for (int i = 0; i< book2.length; i++) {
					bookDropDown.addItem(book2[i]);
				}
			}
			
		});

//		Print out the bookstore and number of books available
		String[] columnNames = {"Bookstore", "# of Books Available"};
		//Creates an array of the bookstores that this author's book is at
		bookStores = theDAO.authorBookstore((String)authorDropDown.getSelectedItem());
		
		 for(int i = 0; i < bookStores.size(); i++) {
	            bookStoresObject[i] = bookStores.get(i);
	      }
		 
		//Creates an array of the number of books of this book available at this book store
			nBooks = theDAO.authorNBooks((String)authorDropDown.getSelectedItem());
			
		 for(int i = 0; i < nBooks.size(); i++) {
	           	nBooksObject[i] = nBooks.get(i);
	      }
		Object[][] data = {bookStoresObject, nBooksObject};
		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		
//		Layout of the JPanel
		BorderLayout layout = new BorderLayout(5, 5);
		mainPanel.setLayout(layout);
		
// 		Add the components to the JPanel
		mainPanel.add(authorDropDown, BorderLayout.WEST);
		mainPanel.add(bookDropDown, BorderLayout.CENTER);
		mainPanel.add(price, BorderLayout.EAST);
		mainPanel.add(table, BorderLayout.SOUTH);
		
		// Make the frame visible and layout the components on it
		mainPanel.setVisible(true);
		return mainPanel;
	}

}
