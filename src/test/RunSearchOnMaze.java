package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze {
    static String[] evaluated = new String[3];
    static int[] solutionSize = new int[3];
    static int[] solutionCost = new int[3];
    static int[] bestSolution = new int[3];
    static int j = 0;
    public static void main(String[] args) {
        int bestSol = 0;
        IMazeGenerator mg = new MyMazeGenerator();
        int n = 1;
        for(int i = 0; i<n;i++) {
            Maze maze = mg.generate(100, 100);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());

            System.out.println("Breadth First Search: evaluated nodes: " + evaluated[0] +
                    ", solution size: " + solutionSize[0] + ", cost: " + solutionCost[0] + "\n");
            System.out.println("Depth First Search: evaluated nodes: " + evaluated[1] +
                    ", solution size: " + solutionSize[1] + ", cost: " + solutionCost[1] + "\n");
            System.out.println("Best First Search: evaluated nodes: " + evaluated[2] +
                    ", solution size: " + solutionSize[2] + ", cost: " + solutionCost[2] + "\n");
            j = 0;
        }
        int min = Math.min(Integer.parseInt(evaluated[0]),Integer.parseInt(evaluated[1]));
        min = Math.min(min,Integer.parseInt(evaluated[2]));
        for(int i=0;i<3;i++){
            if(min == Integer.parseInt(evaluated[i]))
                bestSolution[i]++;
        }
        System.out.println("Breadth " + bestSolution[0] + "Depth " + bestSolution[0] + "Best " + bestSolution[2]);
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        evaluated[j] = String.valueOf(searcher.getNumberOfNodesEvaluated());
        solutionSize[j] = solution.getFinalSolutionSize();
        solutionCost[j] = solution.getSolutionPath().get(solution.getFinalSolutionSize() - 1).getCost();

        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        //******************************** delete //******************************** delete

        domain.getMaze().printMazeWithSolution(solution);

        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();

        j++;
    }
}