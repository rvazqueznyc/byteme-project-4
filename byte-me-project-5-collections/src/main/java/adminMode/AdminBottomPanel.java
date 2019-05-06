/* This class creates a panel in AdminMode GUI that includes buttons to
 * add, rebuild, remove and reset. It also includes some labels.
 * Team Members, Lazaro Yero, Raul Vazquez, Jacob Babb
 * */

package adminMode;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;





public class AdminBottomPanel extends JPanel {
	
	private JButton addButton, rebuildButton, removeButton, resetButton;
	private JLabel filesIndexed, versionNumber;
	private JFileChooser filechoose; 
	String newline;
	public AdminBottomPanel() {
		//Setting panel size
		Dimension dim = getPreferredSize();
		dim.height = 75;
		setPreferredSize(dim);
		filechoose = new JFileChooser(); 
		//Creating component objects
		addButton = new JButton("Add File...");
		rebuildButton = new JButton("Rebuild Out-of-date");
		removeButton = new JButton("Remove Selected Files");
		resetButton = new JButton("Reset Windows");
		filesIndexed = new JLabel("Number of files indexed: " /*needs reference*/);
		versionNumber = new JLabel("Search Engine version: " /*needs reference*/);
		
		
		resetButton.addActionListener(new ActionListener() 
		
		{
		public void actionPerformed(ActionEvent arg0) 
		{
			
		
			   FileWriter fw;
				try {
					
					fw = new FileWriter("output.txt");
					
					
			  
					 
					AdminCenterPanel.model.getDataVector().removeAllElements();
					
					// this method ensures that if any row is deleted it refreshes the table
					AdminCenterPanel.model.fireTableDataChanged();
			        // close the file  
			        fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		});
		
		
		// rebuild Button action listener
		rebuildButton.addActionListener(new ActionListener() 
		
		{
		public void actionPerformed(ActionEvent arg0) 
		{
			
			BufferedReader reader;
			

			// delete all data if it has because we re enter all files after verifying   
		
			AdminCenterPanel.model.getDataVector().removeAllElements();
			
			// this method ensures that if any row is deleted it refreshes the table
			AdminCenterPanel.model.fireTableDataChanged();
		
			/* here we have applied a try and catch block to populate the table very first time when application starts
			 * we have used try and catch block to ensure if the file we are using to store addresses of other files is missing or corrupt it will
			 * will execute  catch block and show the error
			*/
			try {
				/* here we are reading file "output.txt" in which we have stored our data about file
				 * we have passed this file to BufferedReader via FileReader to read it line by line*/
				reader = new BufferedReader(new FileReader(
						"output.txt"));
				//  here we have first line of file which is a file path we have stored
				String line = reader.readLine();
				
				
				//Fthis loop will continue until it finds an empty line
				while (line != null) {
					
					// we have put the path of file in this FIle variable 
					File file = new File(line);
					
					// file.exists() checks that if this file is valid or not
					if (file.exists()){
						// if it is valid we put that path and add Indexed in front of it
						AdminCenterPanel.model.addRow(new Object[]{line,"Indexed"});
					}
				
				else
				{
					// if it is not valid we put that path and add File not found! in front of it
						AdminCenterPanel.model.addRow(new Object[]{line,"File Not Found!"});
					}
					
					line = reader.readLine();
					
				
				}
				
				
				
			// after reading that file we close it
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		
		}});
		
		
		//remove button action Listener
        removeButton.addActionListener(new ActionListener() 
		
		{
		public void actionPerformed(ActionEvent arg0) 
		{
			//Findex.getSelectedRow() gives the selected row number of table and if it is -1 means no row is selected 
			if(AdminCenterPanel.index.getSelectedRow()==-1)
			{// show pop up to show a message about select a row
				JOptionPane.showMessageDialog(null, "Select a row");
			}
			else
			{
				// if a row is selected then this method will execute
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(
							"output.txt"));
					String line = reader.readLine();
					 newline = null;
					
					 int i=0;
					
					 // Here we read all lines of output.txt file and match selected row file path will each line of output.txt file
					 while (line != null) {
						
						 // we pick up all lines in a String variable newLine that are not equal to the file path we choose to remove
						 // in this way we get all paths except the path of  of file path we choose to remove in in String variable newLine
						if(!line.equals(AdminCenterPanel.index.getValueAt(AdminCenterPanel.index.getSelectedRow(), 0)))
						{
							/*For the first time we have to initialize newline variable so we use a variable i which is equal to 0 at start but
							after first line it becomes 1 and we append all lines we pick from output.txt file to the newLine variable
							*/
							if(i==0)
							{
								newline=line;
								i=1;
							}
							else
							{
						newline=newline+"\n"+line;
						
							}
							}
					
						// read next line
						line = reader.readLine();
					
					}
			
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
		
			// here we use the FileWriter and passed the file output.txt 
			
			   FileWriter fw;
			try {
				fw = new FileWriter("output.txt");
				   // read character wise from string and write  
		        // into FileWriter  
		    // we use FileWriter because we want to overwrite the output.txt file with out newLine variable
				fw.write(newline+"\n");
		  
		 
		        // close the file  
		        fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Remove Row From table GUI
			AdminCenterPanel.model.removeRow(AdminCenterPanel.index.getSelectedRow());
			}
		}});
		
        
        
        
		// add button action listener 
		addButton.addActionListener(new ActionListener() 
		
		{
		public void actionPerformed(ActionEvent arg0) 
		{
			
		// here is frame in which select file window opens
		Component frame = null;
		
		// it opens the select window 
		if(filechoose.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION);
		
		// it pick the file path we choose
		File f=	filechoose.getSelectedFile();

		
		
		// Here we check that the file we have picked up is already stored or not
		
		
		int test=0;
		
		// reading all lines of output.txt files and match them with the file we have selected 
           BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(
					"output.txt"));
			String line = reader.readLine();
			
			
			
			while (line != null) {
				// if file is there set test variable value to 1
				if(line.equals(f.getAbsolutePath()))
				{
				
					test=1;
				}
				
				
				// read next line
				line = reader.readLine();
			
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		// if test is equal to 1 means file is already in table we don't need to rewrite it
		
		if(test==1)
		{// Show pop up window that already inserted
			JOptionPane.showMessageDialog(frame, "Already Inserted");
		}
		else
		{
			// else just write the file path to the output.txt
		
			
			// here is another try and catch block for the output.txt file
// try to open this file
			try(FileWriter fw = new FileWriter("output.txt", true);
			   // Pass this file writer to butter writer
				BufferedWriter bw = new BufferedWriter(fw);
			    // then pass this buffer writer to PrintWriter so we can write in the file line by line
					PrintWriter out = new PrintWriter(bw))
			{
				// this will write in the file the path of our selected file
			    out.println(f.getAbsolutePath());
			   
			    // Here we are adding this file path as a row to the model of table so it show up in table 
			    AdminCenterPanel.model.addRow(new Object[]{f.getAbsolutePath(),"Indexed"});
			   
			  
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
				// this will show a pop up about file not found
				JOptionPane.showMessageDialog(frame, "File Not Found");
			}
		  }
		}});
		
		
		
		//Setting the grid layout and positioning components
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////////////////////FIRST ROW////////////////////
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		add(addButton, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(rebuildButton, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		add(removeButton, gc);
		
		///////////////////SECOND ROW///////////////////
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(resetButton, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(filesIndexed, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		add(versionNumber, gc);
	}
}
