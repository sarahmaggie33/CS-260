package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchByAuthorPanel extends JPanel{
	@SuppressWarnings("unchecked")
	public static JPanel author() {
		
		// Make a panel to hold our content and put it on the frame
		JPanel mainPanel = new JPanel();
//		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//		int panelWidth = (int) (screenDimension.width * 0.4);
//		int panelHeight = (int) (screenDimension.height * 0.5);
//		mainPanel.setSize(panelWidth, panelHeight);
		mainPanel.setBackground(Color.CYAN);
		
		// Make author dropdown
		String[] author = {"author1", "author2", "author3"};
		JComboBox authorDropDown = new JComboBox(author);
		authorDropDown.setFont(new Font("Serif", Font.PLAIN, 50));
		
		
		// Add a listener to the button so we can do something when it is clicked
		authorDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This will be called when the button is clicked
				JComboBox combo = (JComboBox)e.getSource();
				String selection = (String)combo.getSelectedItem();
				System.out.println("author selected: " + selection);	
			}
			
		});
		
			
		// Make bookstore dropdown
		String[] book = {"book1", "book2", "book3"};
		JComboBox bookDropDown = new JComboBox(book);
		bookDropDown.setFont(new Font("Serif", Font.PLAIN, 50));
		
		
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
		double p = 12.75;
		JTextField price = new JTextField("$" + p);
		price.setFont(new Font("Serif", Font.PLAIN, 50));
		
		
//		Print out the bookstore and number of books available
		String[] columnNames = {"Bookstore", "# Books Available"};
		Object[][] data = {{"Store1", new Integer(1)},{"Store2",new Integer(2)}, {"Store3", new Integer(3)}};
		JTable table = new JTable(data, columnNames);
		table.setFont(new Font("Serif", Font.PLAIN, 50));
		
		
//		Layout of the JPanel
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.setLayout(layout);

		
// 		Add the components to the JPanel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(authorDropDown, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(bookDropDown, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		mainPanel.add(price, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;     
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(table, c);
		
		// Make the frame visible and layout the components on it
		// Do this last
		mainPanel.setVisible(true);
		return mainPanel;
	}

	
}
