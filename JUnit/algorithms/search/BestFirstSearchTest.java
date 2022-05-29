package algorithms.search;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BestFirstSearchTest {
    private static ISearchable DFSMaze;

    private static ISearchable noDiagonalMaze;
    private static ISearchable NoWallsOnlyDiagonal;

    private static final IMazeGenerator mg = new MyMazeGenerator();
    private static final ISearchingAlgorithm bfs = new BestFirstSearch();
    private BestFirstSearch bestFirstSearch = new BestFirstSearch();

    @BeforeAll
    static void beforeAll() {
        Maze maze = mg.generate(5, 5);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(maze.getRows()-1, maze.getColumns()-1));
        DFSMaze = new SearchableMaze(maze);

        maze.initializeMaze(1);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(maze.getRows() - 1, maze.getColumns() - 1));
        for(int i = 0; i < maze.getRows(); i++){
            maze.setValue(i, 0, 0);
        }
        for(int i = 0; i < maze.getColumns(); i++){
            maze.setValue(maze.getRows() - 1, i, 0);
        }

        noDiagonalMaze = new SearchableMaze(maze);

        maze = new Maze(5,5);
        maze.initializeMaze(1);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(maze.getRows()-1, maze.getColumns()-1));
        for (int i = 0; i < maze.getRows(); i++){
            for (int j = 0; j < maze.getColumns(); j++){
                maze.setValue(i, j, 0);
            }
        }
        NoWallsOnlyDiagonal = new SearchableMaze(maze);


    }

    @Test
    void getName() {
        assertEquals(bfs.getName(),"BestFirstSearch");
    }

    @Test
    void finalSolution() {
        Solution solution = bfs.solve(DFSMaze);
        assertNotEquals(solution.getSolutionPath(),null);
    }

    @Test
    void noDiagonalMazeTest() {
        Solution solutionPath = bestFirstSearch.solve(noDiagonalMaze);
        assertEquals(75,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }

    @Test
    void NoWallsDiagonalSolution() {
        Solution solutionPath = bestFirstSearch.solve(NoWallsOnlyDiagonal);
        assertEquals(60,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }

}