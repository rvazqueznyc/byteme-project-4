/* This class creates a table with two columns that displays the index in AdminMode GUI.
 * Index includes file location and status.
 * */

package adminMode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class AdminCenterPanel extends JPanel {
	
	public static JTable index;
	
	//  every table has a model which deals with all its rows we just set row to model and model set it to the table this is the declaration of model
	public static DefaultTableModel model;
	
	public AdminCenterPanel() {
		
		//Creating the JTable object and preventing editing
		index = new JTable();
		
		
		String columnNames[] = {"File Name","Status"};
		//the model is initialized
		model = new DefaultTableModel();
		
		//we are seting columns to this model
		model.setColumnIdentifiers(columnNames);
		
		// now here we have set this model to table
		index.setModel(model);

		
		JTableHeader tHeader = index.getTableHeader();
		
		tHeader.setFont(new Font("", Font.BOLD, 14));
		
		//Setting table size
		index.setPreferredScrollableViewportSize(new Dimension(775, 325));
		index.setFillsViewportHeight(true);
		
		//Adding a scroll bar to the table
		JScrollPane indexScroll = new JScrollPane(index);
		add(indexScroll);
		BufferedReader reader;
		
		// delete all data if it has because every time we when we run application we enter all files after verifying   
		model.getDataVector().removeAllElements();
		
		// this method ensures that if any row is deleted it refreshes the table
		model.fireTableDataChanged();
		
		
		/* here we have applied a try and catch block to populate the table very the first time when application starts
		 * we have used a try and catch block to ensure if the file we are using to store addresses of other files is missing or corrupt it will
		 * will execute  catch block and show the error
		*/
		try {
			/* here we are reading file "output.txt" in which we have stored our data about our files
			 * we have passed this file to BufferedReader via FileReader to read it line by line*/
			
			reader = new BufferedReader(new FileReader(
					"output.txt"));
			
			//  here we have first line of file which is a file path we have stored
			String line = reader.readLine();
			
		
				// this loop will continue until it finds an empty line
			while (line != null) {
				
				
				
				// we have put path of file in this FIle variable 
				File file = new File(line);
				
				// file.exists() checks that if this file is valid or not
				if (file.exists()){
					// if it is valid we put that path and add Indexed in front of it
					model.addRow(new Object[]{line,"Indexed"});
				}
				else
				{
					// if it is not valid we put that path and add File not found! in front of it
					model.addRow(new Object[]{line,"File Not Found!"});
				}
				
				// it reads next line of file 
				line = reader.readLine();
				
			
			
			
			
			 }
			
			// after reading that file we close it
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}
