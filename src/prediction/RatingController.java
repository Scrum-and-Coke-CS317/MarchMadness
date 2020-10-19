package prediction;

import java.io.FileWriter;
import java.util.*;
import Jama.*;

/**
 * @author scrum-and-coke
 *
 */
public class RatingController {
	ArrayList<Team> allTeams = new ArrayList<Team>();
	private Matrix season, pointDifferentialsMatrix;

	private double[][] teamMatrix;
	private double[][] pointDifferentials;
	private ArrayList<Team> teams;

	public RatingController(ArrayList<Team> teams) {
		this.teams = teams;
		initMatrix();

	}

	private void initMatrix() {
		for (int y = 0; y <= teams.size(); y++) {
			HashMap temp = teams.get(y).getSeason();
			Object[] gamesPlayed = temp.values().toArray();
			if (y == teams.size())
				pointDifferentials[0][y] = 0;
			else {
				pointDifferentials[0][y] = teams.get(y).getSumPointDifferential();
			}
			for (int x = 0; x <= teams.size(); x++) {
				if (y == teams.size()) {
					teamMatrix[x][y] = 1;

				} else {
					teamMatrix[x][y] = (int) gamesPlayed[y];
				}
			}
		}
		season = new Matrix(teamMatrix);
		pointDifferentialsMatrix = new Matrix(pointDifferentials);

	}

	/**
	 * sets the ranks for all the teams in the array.
	 * 
	 * @param teams
	 */
	public void rankTeams() {

		double det = season.det();
		season = season.inverse();
		season = season.times(1 / det);
		season = season.times(pointDifferentialsMatrix);
		double[][] ranks = season.getArrayCopy();
		for (int i = 0; i <= ranks.length; i++) {
			teams.get(i).setRank(ranks[0][i]);
		}

	}
	/**
			 * tests sorting the arrayList and printing it to a file
			 * 
			 * Please Delete and Create Test Class
			 * 
			 * @param a
			 *//*
				 * public static void main(String a[]){
				 * 
				 * ArrayList<Team> arr1= new ArrayList<Team>();
				 * 
				 * Team tempTeam4 = new Team(); tempTeam4.setName("team4");
				 * tempTeam4.setRank(4); arr1.add(tempTeam4);
				 * 
				 * Team tempTeam2 = new Team(); tempTeam2.setName("team2");
				 * tempTeam2.setRank(2); arr1.add(tempTeam2);
				 * 
				 * Team tempTeam1 = new Team(); tempTeam1.setName("team1");
				 * tempTeam1.setRank(1); arr1.add(tempTeam1);
				 * 
				 * Team tempTeam3 = new Team(); tempTeam3.setName("team3");
				 * tempTeam3.setRank(3); arr1.add(tempTeam3);
				 * 
				 * Team tempTeam5 = new Team(); tempTeam5.setName("team5");
				 * tempTeam5.setRank(5); arr1.add(tempTeam5);
				 * 
				 * teamRatingSelectionSort(arr1); System.out.println("Printing to file...");
				 * outputRanking(arr1); }
				 */

	/**
	 * This is a selection sorting algorithm to order Teams by their rankings
	 * 
	 * @param arr an array of teams
	 */
	public void teamRatingSelectionSort() {
		for (int i = 0; i < teams.size(); i++) {
			// find position of smallest rank between (i + 1)th element and last element
			int pos = i;
			for (int j = i; j < teams.size(); j++) {
				if (teams.get(j).getRank() < teams.get(pos).getRank())
					pos = j;
			}
			// Swap min (smallest rank) to current position on array
			Team min = teams.get(pos);
			teams.set(pos, teams.get(i));
			teams.set(i, min);
		}
	}

	/**
	 * This creates a csv file containing all the Teams Sorted by their Rank
	 * 
	 * @param teamsSortedByRank
	 */


	public void outputRanking() {
		String output = "Rank,TeamName\n";

		try(FileWriter csvWriter = new FileWriter("file/ranking.csv")){
			csvWriter.append("Rank");
			csvWriter.append(", ");
			csvWriter.append("TeamName");
			csvWriter.append("\n");


			for (Team team : teams) { 
				csvWriter.write(team.getRank() + ", " + team.getName()+"\n");
				output += team.getRank() + ", " + team.getName()+"\n";

			}

			csvWriter.flush();
			csvWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
