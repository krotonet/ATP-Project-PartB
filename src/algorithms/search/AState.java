package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.Objects;
import java.util.ArrayList;

public abstract class AState {
    protected int cost = 0;
    protected AState foundBy;

    public AState(){
        foundBy = null;
    }

    public AState getFoundBy(){
        return foundBy;
    }

    public void setFoundBy(AState curr){
        this.foundBy=curr;
    }

    public abstract boolean equals(Object other);

}
