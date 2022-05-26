package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {
        this.numOfNodes = 0;
        this.frontier = new LinkedList<>();
        this.solutionPath = new HashSet<>();
    }

    @Override
    public String getName() {
        return "Breath First Search";
    }

}
