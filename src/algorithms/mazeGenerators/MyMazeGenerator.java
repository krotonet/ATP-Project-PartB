package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    Random random = new Random();

    /**
     *
     * @param rows
     * @param columns
     * @return Maze, generated by DFS algorithm
     */
    @Override
    public Maze generate(int rows, int columns) {
        int endRow = 0, endColumn = 0;
        int maxStackSize = 0;

        //initialize maze, grid full of walls
        Maze maze = new Maze(rows, columns);
        maze.setMaze(1);

        //draw starting position on one of the sides
        Position startPosition = getRandomStartPosition(rows, columns);

        //draw starting position on any spot on the grid
//        Position startPosition = new Position(random.nextInt(rows), random.nextInt(columns) );
        maze.setStartPosition(startPosition);
        maze.setValue(startPosition.getRowIndex(),startPosition.getColumnIndex(),0);
        //push starting cell to the stack
        Stack<Position> cells = new Stack<>();
        cells.push(maze.getStartPosition());

        //add walls of starting cell to a list
        List<Position> walls = new ArrayList<Position>();
        addCellsWalls(maze, walls, startPosition);


        while(!cells.isEmpty()){
            Position currentCell = cells.pop();

            //get current's cell neighbors
            List<Position> neighbors = getNeighbors(maze,currentCell);

            //current cell has neighbours which have not been visited
            if(!neighbors.isEmpty()){
                cells.push(currentCell);

                //randomly choose wall of current cell
                Position unvisited = getRandomWall(neighbors);
                //break wall between current and the next cell
                breakWall(maze, currentCell, unvisited);
                maze.setValue(unvisited.getRowIndex(), unvisited.getColumnIndex(), 0);
                cells.push(unvisited);
            }
            //finding the end position
            if (cells.size() > maxStackSize) {
                maxStackSize = cells.size();
                endRow = currentCell.getRowIndex();
                endColumn = currentCell.getColumnIndex();
            }
        }
        maze.setEndPosition(new Position(endRow, endColumn));

        return maze;
    }

    /**
     * break the proper wall between current cell and next cell
     * @param maze, main maze
     * @param currentCell, current cell in the maze
     * @param nextCell, cell to go forward
     */
    public void breakWall(Maze maze,Position currentCell, Position nextCell){
        //move on the same row
        if(nextCell.getRowIndex() == currentCell.getRowIndex()){
            //check if the move was to go right, else left
            if(nextCell.getColumnIndex() - currentCell.getColumnIndex() > 0)
                maze.setValue(currentCell.getRowIndex(), currentCell.getColumnIndex() + 1, 0);
            else
                maze.setValue(currentCell.getRowIndex(), currentCell.getColumnIndex() - 1, 0);
        }
        //move on the same column
        else{
            //check if the move was to go up, else down
            if(currentCell.getRowIndex() - nextCell.getRowIndex() > 0)
                maze.setValue(currentCell.getRowIndex() - 1, currentCell.getColumnIndex(), 0);
            else
                maze.setValue(currentCell.getRowIndex() + 1, currentCell.getColumnIndex(), 0);
        }
    }

    /**
     * generate random start position on the frontier
     * @param rows
     * @param columns
     * @return
     */
    public Position getRandomStartPosition(int rows, int columns){
        Position startPosition = null;
        int randomSide = random.nextInt(4) + 1;
        switch (randomSide) {
            //start from left side
            case 1:
                startPosition = new Position(random.nextInt(rows), 0);
                break;

            //start from upper side
            case 2:
                startPosition = new Position(0, random.nextInt(columns));
                break;

            //start from right side
            case 3:
                startPosition = new Position(random.nextInt(rows), columns - 1);
                break;

            //start from bottom
            case 4:
                startPosition = new Position(rows - 1, random.nextInt(columns));
                break;
            default:
                randomSide = random.nextInt(5);
        }
        return startPosition;
    }

    /**
     * creates list of neighbors for current cell
     * @param maze, current maze
     * @param currentCell, current cell
     * @return List of Positions
     */
    public List<Position> getNeighbors(Maze maze, Position currentCell){
        List<Position> cells = new LinkedList<>();
        if(currentCell.getColumnIndex() - 2 >= 0 && maze.getValue(currentCell.getRowIndex(),currentCell.getColumnIndex() - 2) == 1) {
            cells.add(new Position(currentCell.getRowIndex(),currentCell.getColumnIndex() - 2));
        }
        if(currentCell.getColumnIndex() + 2 < maze.getColumns() && maze.getValue(currentCell.getRowIndex(),currentCell.getColumnIndex() + 2) == 1) {
            cells.add(new Position(currentCell.getRowIndex(),currentCell.getColumnIndex() + 2));
        }
        if(currentCell.getRowIndex() - 2 >= 0 && maze.getValue(currentCell.getRowIndex() - 2, currentCell.getColumnIndex()) == 1) {
            cells.add(new Position(currentCell.getRowIndex() - 2,currentCell.getColumnIndex()));
        }
        if(currentCell.getRowIndex() + 2 < maze.getRows() && maze.getValue(currentCell.getRowIndex() + 2, currentCell.getColumnIndex()) == 1) {
            cells.add(new Position(currentCell.getRowIndex() + 2,currentCell.getColumnIndex()));
        }

        return cells;
    }

    /**
     * selects random wall for potential step
     * @param walls , List of Positions walls of current cell
     * @return Position
     */
    public Position getRandomWall(List<Position> walls){
        return walls.get(random.nextInt(walls.size()));
    }



    /**
     * try of prim algorithm
     *
     *
     *
     *
     *
     *
     * @param maze
     * @param neighbors
     * @return
     */
    public int sumNeighboringCells(Maze maze, List<Position> neighbors){
        int sum = 0;
        for(Position p: neighbors)
            sum += maze.getValue(p.getRowIndex(),p.getColumnIndex());
        return sum;
    }


    public void addCellsWalls(Maze maze, List<Position> walls, Position cell){
        if (cell.getColumnIndex() + 1 < maze.getColumns() && maze.getValue(cell.getRowIndex(), cell.getColumnIndex() + 1) == 1){
            walls.add(new Position(cell.getRowIndex(), cell.getColumnIndex() + 1));
        }
        if (cell.getColumnIndex() - 1 >= 0 && maze.getValue(cell.getRowIndex(), cell.getColumnIndex() - 1) == 1) {
            walls.add(new Position(cell.getRowIndex(), cell.getColumnIndex() - 1));
        }
        if (cell.getRowIndex() + 1 < maze.getRows() && maze.getValue(cell.getRowIndex() + 1, cell.getColumnIndex()) == 1) {
            walls.add(new Position(cell.getRowIndex() + 1, cell.getColumnIndex()));
        }
        if (cell.getRowIndex() - 1 >= 0 && maze.getValue(cell.getRowIndex() - 1, cell.getColumnIndex()) == 1) {
            walls.add(new Position(cell.getRowIndex() - 1, cell.getColumnIndex()));
        }
    }


    public Maze generate1(int rows, int columns) {
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
            List<Position> neighbors = getWallNeighbors(maze, randomWall);
            if(sumNeighboringCells(maze, neighbors) == 1)
                maze.setValue(randomWall.getRowIndex(), randomWall.getColumnIndex(),0);
            addCellsWalls(maze, walls, randomWall);
            walls.remove(randomWall);
        }

        return maze;
    }




    public List<Position> getWallNeighbors(Maze maze, Position wall){
        List<Position> Neighbors = new ArrayList<Position>();

        if (wall.getRowIndex() % 2 == 0 && wall.getColumnIndex() % 2 != 0) {
            Neighbors.add( new Position(wall.getRowIndex(), wall.getColumnIndex() - 1) );

            if(wall.getColumnIndex() < maze.getColumns() - 1) {
                Neighbors.add(new Position(wall.getRowIndex(), wall.getColumnIndex() + 1));
            }
        }
        else if (wall.getColumnIndex() % 2 == 0 && wall.getRowIndex() % 2 != 0){
            Neighbors.add( new Position(wall.getRowIndex() - 1, wall.getColumnIndex()));

            if (wall.getRowIndex() < maze.getRows() - 1){
                Neighbors.add( new Position(wall.getRowIndex() + 1, wall.getColumnIndex()));
            }
        }
        return Neighbors;
    }
}