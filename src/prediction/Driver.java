/**
 * 
 */
package prediction;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author abreyen
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("file/mcb2019CSV.csv");
		FileController fc = null;
		
		try {
			fc = new FileController(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("fileNotFound");
			System.out.println(file.getPath());
		}
		RatingController rc = new RatingController(fc.getAllTeams());
		
		
	}

}
