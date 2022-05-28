package algorithms.search;
//package algorithms.mazeGenerators;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.security.InvalidParameterException;

public class BestFirstSearchTest {
    ISearchingAlgorithm bfs = new BestFirstSearch();
    IMazeGenerator mg = new MyMazeGenerator();
    Maze maze = mg.generate(20,20);
    ISearchable MazeSearch = new SearchableMaze(maze);
    Solution sol = bfs.solve(MazeSearch);
    @Test
    void checkSol(){
        assertNotEquals( sol ,null);
    }

    @Test
    void getName() {
        assertEquals(bfs.getName(),"BestFirstSearch");
    }

    @Test
    void finalSolution() {
        assertNotEquals(sol.getSolutionPath(),null);
    }
}
