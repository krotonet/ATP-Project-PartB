package algorithms.search;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BestFirstSearchTest {
    private static ISearchable LShapeSolution;
    private static ISearchable chooseDiagonalOnEmptyMaze;
    private static ISearchable cantSolveInDiagonal;
    private static ISearchable chooseDiagonalSolution;
    private static ISearchable chooseStraightOverDiagonalStep;


    private static final ISearchingAlgorithm bfs = new BestFirstSearch();
    private final BestFirstSearch bestFirstSearch = new BestFirstSearch();
    private static Solution solutionPath;
    private static Maze maze;


    @BeforeAll
    static void beforeAll() {
        Position startPosition = new Position(0,0);
        Position goalPosition = new Position(4,4);
        maze = getMazeForTest(5,5, startPosition, goalPosition);

        //solution on the maze frontiers
        maze.initializeMaze(1);
        for(int i = 0; i < maze.getRows(); i++){
            maze.breakWall(i,0);
        }
        for(int i = 0; i < maze.getColumns(); i++){
            maze.breakWall(maze.getRows() - 1, i);
        }
        LShapeSolution = new SearchableMaze(maze);

        maze = getMazeForTest(5,5, startPosition, goalPosition);
        maze.initializeMaze(1);
        //two possible Solutions
        for(int i = 0; i < maze.getRows(); i++){
            maze.breakWall(i, 0);
            maze.breakWall(i, maze.getColumns() - 1);
        }
        for(int i = 0; i < maze.getColumns(); i++){
            maze.breakWall(maze.getRows() - 1, i);
            maze.breakWall(0, i);
        }

        //test if algorithm chooses the diagonal
        for(int i = 0; i < maze.getRows(); i++){
            maze.breakWall(i, i);
        }
        cantSolveInDiagonal = new SearchableMaze(maze);

        maze = getMazeForTest(5,5, startPosition, goalPosition);
        chooseDiagonalOnEmptyMaze = new SearchableMaze(maze);

        maze.initializeMaze(1);
        for(int i = 0; i < maze.getRows(); i++){
            for(int j = 0; j < maze.getColumns() && i >= j; j++){
                maze.breakWall(i,j);
            }
        }
        chooseDiagonalSolution = new SearchableMaze(maze);

        startPosition = new Position(0, 2);
        goalPosition = new Position(4,2);
        maze = getMazeForTest(5,5, startPosition, goalPosition);
        maze.initializeMaze(1);
        for(int i = 0; i < maze.getRows(); i++){
            maze.breakWall(i,2);
        }
        maze.breakWall(2, 1);
        chooseStraightOverDiagonalStep = new SearchableMaze(maze);
    }

    public static Maze getMazeForTest(int rows, int columns, Position startPosition, Position goalPosition){
        maze = new Maze(rows,columns);
        maze.setStartPosition(startPosition);
        maze.setGoalPosition(goalPosition);
        return maze;
    }

    @Test
    void finalSolution() {
        IMazeGenerator mg = new MyMazeGenerator();
        maze = mg.generate(5, 5);
        ISearchable DFSMaze = new SearchableMaze(maze);
        solutionPath = bfs.solve(DFSMaze);
        assertNotEquals(solutionPath.getSolutionPath(),null);
    }

    @Test
    void LShapeSolutionTest() {
        ArrayList<AState> expectedSolution = new ArrayList<>();
        MazeState state0 = new MazeState((new Position(0, 0)),null);
        MazeState state1 = new MazeState((new Position(1, 0)), state0);
        MazeState state2 = new MazeState((new Position(2, 0)), state1);
        MazeState state3 = new MazeState((new Position(3, 0)), state2);
        MazeState state4 = new MazeState((new Position(4, 1)), state3);
        MazeState state5 = new MazeState((new Position(4, 2)), state4);
        MazeState state6 = new MazeState((new Position(4, 3)), state5);
        MazeState state7 = new MazeState((new Position(4, 4)), state6);
        expectedSolution.add(0, state0);
        expectedSolution.add(1, state1);
        expectedSolution.add(2, state2);
        expectedSolution.add(3, state3);
        expectedSolution.add(4, state4);
        expectedSolution.add(5, state5);
        expectedSolution.add(6, state6);
        expectedSolution.add(7, state7);
        solutionPath = bestFirstSearch.solve(LShapeSolution);
        for(int i = 0; i < solutionPath.getSolutionPath().size(); i++){
            assertEquals(expectedSolution.get(i), solutionPath.getSolutionPath().get(i));
        }
        assertEquals(75,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }

    @Test
    void chooseDiagonalOnEmptyMazeTest() {
        ArrayList<AState> expectedSolution = new ArrayList<>();
        MazeState state0 = new MazeState((new Position(0, 0)),null);
        MazeState state1 = new MazeState((new Position(1, 1)), state0);
        MazeState state2 = new MazeState((new Position(2, 2)), state1);
        MazeState state3 = new MazeState((new Position(3, 3)), state2);
        MazeState state4 = new MazeState((new Position(4, 4)), state3);
        expectedSolution.add(0, state0);
        expectedSolution.add(1, state1);
        expectedSolution.add(2, state2);
        expectedSolution.add(3, state3);
        expectedSolution.add(4, state4);
        solutionPath = bestFirstSearch.solve(chooseDiagonalOnEmptyMaze);
        for(int i = 0; i < solutionPath.getSolutionPath().size(); i++){
            assertEquals(expectedSolution.get(i), solutionPath.getSolutionPath().get(i));
        }
        assertEquals(60,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }
    @Test
    void cantSolveInDiagonalTest() {
        MazeState state0 = new MazeState((new Position(0, 0)),null);
        MazeState state1 = new MazeState((new Position(1, 1)), state0);
        MazeState state2 = new MazeState((new Position(2, 2)), state1);
        MazeState state3 = new MazeState((new Position(3, 3)), state2);
        MazeState state4 = new MazeState((new Position(4, 4)), state3);

        solutionPath = bestFirstSearch.solve(cantSolveInDiagonal);

        assertFalse(solutionPath.getSolutionPath().contains(state2));

        assertEquals(75,(solutionPath.getSolutionPath().get(solutionPath.getFinalSolutionSize() - 1).getCost()));
    }

    @Test
    void chooseDiagonalSolutionTest() {
        ArrayList<AState> expectedSolution = new ArrayList<>();
        MazeState state0 = new MazeState((new Position(0, 0)),null);
        MazeState state1 = new MazeState((new Position(1, 1)), state0);
        MazeState state2 = new MazeState((new Position(2, 2)), state1);
        MazeState state3 = new MazeState((new Position(3, 3)), state2);
        MazeState state4 = new MazeState((new Position(4, 4)), state3);
        expectedSolution.add(0, state0);
        expectedSolution.add(1, state1);
        expectedSolution.add(2, state2);
        expectedSolution.add(3, state3);
        expectedSolution.add(4, state4);
        solutionPath = bestFirstSearch.solve(chooseDiagonalSolution);
        for(int i = 0; i < solutionPath.getSolutionPath().size(); i++){
            assertEquals(expectedSolution.get(i), solutionPath.getSolutionPath().get(i));
        }
        assertEquals(60,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }

    @Test
    void chooseStraightOverDiagonalStepTest() {
        ArrayList<AState> expectedSolution = new ArrayList<>();
        MazeState state0 = new MazeState((new Position(0, 2)),null);
        MazeState state1 = new MazeState((new Position(1, 2)), state0);
        MazeState state2 = new MazeState((new Position(2, 2)), state1);
        MazeState state3 = new MazeState((new Position(3, 2)), state2);
        MazeState state4 = new MazeState((new Position(4, 2)), state3);
        expectedSolution.add(0, state0);
        expectedSolution.add(1, state1);
        expectedSolution.add(2, state2);
        expectedSolution.add(3, state3);
        expectedSolution.add(4, state4);
        solutionPath = bestFirstSearch.solve(chooseStraightOverDiagonalStep);
        for(int i = 0; i < solutionPath.getSolutionPath().size(); i++){
            assertEquals(expectedSolution.get(i), solutionPath.getSolutionPath().get(i));
        }
        MazeState missingState = new MazeState((new Position(2, 1)), state3);
        assertFalse(solutionPath.getSolutionPath().contains(missingState));
        assertEquals(40, solutionPath.getSolutionPath().get(4).getCost());
    }
}