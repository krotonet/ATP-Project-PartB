package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {
    private int rows;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private int columns;
    private int[][] maze;
    private Position startPosition;
    private Position endPosition;

    public Maze(int rows, int cols){
        this.rows = rows;
        this.columns = cols;
        this.maze = new int[rows][cols];
    }

    public Position getStartPosition(){
        return this.startPosition;
    }

    public Position getGoalPosition(){
        return this.endPosition;
    }

    public void setMaze(int value){
        for(int[] array : this.maze){
            Arrays.fill(array, value);
        }
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
//
//    public String print(){
//        String ans = "";
//        for (int i = 0; i < this.rows; i++){
//            for( int j = 0; j < this.columns; j++){
//                System.out.print(maze[i][j] + " ");
//                ans += maze[i][j];
//            }
//            System.out.print("\n");
//        }
//        return ans;
//    }

    public void print1(){
        for(int y=0;y<rows;y++)
        {
            for(int j=0;j<columns;j++)
            {

                if(y==startPosition.getRowIndex() && j==startPosition.getColumnIndex())
                {
                    System.out.print("S");
                }
                else if(y==endPosition.getRowIndex() && j==endPosition.getColumnIndex())
                {
                    System.out.print("E");
                }
                else if (maze[y][j]==0){
                    System.out.print("0");
                }
                else {
                    System.out.print(maze[y][j]);
                }

            }
            System.out.println("");

        }

    }

    public void print() {
        for(int i = 0; i < this.maze.length; ++i) {
            for(int j = 0; j < this.maze[i].length; ++j) {
                if (i == this.startPosition.getRowIndex() && j == this.startPosition.getColumnIndex()) {
                    System.out.print(" \u001b[44m ");
                } else if (i == this.endPosition.getRowIndex() && j == this.endPosition.getColumnIndex()) {
                    System.out.print(" \u001b[44m ");
                } else if (this.maze[i][j] == 1) {
                    System.out.print(" \u001b[45m ");
                } else {
                    System.out.print(" \u001b[107m ");
                }
            }

            System.out.println(" \u001b[107m");
        }
        System.out.println("\u001b[31m\uD83D\uDC99\uD83D\uDC99\uD83D\uDC99 liad is the qween \uD83E\uDDDC\u200D \uD83D\uDC99\uD83D\uDC99\uD83D\uDC99 \u001b[0m");
        System.out.println();
    }


//    public void print3(Maze M, Solution mysol2){
//
//        for(int i = 0; i < M.maze.length; ++i) {
//            for(int j = 0; j < M.maze[i].length; ++j) {
//
//                if (i == M.getStartPosition().getRowIndex() && j == M.getStartPosition().getColumnIndex()) {
//                    System.out.print(" \u001b[46m"+"S");
//                } else if (i == M.getGoalPosition().getRowIndex() && j == M.getGoalPosition().getColumnIndex()) {
//                    System.out.print(" \u001b[46m"+"E");
//                } else if ( M.maze[i][j] == 1) {
//                    System.out.print(" \u001b[40m ");
//                }
//                else {
//                    for(int x=0 ;x<mysol2.getSolutionPath().size() ;x++){
//                        AState try2 = mysol2.getSolutionPath().get(x);
//                        MazeState tryM = (MazeState) try2;
//                        if(tryM.getPos().getRowIndex() ==i && tryM.getPos().getColumnIndex()==j) {
//                            System.out.print(" \u001b[43m ");
//                            break;
//                        }
//                        else if(x==mysol2.getSolutionPath().size()-1)
//                            System.out.print(" \u001b[107m ");
//                    }
//
//                }
//            }
//
//            System.out.println(" \u001b[107m");
//        }
//        System.out.println("");
//        System.out.println();
//    }
}
