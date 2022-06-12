package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    private static Properties properties = new Properties();
    private static InputStream input = null;
    private static Configurations single_instance = null;

    private Configurations() {
        try {
            String filename = "resources\\config.properties";
            input = new FileInputStream(filename);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return an instance of configurations
     * creating new if not initialized
     */
    public static Configurations getInstance() {
        if (single_instance == null) {
            single_instance = new Configurations();
        }
        return single_instance;
    }

    /**
     *
     * @return how many threads we will use
     */
    public int getThreadPoolSize() {
        String thread_size = properties.getProperty("threadPoolSize");
        int size = Integer.parseInt(thread_size);
        if (size > 0) {
            return size;
        } else
            return 1;//default size of 1 thread.
    }


    /**
     * @return the type of searching algorithm
     */
    public ASearchingAlgorithm getMazeSearchingAlgorithm() {
        String maze_search_name = properties.getProperty("mazeSearchingAlgorithm");
        if (maze_search_name.equals("BreadthFirstSearch")) {
            return new BreadthFirstSearch();
        } else if (maze_search_name.equals("DepthFirstSearch")) {
            return new DepthFirstSearch();
        } else
            return new BestFirstSearch(); // default
    }

    /**
     * @return the maze algorithm we will use
     */
    public AMazeGenerator getMazeGeneratingAlgorithm() {
        String maze_generate_name = properties.getProperty("mazeGeneratingAlgorithm");
        if (maze_generate_name.equals("SimpleMazeGenerator")) {
            return new SimpleMazeGenerator();
        } else
            return new MyMazeGenerator();
    }

}

