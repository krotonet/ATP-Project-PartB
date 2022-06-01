package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private final Stack<AState> openList;

    public DepthFirstSearch() {
        this.numOfNodes = 0;
        this.visited = new HashSet<>();
        this.openList = new Stack<>();
    }

    /**
     * search for a solution based on dfs algorithm
     * @param domain, problem adapted for search algorithm
     * @return Solution -list of all states
     */
    @Override
    public Solution solve(ISearchable domain) {
        ArrayList<AState> neighbors;
        Solution solution = null;
        int countVisited = 1;
        openList.push(domain.getStart());
        visited.add(domain.getStart());

        while (!this.openList.isEmpty()){
            AState current = this.openList.pop();

            if (current.equals(domain.getGoal())) {
                domain.getGoal().cost = current.getCost();
                solution = new Solution(current);
                countVisited++;
                break;
            }

            neighbors = domain.getAllPossibleStates(current);
            for(AState possibleNeighbor : neighbors){
                if(!this.visited.contains(possibleNeighbor)) {
                    this.visited.add(possibleNeighbor);
                    openList.push(possibleNeighbor);
                    countVisited++;
                }
            }
        }
        this.numOfNodes = countVisited;
        return solution;
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }

    public String getNumberOfNodesEvaluated(){
        return String.valueOf(this.numOfNodes);
    }

}
