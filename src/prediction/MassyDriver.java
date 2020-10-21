/**
 * 
 */
package prediction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * @author MHEIMER001
 *
 */
class MassyDriver {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("file/mcb2019CSV.csv");
		FileController fc = new FileController(file);
		HashMap<String,Team> teamsFromFile = fc.getAllTeams();
		RatingController rc = new RatingController(teamsFromFile); 
		HashMap<String,Team> teamsRated = rc.rankTeams();
		rc.outputRanking();
		
	}

}
