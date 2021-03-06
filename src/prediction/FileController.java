package prediction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.Set;
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
	//NOTE: no getters and setters for these int attributes, just change them here or create as needed.
	private int numAttributes = 5,
				team1Index = 1, 
				team1ScoreIndex = 2, 
				team2Index = 3, 
				team2ScoreIndex = 4;
	/**
	 * default constructor for FileController object
	 */
	public FileController() {
		this.allTeams = new HashMap<>();
	}
	
	/**
	 * Constructs a FileController object.
	 * 
	 * pre: csv must have this format: 2018-11-06,@Buffalo,82,St Francis PA,67
	 * data fields are date(year, month, day), team1, team1Score, team2, team2Score.
	 * Winning team may or may not have '@' symbol preceding its name.
	 * 
	 * @param inputFile the input CSV file to be processed
	 */
	public FileController(File inputFile) throws FileNotFoundException{
		allTeams = new HashMap<>();

		Scanner input;
		
			input = new Scanner(inputFile);
	

		
		//process csv data into allTeams hashmap
		while(input.hasNextLine()) {
			String line = input.nextLine();
			line.trim();
			line = line.replace("@", "");
			String[] lineAttributes = line.split(",");
			
			if (lineAttributes.length == numAttributes) {
				//read important attributes from the line
				String team1Name = lineAttributes[team1Index];
				String team2Name = lineAttributes[team2Index];
				int team1Score = Integer.parseInt(lineAttributes[team1ScoreIndex]);
				int team2Score = Integer.parseInt(lineAttributes[team2ScoreIndex]);
				
				updateTeamInfo(team1Name, team2Name, team1Score - team2Score);
				updateTeamInfo(team2Name, team1Name, team2Score - team1Score);
			}
			
		}
		
		//Unplayed teams have 0s added to each others seasons
		Set<String> temp = allTeams.keySet();
		String[] arrayOfAllTeams = temp.toArray(new String[temp.size()]);
		for (int i = 0; i < arrayOfAllTeams.length; i++) {
			Team currTeam = allTeams.get(arrayOfAllTeams[i]);
			for (int j = 0; j < arrayOfAllTeams.length; j++) {
				String compareTeamName = arrayOfAllTeams[j];
				boolean hasPlayed = currTeam.hasPlayedAgainst(compareTeamName);
				if (!hasPlayed) {
					currTeam.addGameNotPlayedToSeason(compareTeamName);
					allTeams.put(currTeam.getName(), currTeam);
				}
			}
//			//make sure team's season is filled out
//			System.out.println(currTeam.getName());
//			//System.out.println(currTeam.getSeason().size());
//			System.out.println(currTeam.getSeason().get(currTeam.getName()));
//			System.out.println(currTeam.getTotalNumGames());
//			//System.out.println();
		}
//		Team beep = allTeams.get("South Florida");
//		System.out.println(beep.getSeason().get("South Florida"));
//		System.out.println(beep.getSeason().get("Alabama A&M"));
//		System.out.println(beep.getSeason().get("Austin Peay"));
//		System.out.println(beep.getSeason().get("Ohio"));
//		System.out.println(beep.getSeason().get("Frostburg St"));
		input.close();
	}
	


	/**
	 * @return allTeams for this FileController
	 */

	public HashMap<String,Team> getAllTeams(){
		return allTeams;
	}

	
	/**@param inputMap the new allTeams for this FileController*/
	public void setAllTeams(HashMap<String, Team> inputMap) {
		this.allTeams = inputMap;
	}

	

//////////////////////////////HELPER METHODS//////////////////////////////

/**
 * updates Team attributes for game input data. Adjusts sumPointDifferential, 
 * totalNumGames, and adds game to season. Creates new team for allTeams if team isn't
 * already in allTeams.
 * 
 *  @param teamName the name of the team being updated.
 *  @param rivalName the team they played against.
 *  @param sumPointDifferentail the point difference between this team and the rival team.
 */
	private void updateTeamInfo(String teamName, String rivalName, int sumPointDifferential) {
		Team team;
		if (!allTeams.containsKey(teamName)) {
			team = new Team(teamName);
			}
		else {
			team = allTeams.get(teamName);
		}
		
		//adjust the attributes for the game.
		team.setSumPointDifferential(team.getSumPointDifferential() + sumPointDifferential);

		team.setTotalNumGames(team.getTotalNumGames() + 1);

		team.addGameToSeason(rivalName);
		
		allTeams.put(teamName, team);
	}
	
}
