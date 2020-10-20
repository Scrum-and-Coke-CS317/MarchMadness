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
	private HashMap<String,Team> teamsMap;
	private String[] mapKeys;
	/**
	 * initializes the matrix and sets teams
	 * 
	 * @param teams
	 */
	public RatingController(HashMap<String,Team> teams){
		this.teamsMap = teams;
		 Collection<Team> values = teams.values(); 
		  
	        // Creating an ArrayList of values 
	        this.teams = new ArrayList<>(values);
		int size = this.teams.size();
		 pointDifferentials = new double[1][size];
		 teamMatrix = new double[size][size];
		initMatrix();

	}

	
	/**
	 * initializes the matrix
	 */

	private void initMatrix() {
		Object[] mapObjectKeys = teamsMap.keySet().toArray();
		mapKeys = Arrays.copyOf(mapObjectKeys,mapObjectKeys.length,
				String[].class);
		sort(mapKeys, 0, mapKeys.length - 1);
		for (int y = 0; y < teams.size(); y++) {
			HashMap<String, Integer> temp = getTeam(mapKeys[y]).getSeason();
			
			Object[] objectKeys = temp.keySet().toArray();
			String[] keys = Arrays.copyOf(objectKeys,objectKeys.length,
					String[].class);
			sort(keys, 0, keys.length - 1);
			int [] gamesPlayed = new int[keys.length];
			for(int n = 0; n < keys.length; n++) {
				gamesPlayed[n] = (int) temp.get(keys[n]);
			}
			
			if (y == teams.size() - 1)
				pointDifferentials[0][y] = 0;
			else {
				pointDifferentials[0][y] = getTeam(mapKeys[y]).getSumPointDifferential();
			}
			for (int x = 0; x < teams.size(); x++) {
				if (x == teams.size() - 1) {
					teamMatrix[x][y] = 1;

				} 
				else {
					teamMatrix[x][y] = gamesPlayed[x];
				}
			}
		}
		season = new Matrix(teamMatrix);
		pointDifferentialsMatrix = new Matrix(pointDifferentials);

	}
	
	private Team getTeam(String teamName) {
		for(Team i: teams) {
			if(i.getName().equals(teamName)) {
				return i;
			}
		}
		return null;
	}
	
	private void merge(String arr[], int l, int m, int r) {
		        // Find sizes of two subarrays to be merged 
		        int n1 = m - l + 1; 
		        int n2 = r - m; 
		  
		        /* Create temp arrays */
		        String L[] = new String[n1]; 
		        String R[] = new String[n2]; 
		  
		        /*Copy data to temp arrays*/
		        for (int i = 0; i < n1; ++i) 
		            L[i] = arr[l + i]; 
		        for (int j = 0; j < n2; ++j) 
		            R[j] = arr[m + 1 + j]; 
		  
		        /* Merge the temp arrays */
		  
		        // Initial indexes of first and second subarrays 
		        int i = 0, j = 0; 
		  
		        // Initial index of merged subarry array 
		        int k = l; 
		        while (i < n1 && j < n2) { 
		            if (L[i].compareTo(R[j]) <= 0) { 
		                arr[k] = L[i]; 
		                i++; 
		            } 
		            else { 
		                arr[k] = R[j]; 
		                j++; 
		            } 
		            k++; 
		        } 
		  
		        /* Copy remaining elements of L[] if any */
		        while (i < n1) { 
		            arr[k] = L[i]; 
		            i++; 
		            k++; 
		        } 
		  
		        /* Copy remaining elements of R[] if any */
		        while (j < n2) { 
		            arr[k] = R[j]; 
		            j++; 
		            k++; 
		        } 
		    } 
		  
		    // Main function that sorts arr[l..r] using 
		    // merge() 
		    private void sort(String arr[], int l, int r) 
		    { 
		        if (l < r) { 
		            // Find the middle point 
		            int m = (l + r) / 2; 
		  
		            // Sort first and second halves 
		            sort(arr, l, m); 
		            sort(arr, m + 1, r); 
		  
		            // Merge the sorted halves 
		            merge(arr, l, m, r); 
		        }
		    } 
		


	/**
	 * sets the ranks for all the teams in the array.
	 * 
	 * @param teams
	 */
	public HashMap<String, Team> rankTeams() {


		double det = season.det();
		season = season.inverse();
		season = season.times(1 / det);
		Matrix rankMatrix = pointDifferentialsMatrix.times(season);
		double[][] ranks = rankMatrix.getArrayCopy();
		
		for (int i = 0; i < ranks[0].length; i++) {
			
			teamsMap.get(mapKeys[i]).setRank(ranks[0][i]);
		}
		return teamsMap;

	}

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
			csvWriter.append(output);



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
