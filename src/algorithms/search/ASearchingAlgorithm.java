package algorithms.search;

import java.util.List;
import java.util.Queue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numOfNodes;
    protected Queue<AState> frontier;
    protected List<AState> solutionPath;

    public ASearchingAlgorithm(){
        this.numOfNodes=0;
    }

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

    public String getNumberOfNodesEvaluated(){
        return String.valueOf(this.numOfNodes);
    }


}
