package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numOfNodes;

    public ASearchingAlgorithm(){
        this.numOfNodes=0;
    }

    public String getNumberOfNodesEvaluated(){
        return String.valueOf(this.numOfNodes);
    }


}
