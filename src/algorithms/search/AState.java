package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.Objects;
import java.util.ArrayList;

public abstract class AState {
    private int cost=0;
    protected Position position;
    private AState foundBy;
//    public AState(){
//        this.cost=0;
//    }

    public AState(Position pos){
        this.position = pos;
        foundBy =null;
    }
//    public AState(int _cost){
//        this.cost=_cost;
//    }

    public Position getPosition(){
        return this.position;
    }

    public void addToCost(int _cost){
        this.cost+=_cost;
    }

    public void updateCost(int _cost){
        this.cost=_cost;
    }

    public int getCost(){
        return this.cost;
    }

    public void setFoundBy(AState curr){
        this.foundBy=curr;
    }
    public boolean equals(Object other) {
        if (other==null)
            return false;
        return this.position.equals(other);

    }

    public String toString(){
        return this.position.toString();
    }

    public AState getFoundBy(){
        return foundBy;
    }

}
