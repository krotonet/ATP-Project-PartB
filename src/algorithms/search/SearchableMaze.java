package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private final Maze maze;
    private final AState startState;
    private final AState goalState;

    public SearchableMaze(Maze _maze) {
        this.maze = _maze;
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

    /********* continue **************/

    public ArrayList<AState> getPossibleStates(AState currentState){ //return all possible states of the current state.
        if (currentState==null)
            return null;

        ArrayList<AState> possibleStates = new ArrayList<AState>();
        MazeState mazeState;
        mazeState = (MazeState) currentState;

        Position pPosition = mazeState.getPosition();
        int currentRow = pPosition.getRowIndex();
        int currentColumn = pPosition.getColumnIndex();

//        boolean rightUpCorner = false;
//        boolean leftUpCorner = false;


        if(IsFree(maze, currentRow, currentColumn, 2)) { //go up
            possibleStates.add(createNewAState(currentRow - 1, currentColumn,  currentState,2));
//            leftUpCorner = true;
            if(IsFree(maze, currentRow , currentColumn, 7)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn - 1,  currentState,7));
            }
            if(IsFree(maze, currentRow , currentColumn, 5)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn + 1,  currentState,5));
            }
        }

        //left -> column - 1
        if(IsFree(maze, currentRow, currentColumn, 1)) { //go right
            possibleStates.add(createNewAState(currentRow, currentColumn + 1,  currentState,1));
//            rightUpCorner = true;
            if(IsFree(maze, currentRow, currentColumn, 5)) { //right->up/up->right corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn + 1,  currentState,5));
            }
            if(IsFree(maze, currentRow, currentColumn, 6)) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn + 1,  currentState,5));
            }

        }

        if(IsFree(maze, currentRow, currentColumn, 4)) { //go down
            possibleStates.add(createNewAState(currentRow + 1, currentColumn,  currentState,4));
            if(IsFree(maze, currentRow, currentColumn, 6)) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn + 1,  currentState,6));
            }
            if(IsFree(maze, currentRow , currentColumn, 8)) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn - 1,  currentState,8));
            }
        }

        if(IsFree(maze, currentRow, currentColumn, 3)) { //go left
            possibleStates.add(createNewAState(currentRow, currentColumn - 1,  currentState,3));
            if(IsFree(maze, currentRow , currentColumn, 7)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn - 1,  currentState,7));
            }
            if(IsFree(maze, currentRow , currentColumn, 8)) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn - 1,  currentState,8));
            }
        }



        return possibleStates;
    }


    public ArrayList<AState> getPossibleStates1(AState currentState){ //return all possible states of the current state.
        if (currentState==null)
            return null;

        ArrayList<AState> possibleStates = new ArrayList<AState>();
        MazeState mazeState;
        mazeState = (MazeState) currentState;

        Position pPosition = mazeState.getPosition();
        int currentRow = pPosition.getRowIndex();
        int currentColumn = pPosition.getColumnIndex();

//        boolean rightUpCorner = false;
//        boolean leftUpCorner = false;


        //left -> column - 1
        if(IsFree(maze, currentRow, currentColumn, 1)) { //go right
            possibleStates.add(createNewAState(currentRow, currentColumn + 1,  currentState,1));
//            rightUpCorner = true;
            if(IsFree(maze, currentRow, currentColumn, 5)) { //right->up/up->right corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn + 1,  currentState,5));
            }
            if(IsFree(maze, currentRow, currentColumn, 6)) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn + 1,  currentState,5));
            }

        }
        if(IsFree(maze, currentRow, currentColumn, 3)) { //go left
            possibleStates.add(createNewAState(currentRow, currentColumn - 1,  currentState,3));
//            leftUpCorner = true;
            if(IsFree(maze, currentRow , currentColumn, 7)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn - 1,  currentState,7));
            }
            if(IsFree(maze, currentRow , currentColumn, 8)) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn - 1,  currentState,8));
            }

        }

        if(IsFree(maze, currentRow, currentColumn, 2)) { //go up
            possibleStates.add(createNewAState(currentRow - 1, currentColumn,  currentState,2));
            if(IsFree(maze, currentRow , currentColumn, 7)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn - 1,  currentState,7));
            }
            if(IsFree(maze, currentRow , currentColumn, 5)) { //left->up/up->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow - 1, currentColumn + 1,  currentState,5));
            }
        }
        if(IsFree(maze, currentRow, currentColumn, 4)) { //go down
            possibleStates.add(createNewAState(currentRow + 1, currentColumn,  currentState,4));
            if(IsFree(maze, currentRow, currentColumn, 6)) { //right->down/down->right corner -> column + 1 && row + 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn + 1,  currentState,6));
            }
            if(IsFree(maze, currentRow , currentColumn, 8)) { //left->down/down->left corner -> column + 1 && row - 1
                possibleStates.add(createNewAState(currentRow + 1, currentColumn - 1,  currentState,8));
            }
        }



        return possibleStates;
    }

    public AState createNewAState(int row, int column, AState currentState, int fatherPosition){
        Position newPosition = new Position(row, column);
        int cost = 0;
        MazeState mazeState = (MazeState) currentState;

        Position pPosition = mazeState.getPosition();
        if ((pPosition.getColumnIndex() == column) || (pPosition.getRowIndex() == row)) {
            cost=10;
            //cost = currentState.getCost() + 10;
        }
        else {
            cost=15;
            //cost = currentState.getCost() + 15;
        }
        return new MazeState(newPosition, currentState, cost);
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

}
