package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.MazeState;
import algorithms.search.Solution;

import java.util.Arrays;

public class Maze {
    private final int rows;
    private final int columns;
    private final int[][] maze;
    private Position startPosition;
    private Position endPosition;

    public Maze(int rows, int columns){
        if ( rows < 2  || columns < 2 ) throw new IllegalArgumentException("Maze dimensions have to be at least 2x2");
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];
    }

    public Maze(Maze other){
        this.rows = other.getRows();
        this.columns = other.getColumns();
        this.maze = new int[this.rows][this.columns];
    }

    public void initializeMaze(int value){
        if (!(value == 0  || value == 1)) throw new IllegalArgumentException("Maze value can only be 0 or 1");
        for(int[] array : this.maze){
            Arrays.fill(array, value);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Position getStartPosition(){
        return this.startPosition;
    }

    public Position getGoalPosition(){
        return this.endPosition;
    }

    public void setStartPosition(Position newPosition) {
        this.startPosition = newPosition;
    }

    public void setEndPosition(Position position) {
        this.endPosition = position;
    }

    public void setValue(int row, int col, int value){
        if ( !( value == 0  || value == 1) ) throw new IllegalArgumentException("Maze value can only be 0 or 1");
        this.maze[row][col] = value;
    }

    public int getValue(int row, int col){
        return this.maze[row][col];
    }

    public void print(){
        for(int i = 0; i < this.rows; ++i) {
            for(int j = 0; j < this.columns; ++j) {
                if (i == this.startPosition.getRowIndex() && j == this.startPosition.getColumnIndex()) {
                    System.out.print(" \u001B[42m "); //green-start
                } else if (i == this.endPosition.getRowIndex() && j == this.endPosition.getColumnIndex()) {
                    System.out.print(" \u001b[44m "); //blue-end
                } else if (this.maze[i][j] == 1) {
                    System.out.print(" \u001b[45m "); //purple
                } else {
                    System.out.print(" \u001b[107m "); //white
                }
            }
            System.out.println(" \u001b[107m");
        }
        System.out.println();
    }

    public void printMazeWithSolution(Solution solution){

        for(int i = 0; i < this.maze.length; ++i) {
            for(int j = 0; j < this.maze[i].length; ++j) {

                if (i == this.getStartPosition().getRowIndex() && j == this.getStartPosition().getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"S");
                } else if (i == this.getGoalPosition().getRowIndex() && j == this.getGoalPosition().getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"E");
                } else if ( this.maze[i][j] == 1) {
                    System.out.print(" \u001b[40m ");
                }
                else {
                    for(int x=0 ;x<solution.getSolutionPath().size() ;x++){
                        AState try2 = solution.getSolutionPath().get(x);
                        MazeState tryM = (MazeState) try2;
                        if(tryM.getPosition().getRowIndex() ==i && tryM.getPosition().getColumnIndex()==j) {
                            System.out.print(" \u001b[43m ");
                            break;
                        }
                        else if(x==solution.getSolutionPath().size()-1)
                            System.out.print(" \u001b[107m ");
                    }
                }
            }
            System.out.println(" \u001b[107m");
        }
        System.out.println("");
        System.out.println();
    }

}