package prediction;

import java.util.Comparator;

public class RankSorter implements Comparator<Team> 
{
    @Override
    public int compare(Team t1, Team t2) {
        return t2.getRank().compareTo(t1.getRank());
    }
}