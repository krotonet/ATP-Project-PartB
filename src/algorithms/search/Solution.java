package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    private ArrayList<AState> finalsolution;

    public Solution(){
        //solution=new ArrayList<AState>();
        finalsolution = new ArrayList<AState>();
    }

    public ArrayList<AState> getSolutionPath(){
        return finalsolution;
    }



    /**
     *
     * @param curr final state of the maze
     * @param startState start state of the maze
     * create a list of the solution`s states in the right order.
     */
    public void createSolution(AState curr,AState startState) {
     /***************************/
        ArrayList<AState> tempSolution = new ArrayList<AState>();
        Stack<AState> temp = new Stack<AState>();
        while (curr!=startState){
            temp.push(curr);
            curr = curr.getFoundBy();
        }
        temp.push(curr); //start
        while(!temp.isEmpty()){
            tempSolution.add(temp.pop());
        }
        this.finalsolution = tempSolution;
    }

}
