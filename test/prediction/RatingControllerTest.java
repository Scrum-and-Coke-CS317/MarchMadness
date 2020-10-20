/**
 * 
 */
package prediction;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
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
	private HashMap<String,Team> teams = new HashMap<String, Team>();

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
		
		teams.put("one", one);
		teams.put("two", two);
		teams.put("three", three);
		teams.put("four", four);
		teams.put("five", five);
		
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
		DecimalFormat df = new DecimalFormat("##.#####");
		
		HashMap<String, Team> test = new HashMap<String, Team>();
				test = rController.rankTeams();

		//System.out.println(df.format(test.get("two").getRank()));
		assertTrue(df.format(test.get("one").getRank()).equals("-0.0128"));
		assertTrue(df.format(test.get("two").getRank()).equals("-0.00896"));//wrong
		assertTrue(df.format(test.get("three").getRank()).equals("0.0048"));
		assertTrue(df.format(test.get("four").getRank()).equals("0.03296"));
		assertTrue(df.format(test.get("five").getRank()).equals("-0.03392"));

	}

	/**
	 * Test method for {@link prediction.RatingController#teamRatingSelectionSort(java.util.ArrayList)}.
	 */
	@Test
	void testTeamRatingSelectionSort() {

	ArrayList<Team> testRateSort = new ArrayList<Team>();
		rController.rankTeams();
		testRateSort = rController.teamRatingSelectionSort();
		assertTrue(testRateSort.get(0).getName().equals("four"));
		assertTrue(testRateSort.get(1).getName().equals("three"));
		assertTrue(testRateSort.get(2).getName().equals("two"));
		assertTrue(testRateSort.get(3).getName().equals("one"));
		assertTrue(testRateSort.get(4).getName().equals("five"));


	}

}
