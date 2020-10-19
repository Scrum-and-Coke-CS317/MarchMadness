/**
 * 
 */
package prediction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.HashMap;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author zeemh
 *
 */
class RatingControllerTest {
	private ArrayList<Team> teams = new ArrayList<Team>();

	private RatingController rController;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		
		Team one = new Team("one");
		one.setSumPointDifferential(-40);
		one.setTotalNumGames(4);
		HashMap<String, Integer> mapOne = new HashMap<String,Integer>();
		mapOne.put("one", 4);
		mapOne.put("two", -1);
		mapOne.put("three", -1);
		mapOne.put("four", -1);
		mapOne.put("five", -1);
		one.setSeason(mapOne);
		
		Team two = new Team("two");
		two.setSumPointDifferential(32);
		two.setTotalNumGames(4);
		HashMap<String,Integer> mapTwo = new HashMap<String,Integer>();
		mapTwo.put("one", -1);
		mapTwo.put("two", 4);
		mapTwo.put("three", -1);
		mapTwo.put("four", -1);
		mapTwo.put("five", -1);
		two.setSeason(mapTwo);
		
		Team three = new Team("three");
		three.setSumPointDifferential(15);
		three.setTotalNumGames(4);
		HashMap<String,Integer> mapThree = new HashMap<String,Integer>();
		mapThree.put("one", -1);
		mapThree.put("two", -1);
		mapThree.put("three", 4);
		mapThree.put("four", -1);
		mapThree.put("five", -1);
		three.setSeason(mapThree);
		
		Team four = new Team("four");
		four.setSumPointDifferential(103);
		four.setTotalNumGames(4);
		HashMap<String,Integer> mapFour = new HashMap<String,Integer>();
		mapFour.put("one", -1);
		mapFour.put("two", -1);
		mapFour.put("three", -1);
		mapFour.put("four", 4);
		mapFour.put("five", -1);
		four.setSeason(mapFour);
		
		Team five = new Team("five");
		five.setSumPointDifferential(-106);
		five.setTotalNumGames(4);
		HashMap<String,Integer> mapFive = new HashMap<String,Integer>();
		mapFive.put("one", -1);
		mapFive.put("two", -1);
		mapFive.put("three", -1);
		mapFive.put("four", -1);
		mapFive.put("five", 4);
		five.setSeason(mapFive);
		
		teams.add(one);
		teams.add(two);
		teams.add(three);
		teams.add(four);
		teams.add(five);
		
		rController = new RatingController(teams);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {

		teams.clear();

	}

	/**
	 * Test method for {@link prediction.RatingController#rankTeams(java.util.ArrayList)}.
	 */
	@Test
	void testRankTeams() {

		
		rController.rankTeams();
		assertTrue(teams.get(0).getRank() == 0.496);
		assertTrue(teams.get(1).getRank() == -0.08);
		assertTrue(teams.get(2).getRank() == 0.056);
		assertTrue(teams.get(3).getRank() == -0.648);
		assertTrue(teams.get(4).getRank() == -0.176);

	}

	/**
	 * Test method for {@link prediction.RatingController#teamRatingSelectionSort(java.util.ArrayList)}.
	 */
	@Test
	void testTeamRatingSelectionSort() {

		rController.rankTeams();
		rController.teamRatingSelectionSort();
		assertTrue(teams.get(0).getName().equals("one"));
		assertTrue(teams.get(1).getName().equals("three"));
		assertTrue(teams.get(2).getName().equals("two"));
		assertTrue(teams.get(3).getName().equals("five"));
		assertTrue(teams.get(4).getName().equals("four"));


	}

}
