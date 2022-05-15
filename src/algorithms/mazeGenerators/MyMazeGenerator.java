package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{
    Random random = new Random();

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        maze.setMaze(1);

        //*******set start position, on the boundaries or randomly************
        Position startPosition = new Position(0,0);
        maze.setStartPosition(startPosition);
        maze.setValue(startPosition.getRowIndex(),startPosition.getColumnIndex(),0);

        List<Position> walls = new ArrayList<Position>();
        addCellsWalls(maze, walls, startPosition);

        while(!walls.isEmpty()){
            Position randomWall = getRandomWall(walls);
            List<Position> neighbors = new ArrayList<Position>();
            getCellsNeighbors(maze, neighbors, randomWall);
            if(sumNeighboringCells(maze, neighbors) == 1)
                maze.setValue(randomWall.getRowIndex(), randomWall.getColumnIndex(),0);
            addCellsWalls(maze, walls, randomWall);
            walls.remove(randomWall);
        }



        return maze;
    }

    public Position getRandomWall(List<Position> walls){
        return walls.get(random.nextInt(walls.size()));
    }

    public int sumNeighboringCells(Maze maze, List<Position> neighbors){
        int sum = 0;
        for(Position p: neighbors)
            sum += maze.getValue(p.getRowIndex(),p.getColumnIndex());
        return sum;
    }


    public void addCellsWalls(Maze maze, List<Position> walls, Position cell){
//        maze.breakWall(cell.getRowIndex(), cell.getColumnIndex());
        if (cell.getColumnIndex() + 1 < maze.getColumns()){
            walls.add(new Position(cell.getRowIndex(), cell.getColumnIndex() + 1));
        }
        if (cell.getColumnIndex() - 1 >= 0) {
            walls.add(new Position(cell.getRowIndex(), cell.getColumnIndex() - 1));
        }
        if (cell.getRowIndex() + 1 < maze.getRows()) {
            walls.add(new Position(cell.getRowIndex() + 1, cell.getColumnIndex()));
        }
        if (cell.getRowIndex() - 1 >= 0) {
            walls.add(new Position(cell.getRowIndex() - 1, cell.getColumnIndex()));
        }
    }


    public void getCellsNeighbors(Maze maze, List<Position> walls, Position cell){
        if (cell.getRowIndex() % 2 == 0 && cell.getColumnIndex() % 2 != 0) {
            walls.add( new Position(cell.getRowIndex(), cell.getColumnIndex() - 1) );

            if(cell.getColumnIndex() < maze.getColumns() - 1) {
                walls.add(new Position(cell.getRowIndex(), cell.getColumnIndex() + 1));
            }
        }
        else if (cell.getColumnIndex() % 2 == 0 && cell.getRowIndex() % 2 != 0){
            walls.add( new Position(cell.getRowIndex() - 1, cell.getColumnIndex()));

            if (cell.getRowIndex() < maze.getRows() - 1){
                walls.add( new Position(cell.getRowIndex() + 1, cell.getColumnIndex()));
            }
        }
    }
}
