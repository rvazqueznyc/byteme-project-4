/* Runs a search engine that users can use to find local files.
 * Search engine has a maintenance mode that allows users to add files
 * to search engine index.
 * */

package byteMe;

import javax.swing.SwingUtilities;

import searchBar.SearchBar;

public class App {
	public static void main(String[] args) {
		
		//Using runnable for the SearchBar of the search engine GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SearchBar();
			}
		});
	}
}
