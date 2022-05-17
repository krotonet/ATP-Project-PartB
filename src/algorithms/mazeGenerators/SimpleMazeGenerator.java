package algorithms.mazeGenerators;

import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator{
    static Random random = new Random();

    /**
     *  maze generated by randomly putting wall or path in the maze
     * @param rows
     * @param columns
     * @return Maze generated simply
     */
    @Override
    public Maze generate(int rows, int columns){
        Maze myMaze = new Maze(rows,columns);
        Map<Integer, List<Integer>> positions = new HashMap<Integer, List<Integer>>();
        myMaze.setStartPosition(new Position(0,0));
        myMaze.setEndPosition(new Position(rows - 1, columns - 1));

        //save solution
        //maybe make the decision of which way to go randomly
        setPath(myMaze,myMaze.getStartPosition().getColumnIndex(),myMaze.getStartPosition().getRowIndex());

        //set false path
        setPath(myMaze,columns / 2,myMaze.getStartPosition().getRowIndex());

//        setPath(myMaze, myMaze.getStartPosition().getColumnIndex(),rows / 2, rows, columns);
//        setPath(myMaze, random.nextInt(rows), random.nextInt(columns), rows, columns);


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


    public Maze generate2(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        maze.setMaze(0);
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

    /**
     * generate a random path to cross the main path
     * the goal here is to get more possible solutions
     * @param maze
     * @param currentColumn , from which column to start the path
     * @param currentRow , from which row to start the path
     */
    private static void setPath(Maze maze, int currentColumn, int currentRow){
        //put ones in the maze
        //then check if it 0 then random
        //else continue
        int decision = random.nextInt(10);
        while(currentColumn != maze.getColumns() && currentRow != maze.getRows()){
            if(decision % 2 == 0){//move row + 1
                maze.setValue(currentRow++,currentColumn,1);
                if(currentRow + 1 < maze.getRows())
                    maze.setValue(currentRow++,currentColumn,1);
            }
            else {
                if(currentColumn + 1 < maze.getColumns())
                    maze.setValue(currentRow, currentColumn++, 1);
            }
            decision = random.nextInt(10);
        }

        while(currentRow != maze.getRows()){
            maze.setValue(currentRow++, currentColumn - 1, 1);
        }
        while(currentColumn != maze.getColumns()){
            maze.setValue(currentRow - 1, currentColumn++, 1);
        }
    }
}
