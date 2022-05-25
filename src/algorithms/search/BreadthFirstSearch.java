package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        this.frontier = new LinkedList<>();
        this.solutionPath = new ArrayList<>();
    }


    @Override
    public String getName() {
        return "Breath First Search";
    }

}
