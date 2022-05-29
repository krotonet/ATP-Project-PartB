package algorithms.search;

import java.util.Comparator;

public class costComparator implements Comparator<AState> {
    /**
     * comparator function for the priority queue we used in BestSearchAlgorithm.
     * prefer a diagonal step rather than the regular one.
     * @param o1
     * @param o2
     * @return 1 if o1 cost greater that o2 cost.
     */
    @Override
    public int compare(AState o1, AState o2) {
        if(o1 == null || o2 == null) throw new NullPointerException();
        if(o1.getCost() > o2.getCost()) return 1;
        else if(o1.getCost() < o2.getCost()) return -1;
        return 0;
    }
}
