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
    private static ISearchable noDiagonalSolution;
    private static ISearchable chooseDiagonalOnEmptyMaze;
    private static ISearchable chooseDiagonal;
    private static ISearchable twoPossibleSolutions;


    private static final ISearchingAlgorithm bfs = new BestFirstSearch();
    private final BestFirstSearch bestFirstSearch = new BestFirstSearch();
    private static Solution solutionPath;
    private static Maze maze;


    @BeforeAll
    static void beforeAll() {
        maze = new Maze(5,5);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(maze.getRows()-1, maze.getColumns()-1));

        //solution on the maze frontiers
        maze.initializeMaze(1);
        for(int i = 0; i < maze.getRows(); i++){
            maze.setValue(i, 0, 0);
        }
        for(int i = 0; i < maze.getColumns(); i++){
            maze.setValue(maze.getRows() - 1, i, 0);
        }
        noDiagonalSolution = new SearchableMaze(maze);

        //test if algorithm chooses the diagonal
        for(int i = 0; i < maze.getRows(); i++){
            maze.setValue(i, i, 0);
        }
        chooseDiagonal = new SearchableMaze(maze);


        maze = new Maze(5,5);
        maze.initializeMaze(1);
        maze.setStartPosition(new Position(0,0));
        maze.setGoalPosition(new Position(maze.getRows()-1, maze.getColumns()-1));
        for (int i = 0; i < maze.getRows(); i++){
            for (int j = 0; j < maze.getColumns(); j++){
                maze.setValue(i, j, 0);
            }
        }
        chooseDiagonalOnEmptyMaze = new SearchableMaze(maze);

        twoPossibleSolutions = new SearchableMaze(maze);
    }

    @Test
    void getName() {
        assertEquals(bfs.getName(),"BestFirstSearch");
    }

    @Test
    void finalSolution() {
        IMazeGenerator mg = new MyMazeGenerator();
        maze = mg.generate(5, 5);
        DFSMaze = new SearchableMaze(maze);
        solutionPath = bfs.solve(DFSMaze);
        assertNotEquals(solutionPath.getSolutionPath(),null);
    }

    @Test
    void noDiagonalSolutionTest() {
        solutionPath = bestFirstSearch.solve(noDiagonalSolution);
        assertEquals(75,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }

    @Test
    void chooseDiagonalOnEmptyMazeTest() {
        solutionPath = bestFirstSearch.solve(chooseDiagonalOnEmptyMaze);
        assertEquals(60,(solutionPath.getSolutionPath().get(solutionPath.getSolutionPath().size() - 1).getCost()));
    }
    @Test
    void chooseDiagonalTest() {
        solutionPath = bestFirstSearch.solve(chooseDiagonal);
        assertEquals(45,(solutionPath.getSolutionPath().get(4).getCost()));
    }

    @Test
    void twoPossibleSolutionsTest() {

    }
}