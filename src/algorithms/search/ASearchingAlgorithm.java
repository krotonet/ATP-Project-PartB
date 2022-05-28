package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numOfNodes;
    protected Queue<AState> frontier;
    protected HashSet<AState> visitedStates;

    public Solution solve(ISearchable domain) {
        this.frontier.add(domain.getStart());
        Solution solution = null;
        while(true){
            if(frontier.isEmpty()){
                break;
            }
            AState currentState = this.frontier.poll();
            if(currentState.equals(domain.getGoal())){
                solution = new Solution(currentState);
                break;
            }
            List<AState> neighbors = domain.getPossibleStates(currentState);
            for(AState possibleNeighbor : neighbors){
                if(!visitedStates.contains(possibleNeighbor) && !frontier.contains(possibleNeighbor)){
                    this.frontier.add(possibleNeighbor);
                    this.visitedStates.add(possibleNeighbor);
                }
            }
        }
        this.numOfNodes = this.visitedStates.size();
        return solution;
    }

    public String getNumberOfNodesEvaluated(){
        return String.valueOf(this.numOfNodes);
    }


}
