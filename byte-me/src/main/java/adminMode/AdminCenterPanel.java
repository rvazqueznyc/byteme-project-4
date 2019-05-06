/* This class creates a table with two columns that displays the index in AdminMode GUI.
 * Index includes file location and status.
 Team Members: Lazaro Yero, Raul Vazquez, Jacob Babb
 * */

package adminMode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class AdminCenterPanel extends JPanel {
	
	protected static String fileChosen;
	private JTable index;
	public static DefaultTableModel model;
	
	public AdminCenterPanel() {
		
	/*	String[] columns = {"File Name", "Status"};
		
		String[][] data = 
			{    {"C:/", "Test"},
				{"C:/2", "Test2"}};*/
		
				/*needs reference*/
		
		//Creating the JTable object and preventing editing
		index = new JTable();
		
		String columnNames[] = {"File Name","Status"};
		 model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		index.setModel(model);
		
		/*{
			public boolean isCellEditable(int data, int columns) {
				return false;
			}
			
			public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
				Component c = super.prepareRenderer(r, data, columns);
				
				if(data % 2 == 0)
					c.setBackground(Color.WHITE);
				
				else
					c.setBackground(Color.LIGHT_GRAY);
				
				if(isCellSelected(data, columns))
					c.setBackground(Color.ORANGE);
				
				return c;
			}
		};*/
		
		JTableHeader tHeader = index.getTableHeader();
		
		tHeader.setFont(new Font("", Font.BOLD, 14));
		
		//Setting table size
		index.setPreferredScrollableViewportSize(new Dimension(775, 325));
		index.setFillsViewportHeight(true);
		
		//Adding a scroll bar to the table
		JScrollPane indexScroll = new JScrollPane(index);
		add(indexScroll);
		int row=0;
		BufferedReader reader;
		
		AdminCenterPanel.model.getDataVector().removeAllElements();
		AdminCenterPanel.model.fireTableDataChanged();
		
		try {
			reader = new BufferedReader(new FileReader(
					"output.txt"));
			String line = reader.readLine();
			
			
			
			while (line != null) {
				
				File file = new File(line);
				
				if (file.exists()){
					AdminCenterPanel.model.addRow(new Object[]{line,"Indexed"});
				}
				else
				{
					AdminCenterPanel.model.addRow(new Object[]{line,"File Not Found!"});
				}
				
				line = reader.readLine();
				
			
			
			
			
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	/*	int row=0;
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(
					"output.txt"));
			String line = reader.readLine();
			model.addRow(new Object[]{line,"Indexed"});
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
				model.addRow(new Object[]{line,"Indexed"});
			row++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.removeRow(row);
		
		*/
	}
}
