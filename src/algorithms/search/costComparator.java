package algorithms.search;

import java.util.Comparator;

public class costComparator implements Comparator<AState> {
    @Override
    public int compare(AState o1, AState o2) {
        if(o1 == null || o2 == null)throw new NullPointerException();
        if(o1.getCost() < o2.getCost()) return 1;
        else if(o1.getCost() > o2.getCost()) return -1;
        return 0;
    }
}
