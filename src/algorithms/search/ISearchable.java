package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;


public interface ISearchable {


    public AState getStart();
    public AState getEnd();

    ArrayList<AState> getPossibleStates(AState curr);
    public int getRowMaze();
    public int getColMaze();

    public Maze getMaze();
}
