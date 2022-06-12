package Server;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private final String tempDirectoryPath;
    private final Configurations configurations = Configurations.getInstance();

    public ServerStrategySolveSearchProblem() {
        this.tempDirectoryPath = System.getProperty("java.io.tmpdir");
    }

    /**
     * the function read the maze from input stream, then try to find a solution`s file using maze hashcode.
     * if no such file then solve the maze and save the file.
     * @param inputStream ,client input
     * @param outputStream ,server response
     */
    @Override
    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);

            Maze mazeToSolve = (Maze)fromClient.readObject();

            Solution solve = findSolution(mazeToSolve);

            if(solve==null){ //if no solution
                ISearchable iSearchable = new SearchableMaze(mazeToSolve);
                ISearchingAlgorithm Search = configurations.getMazeSearchingAlgorithm();
                solve = Search.solve(iSearchable);
                save_sol(solve, mazeToSolve);
            }
            toClient.writeObject(solve); //write the solution to output stream.
            toClient.flush();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * If we don't have a solution this save the solution.
     * @param sol is the solution we want to save
     * @param mazeToSolve hashcode that represent the maze
     */
    public void save_sol(Solution sol , Maze mazeToSolve) {

        try {
            File solFile = new File(tempDirectoryPath + "Solution - " + mazeToSolve.toString().hashCode());
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(solFile));
            objOut.writeObject(sol); //write sol into output stream.
            objOut.flush();
            objOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The function search the maze using the maze hashcode.
     * If exists - returns the saved solution, otherwise return null.
     * @param mazeToSolve the maze hash code.
     * @return the solution of the maze
     */
    public Solution findSolution(Maze mazeToSolve) {
        Solution solved = null;
        String myPath = this.tempDirectoryPath + "Solution - " + mazeToSolve.toString().hashCode();

        try {
            if (Files.exists(Path.of(myPath))) {
                ObjectInputStream input;
                File solFile = new File(myPath);
                input = new ObjectInputStream(new FileInputStream(solFile));
                solved = (Solution) input.readObject(); //read from the file stream
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solved;
    }

}



