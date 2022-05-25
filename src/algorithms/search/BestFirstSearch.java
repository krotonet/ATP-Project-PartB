package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends ASearchingAlgorithm{

    public BestFirstSearch() {
        this.frontier = new PriorityQueue<>(new costComparator());
        this.solutionPath = new ArrayList<>();
    }

    @Override
    public String getName() {
        return "Best First Search";
    }

}
