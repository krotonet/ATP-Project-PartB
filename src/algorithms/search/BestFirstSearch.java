package algorithms.search;

import java.util.HashSet;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        this.numOfNodes = 0;
        this.frontier = new PriorityQueue<>(1, (AState a,AState b) -> a.getCost() - b.getCost());
        this.visited = new HashSet<>();
    }

    @Override
    public String getName() {
        return "Best First Search";
    }

}
