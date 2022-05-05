package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows,columns);

        Random random = new Random();
        int rowP = random.nextInt(rows);
        int colP = random.nextInt(columns);

        maze.setStartPosition(new Position(rowP,colP));
        while(rowP == maze.getStartPosition().getRowIndex() && colP == maze.getStartPosition().getColumnIndex())
        {
            rowP = random.nextInt(rows);
            colP = random.nextInt(columns);
        }
        maze.setEndPosition(new Position(rowP,colP));

        return maze;
    }
}
