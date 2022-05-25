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

    public Maze(int rows, int cols){
        this.rows = rows;
        this.columns = cols;
        this.maze = new int[rows][cols];
    }

    public void setMaze(int value){
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
        this.maze[row][col] = value;
    }

    public int getValue(int row, int col){
        return this.maze[row][col];
    }

    public void print() {
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

    public void printNew() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
                if (j!=maze[0].length-1)
                    System.out.print(",");
            }
            System.out.println();
        }

    }

    public void printMazeWithSolution(Solution mysol2){

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
                    for(int x=0 ;x<mysol2.getSolutionPath().size() ;x++){
                        AState try2 = mysol2.getSolutionPath().get(x);
                        MazeState tryM = (MazeState) try2;
                        if(tryM.getPosition().getRowIndex() ==i && tryM.getPosition().getColumnIndex()==j) {
                            System.out.print(" \u001b[43m ");
                            break;
                        }
                        else if(x==mysol2.getSolutionPath().size()-1)
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