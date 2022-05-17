package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Position(int row, int column, boolean isWall){
        this.row = row;
        this.column = column;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.column = col;
    }
    public int getRowIndex(){
        return this.row;
    }

    public int getColumnIndex(){
        return this.column;
    }

    public String toString(){
        return "{" + this.row + "," + this.column + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.row == position.row && this.column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
