package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private Stack<AState> openList;


    public DepthFirstSearch() {
        super();
        this.numOfNodes = 0;
        //allStates = new boolean[maze.getRows()][maze.getColumns()];
    }

    @Override
    public Solution solve(ISearchable domain) {
        boolean[][] allStates = new boolean[domain.getRowMaze()][domain.getColMaze()];
        if (domain==null)
            return null;
        openList = new Stack<AState>();
        Solution solution = new Solution();
        openList.add(domain.getStart());
        ArrayList<AState> Neighbors;
        while (!openList.isEmpty()){
            AState curr = openList.pop();
            Neighbors = domain.getPossibleStates(curr);

            for(int i=0;i<Neighbors.size();i++){
                Position currNeighbor = Neighbors.get(i).getPosition();
                if(!isVisited(allStates,currNeighbor)){
                    numOfNodes++;
                    Neighbors.get(i).setFoundBy(curr);
                    changeState(allStates, currNeighbor,true);
                    openList.push(Neighbors.get(i)); //add neighbors to open list

                }
                if(Neighbors.get(i).getPosition().equals(domain.getEnd().getPosition())){
                    solution.createSolution(Neighbors.get(i),domain.getStart());
                    return solution;
                }
            }

        }
        return null;
    }

    public void changeState(boolean[][] allStates,Position position,boolean value){
        allStates[position.getRowIndex()][position.getColumnIndex()]= value;
    }

    public boolean isVisited(boolean [][] allStates,Position position){
        return allStates[position.getRowIndex()][position.getColumnIndex()];
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }





}
