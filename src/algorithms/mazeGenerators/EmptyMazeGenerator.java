package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator{

    /**
     * create an empty maze with no walls at all.
     * @param rows , of the maze generated
     * @param columns , of the maze generated
     * @return generated empty maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows,columns);
        maze.initializeMaze(0);
        Random random = new Random();
        int positionRow = random.nextInt(rows);
        int positionColumn = random.nextInt(columns);

        maze.setStartPosition(new Position(positionRow,positionColumn));
        while(positionRow == maze.getStartPosition().getRowIndex() && positionColumn == maze.getStartPosition().getColumnIndex())
        {
            positionRow = random.nextInt(rows);
            positionColumn = random.nextInt(columns);
        }
        maze.setGoalPosition(new Position(positionRow,positionColumn));

        return maze;
    }
}
