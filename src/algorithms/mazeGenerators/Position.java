package algorithms.mazeGenerators;
import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private int row;
    private int column;

    public Position(int row, int column){
        if ( row < 0 ) row = 0;
        if( column < 0 ) column = 0;
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

    /**
     * compare row and col of two positions.
     * @param o
     * @return true if they have the same row and col.
     */
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
