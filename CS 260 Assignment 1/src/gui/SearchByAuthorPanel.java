package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchByAuthorPanel extends JPanel{
	@SuppressWarnings("unchecked")
	public static JPanel author() {
		
//		// Create the frame
//		JFrame f = new JFrame();
//		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
////		Toolkit.getDefaultToolkit().getScreenResolution() 
//		int jFrameWidth = (int) (screenDimension.width * 0.4);
//		int jFrameHeight = (int) (screenDimension.height * 0.5);
//		f.setSize(jFrameWidth, jFrameHeight);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Make the x button kill Java
		
		// Make a panel to hold our content and put it on the frame
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.CYAN);
//		f.setContentPane(mainPanel);
		
		// Make author dropdown
		String[] author = {"author1", "author2", "author3"};
		JComboBox authorDropDown = new JComboBox(author);
		authorDropDown.setFont(new Font("Serif", Font.PLAIN, 50));
		mainPanel.add(authorDropDown);
		
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
		String[] bookstore = {"store1", "store2", "store3"};
		JComboBox storeDropDown = new JComboBox(bookstore);
		storeDropDown.setFont(new Font("Serif", Font.PLAIN, 50));
		mainPanel.add(storeDropDown);
		
		// Add a listener to the button so we can do something when it is clicked
		storeDropDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This will be called when the button is clicked
				JComboBox combo = (JComboBox)e.getSource();
				String selection = (String)combo.getSelectedItem();
				System.out.println("Store selected: " + selection);
			}			
		});		
		
		double p = 12.75;
		JTextField price = new JTextField("$" + p);
		price.setFont(new Font("Serif", Font.PLAIN, 50));
		mainPanel.add(price);
		
		
		// Make the frame visible and layout the components on it
		// Do this last
		mainPanel.setVisible(true);
		return mainPanel;
	}

//	public static void main(String[] args) {
//		new SearchByAuthorPanel();
//	}
	
}
