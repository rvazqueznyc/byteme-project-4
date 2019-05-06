/* This class creates the top panel of the AdminMode GUI.
 * This panel includes the title of the window.
 * */

package adminMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminTopPanel extends JPanel {
	
	private JLabel title;
	
	public AdminTopPanel() {
		//Setting panel size
		Dimension dim = getPreferredSize();
		dim.height = 50;
		setPreferredSize(dim);
		
		//Creating the component objects
		title = new JLabel("Search Engine - Index Maintenance");
		
		title.setFont(title.getFont().deriveFont(24f));

		//Positioning components
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//////////////////FIRST ROW//////////////////
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		add(title, gc);
	}
}
