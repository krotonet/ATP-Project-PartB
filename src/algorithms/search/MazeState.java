package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    protected Position position;

    public MazeState(Position pos, AState foundBy){
        this.position = pos;
        this.foundBy = foundBy;
        this.cost = 0;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object other) {
        Position thisPosition = this.position;
        MazeState otherState = (MazeState)other;
        return thisPosition.equals(otherState.getPosition());
    }

    @Override
    public int hashCode() {
        return this.position.hashCode();
    }

    @Override
    public String toString() {
        return position.toString();
    }

}
