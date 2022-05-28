package algorithms.search;

import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        this.numOfNodes = 0;
        this.frontier = new PriorityQueue<>(1, new costComparator());
        this.visitedStates = new HashSet<>();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
