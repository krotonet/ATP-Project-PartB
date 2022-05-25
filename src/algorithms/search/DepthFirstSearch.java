package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private final Stack<AState> openList;
    private final HashSet<AState> visited;

    public DepthFirstSearch() {
        this.numOfNodes = 0;
        this.visited = new HashSet<>();
        openList = new Stack<AState>();

    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain==null)
            return null;
        boolean solved = false;
        Solution solution = null;

        openList.add(domain.getStart());
        this.visited.add(domain.getStart());

        ArrayList<AState> Neighbors;

        while (!openList.isEmpty() && !solved){
            AState current = openList.pop();
            Neighbors = domain.getPossibleStates(current);

            for (AState currNeighbor : Neighbors) {
                if (!openList.contains(currNeighbor) && !this.visited.contains(currNeighbor)) {
                    this.visited.add(current);//set visited
                    openList.push(currNeighbor); //add neighbors to open list
                }

                if (currNeighbor.equals(domain.getGoal())) {
                    solution = new Solution(currNeighbor);
                    solved = true;
                    break;
                }
            }
        }
        this.numOfNodes = this.visited.size();

        return solution;
    }

//
//    public Solution solve1(ISearchable domain) {
//        List<AState> neighbors = null;
//        Solution solution = null;
//
//        openList.push(domain.getStart());
//        visited.add(domain.getStart());
//
//        while (!this.openList.isEmpty()){
//            AState current = this.openList.pop();
//
//            if (current.equals(domain.getGoal())) {
//                solution = new Solution(current);
//                break;
//            }
//
//            neighbors = domain.getPossibleStates(current);
//            for(AState possibleNeighbor : neighbors){
//                if(!this.visited.contains(possibleNeighbor)) {
//                    this.visited.add(possibleNeighbor);
//                    openList.push(possibleNeighbor);
//                }
//            }
//        }
//        this.numOfNodes = this.visited.size();
//        return solution;
//    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
