package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{
    public abstract Maze generate(int row, int column);

    /**
     * measure the time took to generate the maze
     * with generate() function
     * need to use System.currentTimeMillis()
     * @param row
     * @param column
     * @return the time took to generate the maze in milliseconds
     */
    public long measureAlgorithmTimeMillis(int row, int column){
        long time = System.currentTimeMillis();
        generate(row,column);
        time = System.currentTimeMillis() - time;
        System.out.println(time);
        return time;
    }
}
