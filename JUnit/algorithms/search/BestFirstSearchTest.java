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
    private static ISearchable onlyDiagonalMaze;
    private static ISearchable oneDiagonalOneLSolution;

    private static final IMazeGenerator mg = new MyMazeGenerator();
    private static final ISearchingAlgorithm bfs = new BestFirstSearch();

    @BeforeAll
    static void beforeAll() {
        Maze maze = mg.generate(5, 5);
        DFSMaze = new SearchableMaze(maze);

//        maze.initializeMaze(1);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(maze.getRows() - 1, maze.getColumns() - 1));
        for(int i = 0; i < maze.getRows(); i++){
            maze.setValue(i, 0, 0);
        }
        for(int i = 0; i < maze.getColumns(); i++){
            maze.setValue(maze.getRows() - 1, i, 0);
        }
        noDiagonalMaze = new SearchableMaze(maze);


        for (int i = 0; i < maze.getRows(); i++){
            for (int j = 0; j < maze.getColumns(); j++){
                maze.setValue(i, j, 0);
            }
        }
        oneDiagonalOneLSolution = new SearchableMaze(maze);


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
        Solution solutionPath = new Solution(noDiagonalMaze.getStart());
        assertEquals(80,(solutionPath.getSolutionPath().get(4).getCost()));
    }

    @Test
    void oneDiagonalOneLSolutionTest() {
        Solution solutionPath = new Solution(noDiagonalMaze.getStart());
        assertEquals(60,(solutionPath.getSolutionPath().get(4).getCost()));
    }

}