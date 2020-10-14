/**
 * 
 */
package prediction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author scrum-and-coke
 *
 */
class TeamTest {

	Team scrumTeam = new Team("scrumTeam");
	Team andTeam = new Team("andTeam");
	Team cokeTeam = new Team("cokeTeam");
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		//create team (lost against cokeTeam)
		scrumTeam.setRank(1);
		Map<String, Integer> season1 = new HashMap<>();
		scrumTeam.setSeason(season1);
		scrumTeam.setSumPointDifferential(0);
		scrumTeam.setTotalNumGames(1);
		
		//create second team (empty season)
		andTeam.setRank(1);
		Map<String, Integer> season2 = new HashMap<>();
		andTeam.setSeason(season2);
		andTeam.setSumPointDifferential(0);
		andTeam.setTotalNumGames(0);
		
		//create third team (won against scrumTeam)
		cokeTeam.setRank(1);
		Map<String, Integer> season3 = new HashMap<>();
		season3.put("scrumTeam", -1);
		cokeTeam.setSeason(season3);
		cokeTeam.setSumPointDifferential(0);
		cokeTeam.setTotalNumGames(1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		//n/a
	}

	/**
	 * Test method for {@link prediction.Team#addGameToSeason(java.lang.String)}.
	 */
	@Test
	void testAddGameToSeason() {
		//Test adding a team to the season that it's already played
		assertTrue(cokeTeam.getSeason().get("scrumTeam") == -1);
		assertTrue(cokeTeam.getTotalNumGames() == 1);
		cokeTeam.addGameToSeason(scrumTeam.getName());
		cokeTeam.setTotalNumGames(cokeTeam.getTotalNumGames()+1);
		scrumTeam.setTotalNumGames(scrumTeam.getTotalNumGames()+1);
		assertTrue(cokeTeam.getSeason().get("scrumTeam") == -2);
		assertTrue(cokeTeam.getTotalNumGames() == 2);
		assertTrue(cokeTeam.getTotalNumGames() == 2);
		
		//Test adding a team to the season that it hasn't played before
		assertTrue(cokeTeam.getSeason().get("andTeam") == null);
		cokeTeam.addGameToSeason(andTeam.getName());
		cokeTeam.setTotalNumGames(cokeTeam.getTotalNumGames()+1);
		andTeam.setTotalNumGames(andTeam.getTotalNumGames()+1);
		assertTrue(cokeTeam.getSeason().get("andTeam") == -1);
		assertTrue(cokeTeam.getTotalNumGames() == 3);
		assertTrue(andTeam.getTotalNumGames() == 1);
	}

}
