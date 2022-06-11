package Server;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.ISearchable;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    public ASearchingAlgorithm algo;
    private String tempDirectoryPath;


    //constructor.
    public ServerStrategySolveSearchProblem() {
        this.tempDirectoryPath = System.getProperty("java.io.tmpdir");
        this.algo = Configurations.mazeSearchingAlgorithm();
    }

    /**
     * the function read the maze from input stream, then try to find a solution`s file using maze hashcode.
     * if no such file then solve the maze and save the file.
     */
    @Override
    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);

            Maze myMaze = (Maze)fromClient.readObject();
            int mazeCode = myMaze.hashCode();
            String mazeCodeS = Integer.toString(mazeCode);

            Solution solve = findSolution(mazeCodeS);

            if(solve==null){ //if no solution
                ISearchable iSearchable = new SearchableMaze(myMaze);
                ASearchingAlgorithm algorithm = (ASearchingAlgorithm)algo.newAlgorithm();
                solve = algorithm.solve(iSearchable);
                save_sol(solve, mazeCodeS);
            }
            toClient.writeObject(solve); //write the solution to output stream.
            toClient.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * If we don't have a solution this save the solution.
     * @param sol is the solution we want to save
     * @param s hashcode that represent the maze
     */
    public void save_sol(Solution sol , String s) {

        File solFile = new File(tempDirectoryPath + "Solution - " + s);
        //System.out.println("solFile name is:" + solFile.getName()); //*****************************************************

        try {
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(solFile));
            objOut.writeObject(sol); //write sol into output stream.
            objOut.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The function search the maze using the maze hashcode.
     * If exists - returns the saved solution, otherwise return null.
     * @param mazeCodeS the maze hash code.
     * @return the solution of the maze
     */
    public Solution findSolution(String mazeCodeS) {

        Solution solved = null;
        String myPath = this.tempDirectoryPath + "Solution - " + mazeCodeS;

        if (Files.exists(Path.of(myPath))) {
            //System.out.println("find a solution!!!");
            ObjectInputStream input = null;
            try {
                File solFile = new File(myPath);
                input = new ObjectInputStream(new FileInputStream(solFile));
                solved = (Solution) input.readObject(); //read from the file stream
                input.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return solved;

        }
        return null;
    }

}



