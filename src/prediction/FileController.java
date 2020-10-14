package prediction;

/**
 * @author Devin Murphy
 * 
 * File Controller receives and processes an input csv containing sports 
 * team data.
 *
 */
public class FileController {
	private ArrayList<Team> teamList;
	
	/**
	 * Constructs a FileController object.
	 * 
	 * pre: csv must have this format: 2018-11-06,@Buffalo,82,St Francis PA,67
	 * data fields are date(year, month, day), team1, team1Score, team2, team2Score.
	 * Also note team with higher score preceded with an @ symbol
	 * 
	 * @param inputFileLocation the location of the input file in relation to this folder
	 */
	public FileController(String inputFileLocation) {
		teamList = new ArrayList<>();
	}
}
