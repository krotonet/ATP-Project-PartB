package algorithms.search;

public abstract class AState {
    protected int cost = 0;
    protected AState foundBy;

    public int getCost() {
        return cost;
    }

    public AState(){
        foundBy = null;
    }

    public AState getFoundBy(){
        return foundBy;
    }

    public abstract boolean equals(Object other);

}
