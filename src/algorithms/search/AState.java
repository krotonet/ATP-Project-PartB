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

    public static int compareStates(AState o1, AState o2) {
        if(o1 == null || o2 == null) throw new NullPointerException();
        if(o1.getCost() > o2.getCost()) return 1;
        else if(o1.getCost() < o2.getCost()) return -1;
        return 0;
    }
    public abstract boolean equals(Object other);
    
    public abstract int hashCode();

}
