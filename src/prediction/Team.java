/**
 * 
 */
package prediction;


import java.util.*;


/**
 * @author zeemh
 *
 */
public class Team {
	private String name;
	private int sumPointDifferential = 0, totalNumGames = 0;
	private double rank;
	private HashMap<String, Integer> season;
	
	/**
	 * Constructor for Team object
	 * 
	 * @param inputName the name of this team
	 */
	public Team(String inputName) {

		season = new HashMap<String, Integer>();

		this.name = inputName;
		rank = 0.0;
	}
	
	/**
	 * @return the rank
	 */
	public double getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(double rank) {
		this.rank = rank;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the sumPointDifferential
	 */
	public int getSumPointDifferential() {
		return sumPointDifferential;
	}
	/**
	 * @param sumPointDifferential the sumPointDifferential to set
	 */
	public void setSumPointDifferential(int sumPointDifferential) {
		this.sumPointDifferential = sumPointDifferential;
	}
	/**
	 * @return the totalNumGames
	 */
	public int getTotalNumGames() {
		return totalNumGames;
	}
	/**
	 * @param totalNumGames the totalNumGames to set
	 */
	public void setTotalNumGames(int totalNumGames) {
		this.totalNumGames = totalNumGames;
	}
	/**
	 * @return the season
	 */
	public HashMap<String, Integer> getSeason() {
		return season;
	}
	/**
	 * @param season the season to set
	 */
	public void setSeason(HashMap<String, Integer> season) {
		this.season = season;
	}
	/**
	 * This method tallies the number of games a certain team played against this team.
	 * the tallies are negative in preperation to be added to a matrix.
	 * 
	 * @param name of team to add the game to season
	 */
	public void addGameToSeason(String name) {
		if(season.containsKey(name)) {
			int temp = season.get(name);
			season.put(name, temp - 1);
		}
		else {
			season.putIfAbsent(name, -1);
		}
	}
}
