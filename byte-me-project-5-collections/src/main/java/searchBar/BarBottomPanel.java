/* This class creates a tool bar within the SearchBar GUI that includes
 * "maintenance" button, "about..." button and a tally of indexed files.
 * */

package searchBar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import adminMode.AdminMode;

public class BarBottomPanel extends JPanel {
	
	//BarBottomPanel components
	private JButton maintenance, about;
	private JLabel label1;
	
	public BarBottomPanel() {
		//Creating component objects
		maintenance = new JButton("Maintenance");
		about = new JButton("About...");
		label1 = new JLabel("Number of files indexed: " /*missing reference*/);
		
		//Adding the components to the toolbar
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 15, 0, 0);
		add(maintenance, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(label1, gc);
		
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 15);
		add(about, gc);
		
		//Maintenance button opens the AdminMode
		maintenance.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new AdminMode();
			}
		});
	}
}
