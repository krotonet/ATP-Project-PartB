package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numOfNodes;
    protected Queue<AState> frontier;
    protected HashSet<AState> visitedStates;

    /**
     *
     * @param domain, problem adapted for search algorithm
     * @return
     */
    public Solution solve(ISearchable domain) {

        this.frontier.add(domain.getStart());
        Solution solution = null;
        int counter = 1;
        while(true){
            if(frontier.isEmpty()){
                break;
            }
            AState currentState = this.frontier.poll();
            this.visitedStates.add(currentState);
            if(currentState.equals(domain.getGoal())){
                counter++;
                domain.getGoal().cost = currentState.getCost();
                solution = new Solution(currentState);
                break;
            }
            List<AState> neighbors = domain.getAllPossibleStates(currentState);

            for(AState possibleNeighbor : neighbors){
                if(!visitedStates.contains(possibleNeighbor) && !frontier.contains(possibleNeighbor)){
                    this.frontier.add(possibleNeighbor);
                    this.visitedStates.add(possibleNeighbor);
                    counter++;
                }
            }
        }
        this.numOfNodes = counter;
        return solution;
    }

    /**
     *
     * @return , number of nodes the algorithm has visited
     */
    public String getNumberOfNodesEvaluated(){
        return String.valueOf(this.numOfNodes);
    }


}
