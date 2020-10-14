package prediction;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * @author Devin Murphy
 * 
 * File Controller receives and processes an input csv containing sports 
 * team data.
 *
 */
public class FileController {
	private HashMap<String, Team> allTeams;
	
	/**
	 * default constructor for FileController object
	 */
	public FileController() {this.allTeams = new HashMap<>();}
	
	/**
	 * Constructs a FileController object.
	 * 
	 * pre: csv must have this format: 2018-11-06,@Buffalo,82,St Francis PA,67
	 * data fields are date(year, month, day), team1, team1Score, team2, team2Score.
	 * Also note team with higher score preceded with an @ symbol
	 * 
	 * @param inputFile the input CSV file to be processed
	 */
	public FileController(File inputFile) throws FileNotFoundException{
		allTeams = new HashMap<>();
		Scanner input;
		try {
			input = new Scanner(inputFile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//process csv data into allTeams hashmap
		while(input.hasNextLine()) {
			String inputRow = input.nextLine();
			
		}
		
		input.close();
	}
	
	/**
	 * allTeams getter
	 * 
	 * @return the allTeams for this FileController
	 */
	public HashMap<String, Team> getallTeams() {return this.allTeams;}
	
	/**
	 * allTeams setter
	 * 
	 * @param inputMap the new allTeams for this FileController
	 */
	public void setallTeams(HashMap<String, Team> inputMap) {this.allTeams = inputHashMap;}
}

//////////////////////////////HELPER METHODS//////////////////////////////
