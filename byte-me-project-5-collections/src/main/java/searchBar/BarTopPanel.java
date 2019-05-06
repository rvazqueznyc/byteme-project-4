/* This class creates a panel in the SearchBar GUI that includes
 * search bar, search button and a combo box of search options.
 * Jacob Babb, Lazaro Yero, Raul Vazquez
 * */

package searchBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	BufferedReader reader;
	ArrayList<String> path;
	String option;
	File file;
		
	public SearchBarPanel() {
		option="-- Search Options --";
		files();
		
		//Creating panel size
		Dimension dim = getPreferredSize();
		dim.height = 40;
		setPreferredSize(dim);
		
		//Adding components
		searchComboBox = new JComboBox<>(options);
		searchBar = new JTextField();
		searchButton = new JButton("Search");
		
		//All Search Terms", "Any Search Terms", "Exact Phrase"
		
		searchButton.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) 
			{  files();
				
				if(option.equals("-- Search Options --"))
				{
					JOptionPane.showMessageDialog(null, "Select Any Option First!");
				}
				else
				{
					if(searchBar.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Enter Keywords First!");
					}
					
					//***************************************************************OR*******************************************************//
									else if(option.equals("Any Search Terms"))
					{
						int filefound=0;
						SearchBar.barCenterPanel.setText(null);
					
						Scanner input = null;
				
						
						ArrayList<String> search = new ArrayList<String>();
						ArrayList<String> words = new ArrayList<String>();
						input = new Scanner(searchBar.getText());
						input.useDelimiter(" +");
						while(input.hasNext())
						{
							search.add(input.next());	
						}
					
						int a;
						
						for(int i=0;i<path.size();i++)
						{ 
							a=0;
							//get first file
							words.clear();
							file = new File(path.get(i));
							
							//get all words of file
							try {
								input = new Scanner(file);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							input.useDelimiter(" +");
							while(input.hasNext())
							{
								words.add(input.next());	
							}

							//match search terms with all words of file
							for(int k=0;k<search.size();k++)
							{
								for(int j=0;j<words.size();j++)
								{
									if(search.get(k).equals(words.get(j)))
									{
									//System.out.println(search.get(k)+" "+path.get(i));
									//SearchBar.barCenterPanel.setText(search.get(k)+" "+path.get(i)+"\n");
									
									SearchBar.barCenterPanel.append(path.get(i)+"\n");
									filefound=1;
									a=1;
									break;
									}
								}
								if(a==1)
									break;
							}
					}
						
						if(filefound==0)
						{
							JOptionPane.showMessageDialog(null, "No Results Found!");
						}
				}
					
					//**************************************************************AND*********************************************************//
					
					else if(option.equals("All Search Terms"))
					{
						int filefound=0;
						SearchBar.barCenterPanel.setText(null);
					
						Scanner input = null;
				
						int a;
						ArrayList<String> search = new ArrayList<String>();
						ArrayList<String> words = new ArrayList<String>();
						input = new Scanner(searchBar.getText());
						input.useDelimiter(" +");
						while(input.hasNext())
						{
							search.add(input.next());	
						}
					
						
						for(int i=0;i<path.size();i++)
						{
							a=0;
							//get first file
							words.clear();
							file = new File(path.get(i));
							
							//get all words of file
							try {
								input = new Scanner(file);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							input.useDelimiter(" +");
							while(input.hasNext())
							{
								words.add(input.next());	
							}
							
							
							//match search terms with all words of file
							for(int k=0;k<search.size();k++)
							{
								for(int j=0;j<words.size();j++)
								{
									if(search.get(k).equals(words.get(j)))
									{
										//System.out.println(search.get(k)+" "+path.get(i));
									//SearchBar.barCenterPanel.setText(search.get(k)+" "+path.get(i)+"\n");
									a++;
									
									}
								}
								
								if(a==search.size())
								{
									filefound=1;
									SearchBar.barCenterPanel.append(path.get(i)+"\n");
								}
							}
					}
						
						if(filefound==0)
						{
							JOptionPane.showMessageDialog(null, "No Results Found!");
						}
				}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					//***************************************************************Exact Phrase*******************************************************//
					else if(option.equals("Exact Phrase"))
					{
						int a=0,l=0,filefound=0;;
						SearchBar.barCenterPanel.setText(null);
					
						Scanner input = null;

						ArrayList<String> search = new ArrayList<String>();
						ArrayList<String> words = new ArrayList<String>();
						input = new Scanner(searchBar.getText());
						input.useDelimiter(" +");
						while(input.hasNext())
						{
							search.add(input.next());	
						}

						for(int i=0;i<path.size();i++)
						{
							//get first file
							words.clear();
							file = new File(path.get(i));
							
							//get all words of file
							try {
								input = new Scanner(file);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							input.useDelimiter(" +");
							
							while(input.hasNext())
							{
								words.add(input.next());	
							}
							
							//match search terms with all words of file
							for(int k=0;k<search.size();k++)
							{
								for(int j=a;j<words.size();j++)
								{
									if(search.get(k).equals(words.get(j)))
									{
										
								l++;
										a=j;
										break;
									}
							}
								
								if(l==search.size())
								{
									filefound=1;
									SearchBar.barCenterPanel.append(path.get(i)+"\n");
									}
								}
							l=0;
					}
						if(filefound==0)
						{
							JOptionPane.showMessageDialog(null, "No Results Found!");
						}
					}
			}}});
		
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
	
	public void files()
	{
		path = new ArrayList<String>();
		try {
			/*FA here we are reading file "output.txt" in which we have stored our data about file
			 * we have passed this file to BufferedReader via FileReader to read it line by line*/
			
			reader = new BufferedReader(new FileReader(
					"output.txt"));
			
			//FA  here we have first line of file which is a file path we have stored
			String line = reader.readLine();
		
			//FA this loop will continue until it finds an empty line
			while (line != null) {
				//FA we have put path of file in this FIle variable 
				File file = new File(line);
				
				//FA file.exists() checks that if this file is valid or not
				if (file.exists()){
					//FA if it is valid we put that path and add Indexed in front of it
					path.add(line);
				}
			
				
				//FA it reads next line of file 
				line = reader.readLine();
			 }
			
			//FA after reading that file we close it
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

