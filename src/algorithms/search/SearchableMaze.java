package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private final Maze maze;
    private final AState startState;
    private final AState goalState;
    int lowPrice = 10, highPrice = 15;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        startState = new MazeState(maze.getStartPosition(), null,0);
        goalState = new MazeState(maze.getGoalPosition(), null,0);
    }

    public AState getStart() {
        return startState;
    }

    public AState getGoal(){
        return goalState;
    }

    public Maze getMaze(){
        return maze;
    }

    /**
     *  generate all currentState's surrounding neighbors for possible solution progress
     * @param currentState , the found by state of each new position generated here
     * @return ,return all possible states of the current state.
     */
    public ArrayList<AState> getAllPossibleStates(AState currentState){

        ArrayList<AState> possibleStates = new ArrayList<>();
        MazeState mazeState;
        mazeState = (MazeState) currentState;

        Position pPosition = mazeState.getPosition();
        int currentRow = pPosition.getRowIndex();
        int currentColumn = pPosition.getColumnIndex();

        if(IsFree(maze, currentRow, currentColumn, "UP")) { //go up
            possibleStates.add(createNewAState(currentRow - 1, currentColumn,  currentState,lowPrice));
            if(IsFree(maze, currentRow , currentColumn, "LEFT-UP")) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn - 1,  currentState,highPrice));
            }
            if(IsFree(maze, currentRow , currentColumn, "RIGHT-UP")) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn + 1,  currentState,highPrice));
            }
        }

        //left -> column - 1
        if(IsFree(maze, currentRow, currentColumn, "RIGHT")) { //go right
            possibleStates.add(createNewAState(currentRow, currentColumn + 1,  currentState,lowPrice));
            if(IsFree(maze, currentRow, currentColumn, "RIGHT-UP")) { //right->up/up->right corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn + 1,  currentState,highPrice));
            }
            if(IsFree(maze, currentRow, currentColumn, "RIGHT-DOWN")) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn + 1,  currentState,highPrice));
            }
        }

        if(IsFree(maze, currentRow, currentColumn, "DOWN")) { //go down
            possibleStates.add(createNewAState(currentRow + 1, currentColumn,  currentState,lowPrice));
            if(IsFree(maze, currentRow, currentColumn, "RIGHT-DOWN")) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn + 1,  currentState,highPrice));
            }
            if(IsFree(maze, currentRow , currentColumn, "LEFT-DOWN")) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn - 1,  currentState,highPrice));
            }
        }

        if(IsFree(maze, currentRow, currentColumn, "LEFT")) { //go left
            possibleStates.add(createNewAState(currentRow, currentColumn - 1,  currentState,lowPrice));
            if(IsFree(maze, currentRow , currentColumn, "LEFT-UP")) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn - 1,  currentState,highPrice));
            }
            if(IsFree(maze, currentRow , currentColumn, "LEFT-DOWN")) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn - 1,  currentState,highPrice));
            }
        }
        return possibleStates;
    }

    /**
     * creating new position to create new maze state
     * @param row
     * @param column
     * @param currentState , founded by state
     * @param cost ,cost of path to get this new state
     * @return
     */
    public AState createNewAState(int row, int column, AState currentState, int cost){
        Position newPosition = new Position(row, column);
        return new MazeState(newPosition, currentState, currentState.getCost() + cost);
    }

    /**
     * check if progress in direction is possible, for enabling generating position
     * of for the needed direction
     * @param maze, current maze
     * @param row ,current's position row
     * @param column ,current's position column
     * @param direction ,which direction the check
     * @return , true if requested direction is possible neighbor
     */
    public boolean IsFree(Maze maze, int row, int column, String direction){
        switch (direction){
            case "RIGHT"://right
                if(column + 1 < maze.getColumns() && maze.getValue(row,column + 1) == 0) return true;
                break;

            case "UP"://up
                if(row - 1 >= 0 && maze.getValue(row - 1, column) == 0) return true;
                break;

            case "LEFT"://left
                if(column - 1 >= 0 && maze.getValue(row,column - 1) == 0) return true;
                break;

            case "DOWN"://down
                if(row + 1 < maze.getRows() && maze.getValue(row + 1, column) == 0) return true;
                break;

            case "RIGHT-UP":// right->up/up->right corner -> column + 1 && row -1
                if(column + 1 < maze.getColumns() && row - 1 >= 0 && maze.getValue(row - 1,column + 1) == 0) return true;
                break;

            case "RIGHT-DOWN"://2 right->down/down->right corner -> column + 1 && row + 1
                if(column + 1 < maze.getColumns() && row + 1 < maze.getRows() && maze.getValue(row + 1,column + 1) == 0) return true;
                break;

            case "LEFT-UP"://3 left->up/up->left corner -> row - 1 && col - 1
                if(row - 1 >= 0 && column - 1 >= 0 && maze.getValue(row - 1, column - 1) == 0)return true;
                break;

            case "LEFT-DOWN"://4 left->down/down->left corner -> row + 1 && col - 1
                if(column - 1 >= 0 && row + 1 < maze.getRows() && maze.getValue(row + 1,column - 1) == 0) return true;
                break;

        }
        return false;
    }

}