package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    private final ArrayList<AState> finalSolution;

    public Solution(AState finalState){
        finalSolution = new ArrayList<AState>();
        createSolution(finalState);
    }

    public ArrayList<AState> getSolutionPath(){
        return finalSolution;
    }

    /**
     *
     * @param state start state of the maze
     * create a list of the solution`s states in the right order.
     */
    private void createSolution(AState state)
    {
        Stack<AState> tempPath = new Stack<>();
        AState currentState = state;
        while ( currentState!= null ){
            tempPath.add(currentState);
            currentState = currentState.getFoundBy();
        }
        while(!tempPath.isEmpty()){
            this.finalSolution.add(tempPath.pop());
        }
    }

}
