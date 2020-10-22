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
		
	}
	
	/**
	 * @return the rank
	 */
	public double getRank() {
		return this.rank;
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
		totalNumGames++;
	}
	
	/**
	 * Method used to put zero in the matrix for teams that did not play each other.
	 * Because they did not play each other it is assumed the teams are not already
	 * mapped to each other.
	 * 
	 * @param inputTeamName the name of the team this Team did not play against
	 */
	public void addGameNotPlayedToSeason(String inputTeamName) {
		//Unplayed teams shouldn't be in this teams season yet
		if (inputTeamName == this.name) {
			//seems to have 1 extra game to totalNumGames so I just decremented by one.
			this.totalNumGames--;
			season.put(this.name, totalNumGames);
		}
		else if (!season.containsKey(inputTeamName)) {
		season.put(inputTeamName, 0);
		}
		else {
			System.out.println("ERROR: Unplayed team should not already be in "
					+ "this team's season!");
		}
	}
	
	/**
	 * determines whether this Team has played against another team
	 * 
	 * @param rivalName the team we are checking if they played against
	 * @return output whether or not they have played against each other
	 */
	public boolean hasPlayedAgainst (String rivalName) {
		boolean output;
		
		if (season.containsKey(rivalName)) {
			output = true;
		}
		else {
			output = false;
		}
		
		return output;
	}
}
