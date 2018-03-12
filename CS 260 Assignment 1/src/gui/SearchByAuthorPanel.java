package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchByAuthorPanel extends JPanel{
	static String selection = null;
	public static JPanel author() {
		// Make a panel to hold our content and put it on the frame
		JPanel mainPanel = new JPanel();
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int panelWidth = (int) (screenDimension.width * 0.4);
		int panelHeight = (int) (screenDimension.height * 0.5);
		mainPanel.setSize(panelWidth, panelHeight);
		mainPanel.setBounds(0, 0, panelWidth, panelHeight);
		mainPanel.setBackground(Color.CYAN);
		
		// Make author dropdown
		String[] author = {"author1", "author2", "author3"};
		JComboBox authorDropDown = new JComboBox(author);
		authorDropDown.setFont(new Font("Serif", Font.PLAIN, 14));
		
		
		// Add a listener to the button so we can do something when it is clicked
		authorDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This will be called when the button is clicked
				JComboBox combo = (JComboBox)e.getSource();
				selection = (String)combo.getSelectedItem();
				System.out.println("author selected: " + selection);
			}
			
		});
		
			
		// Make bookstore dropdown
		String[] book = {"book1", "book2", "book3"};
		JComboBox bookDropDown = new JComboBox(book);
		bookDropDown.setFont(new Font("Serif", Font.PLAIN, 14));
		
		
		// Add a listener to the button so we can do something when it is clicked
		bookDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This will be called when the button is clicked
				JComboBox combo = (JComboBox)e.getSource();
				selection = (String)combo.getSelectedItem();
				System.out.println("Book selected: " + selection);
			}			
		});		
		
//		Print out the cost of the book in a text box
		double p = 12.75;
		JTextField price = new JTextField("$" + p);
		price.setFont(new Font("Serif", Font.PLAIN, 14));
		
		
//		Print out the bookstore and number of books available
		String[] columnNames = {"Bookstore", "# Books Available"};
		Object[][] data = {{"Store1", new Integer(1)},{"Store2",new Integer(2)}, {"Store3", new Integer(3)}};
		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Serif", Font.PLAIN, 14));
		
		
//		Layout of the JPanel
		BorderLayout layout = new BorderLayout(30, 30);
		mainPanel.setLayout(layout);
		
// 		Add the components to the JPanel
		mainPanel.add(authorDropDown, BorderLayout.WEST);
		mainPanel.add(bookDropDown, BorderLayout.CENTER);
		mainPanel.add(price, BorderLayout.EAST);
		mainPanel.add(table, BorderLayout.SOUTH);
		
		// Make the frame visible and layout the components on it
		// Do this last
		mainPanel.setVisible(true);
		return mainPanel;
	}

}
