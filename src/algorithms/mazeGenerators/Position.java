package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column){
        if ( row < 0  || column < 0 ) throw new IllegalArgumentException("Position can't be negative");
        this.row = row;
        this.column = column;
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
        return this.row == position.getRowIndex() && this.column == position.getColumnIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
