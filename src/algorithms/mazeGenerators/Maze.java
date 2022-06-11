package algorithms.mazeGenerators;

import java.io.Serializable;
import algorithms.search.AState;
import algorithms.search.MazeState;
import algorithms.search.Solution;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class Maze implements Serializable {
    private final int rows;
    private final int columns;
    private final int[][] maze;
    private Position startPosition;
    private Position goalPosition;

    /**
     * constructor
     * if any dimension lower or equal 2, we generate it as dimension 3.
     * @param rows
     * @param columns
     */
    public Maze(int rows, int columns){
        if (rows <= 2) rows = 3;
        if(columns <= 2 ) columns=3;
        this.rows = rows;
        this.columns = columns;
        this.maze = new int[rows][columns];
    }

    //copy constructor
    public Maze(Maze other){
        this.rows = other.getRows();
        this.columns = other.getColumns();
        this.startPosition = new Position(other.getStartPosition().getRowIndex(), other.getStartPosition().getColumnIndex());
        this.goalPosition = new Position(other.getGoalPosition().getRowIndex(), other.getGoalPosition().getColumnIndex());
        this.maze = new int[this.rows][this.columns];
    }

    /**
     * change each cell of the maze to the given value.
     * if value not 0 or 1, default value is 0.
     * @param value, initialize maze with the value sent, 1 or 0 only
     */
    public void initializeMaze(int value){
        if (!(value == 0  || value == 1)) value = 0;
        for(int[] array : this.maze){
            Arrays.fill(array, value);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Position getStartPosition(){
        return this.startPosition;
    }

    public Position getGoalPosition(){
        return this.goalPosition;
    }

    public void setStartPosition(Position newPosition) {
        this.startPosition = newPosition;
    }

    public void setGoalPosition(Position position) {
        this.goalPosition = position;
    }

    public void breakWall(int row, int columns){
        this.maze[row][columns] = 0;
    }

    public void buildWall(int row, int columns){
        this.maze[row][columns] = 1;
    }

    public int getValue(int row, int col){
        return this.maze[row][col];
    }
    public void print(){
        for(int y=0;y<rows;y++){
            for(int j=0;j<columns;j++){
                if(y==this.startPosition.getRowIndex() && j==this.startPosition.getColumnIndex()){
                    System.out.print("S");
                }
                else if(y==this.goalPosition.getRowIndex() && j==this.goalPosition.getColumnIndex()){
                    System.out.print("E");
                }
                else if (getValue(y, j)==0){
                    System.out.print("0");
                }
                else {
                    System.out.print(getValue(y, j));
                }
            }
            System.out.println("");
        }
    }

    /**
     * printing the maze in colors.
     * green is the start position
     * blue is goal position
     * purple is a wall
     */
    public void coloredPrint(){
        for(int i = 0; i < this.rows; ++i) {
            for(int j = 0; j < this.columns; ++j) {
                if (i == this.startPosition.getRowIndex() && j == this.startPosition.getColumnIndex()) {
                    System.out.print(" \u001B[42m ");
                } else if (i == this.goalPosition.getRowIndex() && j == this.goalPosition.getColumnIndex()) {
                    System.out.print(" \u001b[44m ");
                } else if (this.maze[i][j] == 1) {
                    System.out.print(" \u001b[45m ");
                } else {
                    System.out.print(" \u001b[107m ");
                }
            }
            System.out.println(" \u001b[107m");
        }
        System.out.println();
    }

    /**
     *
     * @param solution , solution to color it inside the maze
     */
    public void printMazeWithSolution(Solution solution){

        for(int i = 0; i < this.maze.length; ++i) {
            for(int j = 0; j < this.maze[i].length; ++j) {

                if (i == this.getStartPosition().getRowIndex() && j == this.getStartPosition().getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"S");
                } else if (i == this.getGoalPosition().getRowIndex() && j == this.getGoalPosition().getColumnIndex()) {
                    System.out.print(" \u001b[46m"+"E");
                } else if ( this.maze[i][j] == 1) {
                    System.out.print(" \u001b[40m ");
                }
                else {
                    for(int x=0 ;x<solution.getSolutionPath().size() ;x++){
                        AState try2 = solution.getSolutionPath().get(x);
                        MazeState tryM = (MazeState) try2;
                        if(tryM.getPosition().getRowIndex() ==i && tryM.getPosition().getColumnIndex()==j) {
                            System.out.print(" \u001b[43m ");
                            break;
                        }
                        else if(x==solution.getSolutionPath().size()-1)
                            System.out.print(" \u001b[107m ");
                    }
                }
            }
            System.out.println(" \u001b[107m");
        }
        System.out.println();
    }

    private int[] getMazeIdentifiers(){
        int[] mazeIdentifiers = new int[6];
        mazeIdentifiers[0] = rows;
        mazeIdentifiers[1] = columns;
        mazeIdentifiers[2] = startPosition.getRowIndex();
        mazeIdentifiers[3] = startPosition.getColumnIndex();
        mazeIdentifiers[4] = goalPosition.getRowIndex();
        mazeIdentifiers[5] = goalPosition.getColumnIndex();
        return mazeIdentifiers;
    }

    public void setIdentifiers(byte[] maze){

    }

    /**
     * the function turn the maze into bytes array.
     * first 7 bytes: <=255,row, col, start row,start col, goal row, goal col.
     * next bytes will be the same as the maze grid.
     * @return
     */
    public byte[] toByteArray(){
        //6 for x,y of row and column of start and goal position + rows and columns numbers of maze
        byte[] mazeByteArray;
        int[] mazeIdentifiers = getMazeIdentifiers();
        int nextIndex = 1;

        if(rows <= 255 && columns <= 255 && goalPosition.getColumnIndex() <= 255 && goalPosition.getRowIndex() <= 255 && startPosition.getColumnIndex() <= 255 && startPosition.getRowIndex() <= 255){
            mazeByteArray = new byte[(rows * columns) + 6 + 1];
            mazeByteArray[0] = 0;
            for(int value : mazeIdentifiers)
                mazeByteArray[nextIndex++] = (byte) value;
        }
        else{
            mazeByteArray = new byte[rows * columns + 6 * 4 + 1];
            mazeByteArray[0] = 1;
            for(int value : mazeIdentifiers) {
                byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
                nextIndex = mergeArrays(mazeByteArray, bytes, nextIndex);
            }
        }
        //copy the maze grid to the new array, turn each number to byte number.
        for (int[] row : maze){
            for(int val : row){
                mazeByteArray[nextIndex++] = (byte) val;
            }
        }
        return mazeByteArray;
    }

    static int binaryToDecimal(int n)
    {
        int num = n;
        int dec_value = 0;

        // Initializing base
        // value to 1, i.e 2^0
        int base = 1;

        int temp = num;
        while (temp > 0) {
            int last_digit = temp % 10;
            temp = temp / 10;

            dec_value += last_digit * base;

            base = base * 2;
        }

        return dec_value;
    }

    private static int mergeArrays(byte[] maze, byte[] toInsert, int nextIndex){
        int counter = 0;
        while(counter < toInsert.length){
            maze[nextIndex++] = toInsert[counter];
            counter++;
        }
        return nextIndex;
    }

    /**
     * a constructor that get a bytes array and use it in order to create the maze.
     * @param bytes
     */
    public Maze(byte[] bytes){ //build maze with this array
        int nextIndexToREad = 1;
        if(bytes[0] == 0) {//7 bytes of information
            this.rows = Byte.toUnsignedInt(bytes[1]);
            this.columns = Byte.toUnsignedInt(bytes[2]);
            this.startPosition = new Position(Byte.toUnsignedInt(bytes[3]), Byte.toUnsignedInt(bytes[4]));
            this.goalPosition = new Position(Byte.toUnsignedInt(bytes[5]), Byte.toUnsignedInt(bytes[6]));
            nextIndexToREad = 7;
        }else{
            this.rows = ByteBuffer.wrap(bytes, 1, 4).getInt();;
            this.columns = ByteBuffer.wrap(bytes, 5,4).getInt();;
            this.startPosition = new Position(ByteBuffer.wrap(bytes, 9,4).getInt(), ByteBuffer.wrap(bytes, 13,4).getInt());
            this.goalPosition = new Position(ByteBuffer.wrap(bytes, 17,4).getInt(), ByteBuffer.wrap(bytes, 21,4).getInt());
            nextIndexToREad = 25;
        }
        this.maze = new int[this.rows][this.columns];
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++) {
                this.maze[i][j] = Byte.toUnsignedInt(bytes[nextIndexToREad++]);
            }
        }


    }
    public String toString(){
        String s;
        s = rows + " " + columns + " " + startPosition.getRowIndex() + " " + startPosition.getColumnIndex() + " " + goalPosition.getRowIndex() + " " + goalPosition.getColumnIndex() + " " ;
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze1 = (Maze) o;
        return rows == maze1.rows && columns == maze1.columns && Arrays.equals(maze, maze1.maze) && Objects.equals(startPosition, maze1.startPosition) && Objects.equals(goalPosition, maze1.goalPosition);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, columns, startPosition, goalPosition);
        result = 31 * result + Arrays.hashCode(maze);
        return result;
    }
}