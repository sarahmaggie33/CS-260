package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class Henry {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//		Toolkit.getDefaultToolkit().getScreenResolution(); 
		int jFrameWidth = (int) (screenDimension.width * 0.4);
		int jFrameHeight = (int) (screenDimension.height * 0.5);
		f.setBounds(0, 0, jFrameWidth, jFrameHeight);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
        
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setSize(jFrameWidth, jFrameHeight);
		tabbedPane.setBounds(0, 0, jFrameWidth, jFrameHeight);
		
//		Tab 1
		JPanel author = new JPanel();
		author.add(SearchByAuthorPanel.author());
		tabbedPane.addTab("Search by Author", author);
//		tabbedPane.setSelectedIndex(0);
		
//		Tab 2
		JPanel category = new JPanel();
		JPanel categoryContent = new JPanel(new BorderLayout());
		category.add(categoryContent);
		tabbedPane.addTab("Search by Category", category);

//		tabbedPane.setOpaque(true);
		tabbedPane.setFont(new Font("Serif", Font.BOLD, 50));
		f.add(tabbedPane);
//		f.pack();
		f.setVisible(true);
	}
}
