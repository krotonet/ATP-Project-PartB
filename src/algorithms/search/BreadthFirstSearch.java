package algorithms.search;

import java.util.HashSet;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        this.numOfNodes = 0;
        this.frontier = new LinkedList<>();
        this.visitedStates = new HashSet<>();
    }

    @Override
    public String getName() {
        return "BreathFirstSearch";
    }

}
