package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;

public interface ISearchable {
    int lowPrice = 10, highPrice = 15;

    AState getStart();

    AState getGoal();

    ArrayList<AState> getAllPossibleStates(AState curr);

    Maze getMaze();
}
