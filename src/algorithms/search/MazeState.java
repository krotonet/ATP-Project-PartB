package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    protected Position position;

    public MazeState(Position pos, AState foundBy, int cost){
        this.position = pos;
        this.foundBy = foundBy;
        this.cost = cost;
    }

    public Position getPosition(){
        return this.position;
    }

    @Override
    public boolean equals(Object other) {
        Position thisPosition = (Position) this.position;
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
