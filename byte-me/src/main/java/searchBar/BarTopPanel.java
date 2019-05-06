/* This class creates a panel in the SearchBar GUI that includes
 * search bar, search button and a combo box of search options.
 * */

package searchBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarTopPanel extends JPanel {
	
	private JLabel title;
	private SearchBarPanel searchBarPanel;
	
	public BarTopPanel() {
		//Creating the size of the panel
		Dimension dim = getPreferredSize();
		dim.height = 100;
		setPreferredSize(dim);
		
		//Adding components, including a panel within BarTopPanel named SearchBarPanel
		title = new JLabel("Byte Me Search Engine", JLabel.CENTER);
		searchBarPanel = new SearchBarPanel();
		
		title.setFont(title.getFont().deriveFont(24f));		
		
		setLayout(new BorderLayout());
		
		add(title, BorderLayout.CENTER);
		add(searchBarPanel, BorderLayout.SOUTH);		
	}
}

class SearchBarPanel extends JPanel {
	private JComboBox<String> searchComboBox;
	private static String[] options = {"-- Search Options --", "All Search Terms", "Any Search Terms", "Exact Phrase"};
	private JTextField searchBar;
	private JButton searchButton;
		
	public SearchBarPanel() {
		//Creating panel size
		Dimension dim = getPreferredSize();
		dim.height = 40;
		setPreferredSize(dim);
		
		//Adding components
		searchComboBox = new JComboBox<>(options);
		searchBar = new JTextField();
		searchButton = new JButton("Search");
		
		searchBar.setPreferredSize(new Dimension(500, 23));
		
		//Determining the location of the components
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		 
		gc.weightx = 1;
		gc.weighty = 1;
			
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0, 80, 0, 0);
		gc.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
		add(searchComboBox, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.ABOVE_BASELINE;
		gc.insets = new Insets(0, 0, 0, 0);
		add(searchBar, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gc.insets = new Insets(0, 0, 0, 80);
		add(searchButton, gc);
	}
}
