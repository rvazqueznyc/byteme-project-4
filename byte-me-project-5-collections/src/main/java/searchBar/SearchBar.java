/* This class uses swing to create a GUI for SearchBar
 * window of the ByteMe search engine.
 * */

package searchBar;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class SearchBar extends JFrame {
	
	//Components used in the SearchBar
	private BarTopPanel barTopPanel;
	private BarCenterPanel barCenterPanel;
	private BarBottomPanel barBottomPanel;
	
	public SearchBar() {
		super("Search Engine");
		
		setLayout(new BorderLayout());
		
		//Creating component objects
		barTopPanel = new BarTopPanel();
		barCenterPanel = new BarCenterPanel();
		barBottomPanel = new BarBottomPanel();
		
		//Positioning components
		add(barTopPanel, BorderLayout.NORTH);
		add(barCenterPanel, BorderLayout.CENTER);
		add(barBottomPanel, BorderLayout.SOUTH);
		
		//SearchBar specifications
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
