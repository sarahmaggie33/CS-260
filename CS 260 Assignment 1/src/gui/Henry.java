package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Henry {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
//		Toolkit.getDefaultToolkit().getScreenResolution(); 
		int jFrameWidth = (int) (screenDimension.width * 0.4);
		int jFrameHeight = (int) (screenDimension.height * 0.5);
		f.setSize(jFrameWidth, jFrameHeight);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel author = new JPanel();
		author.add(SearchByAuthorPanel.author());
		tabbedPane.addTab("Search by Author", author);
		tabbedPane.setSelectedIndex(0);
//		tabbedPane.addTab("Search by ");

		
		tabbedPane.setFont(new Font("Serif", Font.BOLD, 50));
		f.add(tabbedPane);
		
		f.setVisible(true);
	}
}
