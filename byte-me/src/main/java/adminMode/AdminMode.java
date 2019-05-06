/* This class creates a swing AdminMode GUI for the maintenance/administrator
 * window of the ByteMe search engine.
 * */

package adminMode;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class AdminMode extends JFrame {
	
	//Components used in AdminMode
	private AdminTopPanel adminTopPanel;
	private AdminCenterPanel adminCenterPanel;
	private AdminBottomPanel adminBottomPanel;

	public AdminMode() {
		super("Search Engine Maintenance");
		
		setLayout(new BorderLayout());
		
		//Creating the component objects
		adminTopPanel = new AdminTopPanel();
		adminCenterPanel = new AdminCenterPanel();
		adminBottomPanel = new AdminBottomPanel();
		
		//Positioning the components
		add(adminTopPanel, BorderLayout.NORTH);
		add(adminCenterPanel, BorderLayout.CENTER);
		add(adminBottomPanel, BorderLayout.SOUTH);
		
		//AdminMode specifications
		setSize(800, 450);
		setVisible(true);
	}
}
