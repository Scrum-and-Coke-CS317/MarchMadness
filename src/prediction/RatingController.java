package prediction;


import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author scrum-and-coke
 *
 */
public class RatingController {

	private int[][] teamMatrix;
	private int[] pointDeferentials;

	/**
	 * tests sorting the arrayList and printing it to a file
	 * 
	 * Please Delete and Create Test Class
	 * @param a
	 */
	public static void main(String a[]){

		ArrayList<Team> arr1= new ArrayList<Team>();

		Team tempTeam4 = new Team();
		tempTeam4.setName("team4");
		tempTeam4.setRank(4);
		arr1.add(tempTeam4);

		Team tempTeam2 = new Team();
		tempTeam2.setName("team2");
		tempTeam2.setRank(2);
		arr1.add(tempTeam2);

		Team tempTeam1 = new Team();
		tempTeam1.setName("team1");
		tempTeam1.setRank(1);
		arr1.add(tempTeam1);

		Team tempTeam3 = new Team();
		tempTeam3.setName("team3");
		tempTeam3.setRank(3);
		arr1.add(tempTeam3);

		Team tempTeam5 = new Team();
		tempTeam5.setName("team5");
		tempTeam5.setRank(5);
		arr1.add(tempTeam5);

		teamRatingSelectionSort(arr1);
		System.out.println("Printing to file...");
		outputRanking(arr1);
	}
	
	/**
	 * this does nothing yet
	 * @param teamName
	 * @return
	 */
	public static int getRating(Team team) {

		return 0;

	}

	/**
	 * This is a selection sorting algorithm to order Teams by their rankings
	 * @param arr an array of teams
	 */
	public static void teamRatingSelectionSort(ArrayList<Team> arr){
		for (int i = 0; i < arr.size(); i++) {
			// find position of smallest rank between (i + 1)th element and last element
			int pos = i;
			for (int j = i; j < arr.size(); j++) {
				if (arr.get(j).getRank() < arr.get(pos).getRank())
					pos = j;
			}
			// Swap min (smallest rank) to current position on array
			Team min = arr.get(pos);
			arr.set(pos, arr.get(i));
			arr.set(i, min);
		}
	}

	/**
	 * This creates a csv file containing all the Teams Sorted by their Rank
	 * @param teamsSortedByRank
	 */
	public static void outputRanking(ArrayList<Team> teamsSortedByRank) {

		try(FileWriter csvWriter = new FileWriter("file/ranking.csv")){
			csvWriter.append("Rank");
			csvWriter.append(", ");
			csvWriter.append("TeamName");
			csvWriter.append("\n");
			
			for (Team team : teamsSortedByRank) { 
				csvWriter.write(team.getRank() + ", " + team.getName());
				csvWriter.append("\n");
			}
			
			csvWriter.flush();
			csvWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
