package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchableMaze implements ISearchable{
    private Maze maze;
    private AState startState;
    private AState goalState;
    //private boolean[][] allStates;

    public SearchableMaze(Maze _maze) { //constructor
        this.maze = _maze;
        startState = new MazeState(maze.getStartPosition());
        goalState = new MazeState(maze.getGoalPosition());
    }
    public AState getStart() {
        return startState;
    }
    public AState getEnd(){
        return goalState;
    }

    /********* continue **************/

    public ArrayList<AState> getPossibleStates(AState curr){ //return all possible states of the current state.
        if (curr==null)
            return null;

        ArrayList<AState> possibleStates = new ArrayList<AState>();

        Position pPosition = curr.getPosition();
        int currentRow = pPosition.getRowIndex();
        int currentColumn = pPosition.getColumnIndex();

        boolean rightUpCorner = false;
        boolean leftUpCorner = false;

        //left -> column - 1
        if(IsFree(maze, currentRow, currentColumn, 1)) { //go right
            possibleStates.add(new MazeState(new Position(currentRow, currentColumn + 1)));
            rightUpCorner = true;
            if(IsFree(maze, currentRow, currentColumn, 5)) { //right->up/up->right corner -> column + 1 && row - 1
                possibleStates.add(new MazeState(new Position(currentRow - 1, currentColumn + 1)));
            }
            if(IsFree(maze, currentRow, currentColumn, 6)) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(new MazeState(new Position(currentRow + 1, currentColumn + 1)));
            }

        }
        if(IsFree(maze, currentRow, currentColumn, 3)) { //go left
            possibleStates.add(new MazeState(new Position(currentRow, currentColumn - 1)));
            leftUpCorner = true;
            if(IsFree(maze, currentRow , currentColumn, 7)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(new MazeState(new Position(currentRow - 1, currentColumn - 1)));
            }
            if(IsFree(maze, currentRow , currentColumn, 8)) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(new MazeState(new Position(currentRow + 1, currentColumn - 1)));
            }
        }

        if(IsFree(maze, currentRow, currentColumn, 4)) { //go down
            possibleStates.add(new MazeState(new Position(currentRow + 1,currentColumn)));
            if(!rightUpCorner && IsFree(maze, currentRow, currentColumn, 6)) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(new MazeState(new Position(currentRow + 1, currentColumn + 1)));
            }
            if(!leftUpCorner && IsFree(maze, currentRow , currentColumn, 8)) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(new MazeState(new Position(currentRow + 1, currentColumn - 1)));
            }
        }

        if(IsFree(maze, currentRow, currentColumn, 2)) { //go up
            possibleStates.add(new MazeState(new Position(currentRow - 1, currentColumn)));
            if(!leftUpCorner && IsFree(maze, currentRow , currentColumn, 7)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(new MazeState(new Position(currentRow - 1, currentColumn - 1)));
            }
            if(!rightUpCorner && IsFree(maze, currentRow , currentColumn, 5)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(new MazeState(new Position(currentRow - 1, currentColumn + 1)));
            }
        }

        return possibleStates;
    }
    public boolean IsFree(Maze maze, int row, int column, int direction){
        switch (direction){
            case 1://right
                if(column + 1 < maze.getColumns() && maze.getValue(row,column + 1) == 0) return true;
                break;

            case 2://up
                if(row - 1 >= 0 && maze.getValue(row - 1, column) == 0) return true;
                break;

            case 3://left
                if(column - 1 >= 0 && maze.getValue(row,column - 1) == 0) return true;
                break;

            case 4://down
                if(row + 1 < maze.getRows() && maze.getValue(row + 1, column) == 0) return true;
                break;

            case 5:// right->up/up->right corner -> column + 1 && row -1
                if(column + 1 < maze.getColumns() && row - 1 >= 0 && maze.getValue(row - 1,column + 1) == 0) return true;
                break;

            case 6://2 right->down/down->right corner -> column + 1 && row + 1
                if(column + 1 < maze.getColumns() && row + 1 < maze.getRows() && maze.getValue(row + 1,column + 1) == 0) return true;
                break;

            case 7://3 left->up/up->left corner -> row - 1 && col - 1
                if(row - 1 >= 0 && column - 1 >= 0 && maze.getValue(row - 1, column - 1) == 0)return true;
                break;

            case 8://4 left->down/down->left corner -> row + 1 && col - 1
                if(column - 1 >= 0 && row + 1 < maze.getRows() && maze.getValue(row + 1,column - 1) == 0) return true;
                break;

        }
        return false;
    }



    public void addToList(ArrayList<AState> possibleStates,AState curr,int row,int col){
        if(curr==null)
            return;
        possibleStates.add(new MazeState(new Position(curr.getPosition().getRowIndex() + row,curr.getPosition().getColumnIndex() +col)));
    }

    public int getRowMaze(){
        return maze.getRows();
    }
    public int getColMaze(){
        return maze.getColumns();
    }



    public Maze getMaze(){
        return maze;
    }

}
