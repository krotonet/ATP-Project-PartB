package algorithms.mazeGenerators;

import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator{
    static Random random = new Random();
    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        Map<Integer,int[]> positions = new HashMap<Integer,int[]>();
        maze.setStartPosition(new Position(0,0));
        maze.setEndPosition(new Position(Math.min(rows, columns) - 1, Math.min(rows, columns) - 1));

        //save solution
        //maybe make the decision of which way to go randomly
        int c = 0;
        for(int i = 0; i < rows; i ++) {
            int[] temp = new int[2];
            if(i == 0) {
                temp[0] = i;
            }else {
                temp[0] = i - 1;
            }
            temp[1] = i;
            positions.put(i, temp);
        }


        //set walls in random cells
        Random random = new Random();
        for(int i = 0; i < rows; i ++){
            for(int j = 0; j < columns; j ++) {
                if(positions.containsKey(i) && (positions.get(i)[0] == j || positions.get(i)[1] == j)){
                    continue;
                }
                maze.setValue(i, j, random.nextInt(2));
            }
        }
        return maze;
    }
    public Maze generate2(int rows, int columns){
        Maze myMaze = new Maze(rows,columns);
        Map<Integer, List<Integer>> positions = new HashMap<Integer, List<Integer>>();
        myMaze.setStartPosition(new Position(0,0));
        myMaze.setEndPosition(new Position(Math.min(rows, columns) - 1, Math.min(rows, columns) - 1));

        //save solution
        //maybe make the decision of which way to go randomly
        setPath(myMaze,myMaze.getStartPosition().getColumnIndex(),myMaze.getStartPosition().getRowIndex(), rows, columns);

        //set false path
        setPath(myMaze,columns / 2,myMaze.getStartPosition().getRowIndex(), rows, columns);

        //set walls in random cells
        for(int i = 0; i < rows; i ++){
            for(int j = 0; j < columns; j ++) {
                if(myMaze.getValue(i,j) != 1)
                    myMaze.setValue(i, j, random.nextInt(2));
                else
                    myMaze.setValue(i, j, 0);
            }
        }
        return myMaze;
    }
    private static void setPath(Maze maze, int currentColumn, int currentRow, int row, int col){

        //put ones in the maze
        //then check if it 0 then random
        //else continue

        int decision = random.nextInt(10);
        while(currentColumn != col && currentRow != row){
            if(decision % 2 == 0){//move row + 1
                maze.setValue(currentRow++,currentColumn,1);
            }
            else {
                maze.setValue(currentRow, currentColumn++, 1);
            }
            if(decision % 3 == 0){
                maze.setValue(currentRow, currentColumn++, 1);
                maze.setValue(currentRow, currentColumn++, 1);
            }
            if(decision % 2 == 0){
                maze.setValue(currentRow++, currentColumn, 1);
                maze.setValue(currentRow++, currentColumn, 1);
            }
            decision = random.nextInt(10);
        }

        if(currentRow != row){
            while(currentRow != row){
                maze.setValue(currentRow++, currentColumn - 1, 1);
            }
        }else{
            while(currentColumn != col){
                maze.setValue(currentRow - 1, currentColumn++, 1);
            }
        }
    }
}
