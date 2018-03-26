import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

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


public class SearchByAuthorPanel extends JPanel {
	@SuppressWarnings("unchecked")
	public static JPanel author() {
		JPanel mainPanel = new JPanel();
		HenryDAO theDAO = new HenryDAO();

//		Object [] bookStoresObject = new Object[100000000];
//		Object [] nBooksObject = new Object[100000000];
		
		
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
//				System.out.println("Book selected: " + selection);

				//	Print out the cost of the book in a text box
				double bookPrice = theDAO.getPrice((String)bookDropDown.getSelectedItem());
//				System.out.println((String)bookDropDown.getSelectedItem());

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
//				System.out.println("author selected: " + selection);

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


//		Layout of the JPanel
		BorderLayout layout = new BorderLayout(5, 5);
		mainPanel.setLayout(layout);
		
// 		Add the components to the JPanel
		mainPanel.add(authorDropDown, BorderLayout.WEST);
		mainPanel.add(bookDropDown, BorderLayout.CENTER);
		mainPanel.add(price, BorderLayout.EAST);
		
		
		// Make the frame visible and layout the components on it
		mainPanel.setVisible(true);
		return mainPanel;
	}

}
