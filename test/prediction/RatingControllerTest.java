/**
 * 
 */
package prediction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author zeemh
 *
 */
class RatingControllerTest {
	private ArrayList<Team> teams = new ArrayList<Team>();
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Team one = new Team("one");
		one.setSumPointDifferential();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link prediction.RatingController#RatingController(java.util.ArrayList)}.
	 */
	@Test
	void testRatingController() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link prediction.RatingController#rankTeams(java.util.ArrayList)}.
	 */
	@Test
	void testRankTeams() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link prediction.RatingController#teamRatingSelectionSort(java.util.ArrayList)}.
	 */
	@Test
	void testTeamRatingSelectionSort() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link prediction.RatingController#outputRanking(java.util.ArrayList)}.
	 */
	@Test
	void testOutputRanking() {
		fail("Not yet implemented"); // TODO
	}

}