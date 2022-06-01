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
    private Position goalPosition;

    /**
     * constructor
     * if any dimension lower or equal 2, we generate it as dimension 3.
     * @param rows
     * @param columns
     */
    public Maze(int rows, int columns){
        if (rows <= 2) rows = 3;
        if(columns <= 2 ) columns=3;
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];
    }

    //copy constructor
    public Maze(Maze other){
        this.rows = other.getRows();
        this.columns = other.getColumns();
        this.startPosition = new Position(other.getStartPosition().getRowIndex(), other.getStartPosition().getColumnIndex());
        this.goalPosition = new Position(other.getGoalPosition().getRowIndex(), other.getGoalPosition().getColumnIndex());
        this.maze = new int[this.rows][this.columns];
    }

    /**
     * change each cell of the maze to the given value.
     * @param value
     */
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
        return this.goalPosition;
    }

    public void setStartPosition(Position newPosition) {
        this.startPosition = newPosition;
    }

    public void setGoalPosition(Position position) {
        this.goalPosition = position;
    }

    public void breakWall(int row, int columns){
        this.maze[row][columns] = 0;
    }

    public void buildWall(int row, int columns){
        this.maze[row][columns] = 1;
    }

    public int getValue(int row, int col){
        return this.maze[row][col];
    }

    public void print(){
        String mazePlot = "{";
        String toAdd = "";
        for(int i = 0; i < this.rows; ++i) {
            mazePlot += "{";
            for(int j = 0; j < this.columns; ++j) {
                if(this.startPosition.getRowIndex() == i && this.startPosition.getColumnIndex() == j)
                    if (j != this.columns - 1)
                        mazePlot += "E,";
                    else
                        mazePlot += "E";
                else if(this.goalPosition.getRowIndex() == i && this.goalPosition.getColumnIndex() == j)
                    if (j != this.columns - 1)
                        mazePlot += "S,";
                    else
                        mazePlot += "S";
                else {
                    if (j != this.columns - 1)
                        mazePlot += this.maze[i][j] + ",";
                    else
                        mazePlot += this.maze[i][j];
                }
            }
            if(i != this.rows - 1)
                mazePlot += "}\n,";
            else
                mazePlot += "}}";
        }
        System.out.println(mazePlot);
    }

    /**
     * printing the maze in colors.
     * green is the start position
     * blue is goal position
     * purple is a wall
     */
    public void coloredPrint(){
        for(int i = 0; i < this.rows; ++i) {
            for(int j = 0; j < this.columns; ++j) {
                if (i == this.startPosition.getRowIndex() && j == this.startPosition.getColumnIndex()) {
                    System.out.print(" \u001B[42m ");
                } else if (i == this.goalPosition.getRowIndex() && j == this.goalPosition.getColumnIndex()) {
                    System.out.print(" \u001b[44m ");
                } else if (this.maze[i][j] == 1) {
                    System.out.print(" \u001b[45m ");
                } else {
                    System.out.print(" \u001b[107m ");
                }
            }
            System.out.println(" \u001b[107m");
        }
        System.out.println();
    }

    /**
     *
     * @param solution , solution to color it inside the maze
     */
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
        System.out.println();
    }

}