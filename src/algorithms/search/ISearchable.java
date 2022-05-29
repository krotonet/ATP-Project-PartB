package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;

public interface ISearchable {

    AState getStart();

    AState getGoal();

    ArrayList<AState> getAllPossibleStates(AState curr);

    Maze getMaze();
}
