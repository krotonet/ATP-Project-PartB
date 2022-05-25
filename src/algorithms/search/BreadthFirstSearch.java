package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    private final Queue<AState> frontier;
    private final List<AState> solutionPath;

    public BreadthFirstSearch() {
        this.frontier = new LinkedList<>();
        this.solutionPath = new ArrayList<>();
    }

    @Override
    public Solution solve(ISearchable domain) {
        this.frontier.add(domain.getStart());
        Solution solution = null;
        while(true){
            if(frontier.isEmpty()){
                break;
            }
            AState currentState = this.frontier.poll();
            this.solutionPath.add(currentState);
            if(currentState.equals(domain.getGoal())){
                solution = new Solution(currentState);
                break;
            }
            List<AState> neighbors = domain.getPossibleStates(currentState);
            for(AState possibleNeighbor : neighbors){
                if(!solutionPath.contains(possibleNeighbor) && !frontier.contains(possibleNeighbor)){
                    this.frontier.add(possibleNeighbor);
                }
            }
        }
        this.numOfNodes = this.solutionPath.size();
        return solution;
    }

    @Override
    public String getName() {
        return "Breath First Search";
    }

}
