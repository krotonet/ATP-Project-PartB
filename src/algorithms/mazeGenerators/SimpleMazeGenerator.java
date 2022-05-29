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
        myMaze.setStartPosition(new Position(0,0));
        myMaze.setEndPosition(new Position(rows - 1, columns - 1));

        //save solution
        //maybe make the decision of which way to go randomly
        setPath(myMaze, myMaze.getStartPosition().getRowIndex(), myMaze.getStartPosition().getColumnIndex());

        //set false paths
        setPath(myMaze, myMaze.getStartPosition().getRowIndex(),columns / 2);
        setPath(myMaze, rows / 2, myMaze.getStartPosition().getColumnIndex());
        setPath(myMaze, random.nextInt(rows), random.nextInt(columns));

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

    /**
     * generate a random path to cross the main path
     * the goal here is to get more possible solutions in maze
     * @param maze
     * @param currentColumn , from which column to start the path
     * @param currentRow , from which row to start the path
     */
    private static void setPath(Maze maze, int currentRow, int currentColumn){
        //generate random number to decide random progress int maze
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

        //if path didn't end in the end row position
        while(currentRow != maze.getRows()){
            maze.setValue(currentRow++, currentColumn - 1, 1);
        }
        //getting column end position
        while(currentColumn != maze.getColumns()){
            maze.setValue(currentRow - 1, currentColumn++, 1);
        }
    }
}
