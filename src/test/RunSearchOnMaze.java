//package test;
//import algorithms.mazeGenerators.IMazeGenerator;
//import algorithms.mazeGenerators.Maze;
//import algorithms.mazeGenerators.MyMazeGenerator;
//import algorithms.search.*;
//import java.util.ArrayList;
//public class RunSearchOnMaze {
//    public static void main(String[] args) {
//        IMazeGenerator mg = new MyMazeGenerator();
//        Maze maze = mg.generate(10, 10);
//        SearchableMaze searchableMaze = new SearchableMaze(maze);
//        solveProblem(searchableMaze, new BreadthFirstSearch());
//        solveProblem(searchableMaze, new DepthFirstSearch());
//        //solveProblem(searchableMaze, new BestFirstSearch());
//    }
//
//    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//        //Solve a searching problem with a searcher
//        Solution solution = searcher.solve(domain);
//        domain.getMaze().printMazeWithSolution(solution);
//        System.out.println(String.format("'%s' algorithm - nodes evaluated:%d", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//        //Printing Solution Path
//        System.out.println("Solution path:");
//        ArrayList<AState> solutionPath = solution.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++){
//            System.out.println(String.format("%s.%s.%d",i,solutionPath.get(i),solutionPath.get(i).getCost()));
//        }
//
//    }
//}
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

    static int j = 0;
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        String str = "";
        for (int i = 0; i < 3; i++) {
            if(i == 0)
                str+="Breadth First Search: ";
            if(i == 1)
                str+="Depth First Search: ";
            if(i == 2)
                str+="Best First Search: ";
            str += "evaluated nodes: " + evaluated[i] + ", solution size: " + solutionSize[i] + ", cost: "+ solutionCost[i] + "\n";
        }
        System.out.println(str);
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        evaluated[j] = searcher.getNumberOfNodesEvaluated();
        solutionSize[j] = solution.getFinalSolutionSize();
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        //******************************** delete //******************************** delete

//        domain.getMaze().printMazeWithSolution(solution);

        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
            solutionCost[j] += solutionPath.get(i).getCost();
        }
        j++;
    }
}