import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Henry {
	public static void main(String[] args) {

		JFrame f = new JFrame();
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

		int xDim = (int)(screenDimension.getWidth()*.3);
		int yDim = (int)(screenDimension.getHeight()*.5);

		int jFrameWidth = (int) (xDim);
		int jFrameHeight = (int) (yDim);
		f.setBounds(0, 0, jFrameWidth, jFrameHeight);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
        
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setSize(100, 100);
		tabbedPane.setBounds(0, 0, jFrameWidth, jFrameHeight);
		
		//Tab 1
		JPanel author = new JPanel();
		author.add(SearchByAuthorPanel.author());
		tabbedPane.addTab("Search by Author", author);

		
		//Tab 2
		JPanel category = new JPanel();
		category.add(SearchByCategoryPanel.category());
		tabbedPane.addTab("Search by Category", category);

		//Tab3
		JPanel publisher = new JPanel();
		publisher.add(SearchByPublisherPanel.publisher());
		tabbedPane.addTab("Search by Publisher", publisher);

		//Sets the size of tabbed pane and adds it to the frame
		tabbedPane.setFont(new Font("Serif", Font.BOLD, 16));
		f.add(tabbedPane);

		f.setVisible(true);


	}
}
