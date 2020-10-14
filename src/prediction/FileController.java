package prediction;

/**
 * @author Devin Murphy
 * 
 * File Controller receives and processes an input csv containing sports 
 * team data.
 *
 */
public class FileController {
	private HashMap<String, Team> teamHashMap;
	
	/**
	 * default constructor for FileController object
	 */
	public FileController() {this.teamHashMap = new HashMap<>();}
	
	/**
	 * Constructs a FileController object.
	 * 
	 * pre: csv must have this format: 2018-11-06,@Buffalo,82,St Francis PA,67
	 * data fields are date(year, month, day), team1, team1Score, team2, team2Score.
	 * Also note team with higher score preceded with an @ symbol
	 * 
	 * @param inputFile the input CSV file to be processed
	 */
	public FileController(File inputFile) {
		teamList = new HashMap<>();
	}
	
	/**
	 * teamHashMap getter
	 * 
	 * @return the teamHashMap for this FileController
	 */
	public HashMap<String, Team> getTeamHashMap() {return this.teamHashMap;}
	
	/**
	 * teamHashMap setter
	 * 
	 * @param inputMap the new teamHashMap for this FileController
	 */
	public void setTeamHashMap(HashMap<String, Team> inputMap) {this.teamHashMap = inputHashMap;}
}
