/**
 * 
 */
package prediction;
import java.util.*;
/**
 * @author zeemh
 *
 */
public class RatingController {

	public void outputRating(ArrayList<Team> teams) {
		Collections.sort(teams, new RankingComparator());
	}
}

class RankingComparator implements Comparator<Team>{
    
    public int compare(Team team1, Team team2){
        
        if( team1.getRank() > team2.getRank() ){
            return 1;
        }else if( team1.getRank() > team2.getRank() ){
            return -1;
        }else{
            return 0;
        }
        
    }
}