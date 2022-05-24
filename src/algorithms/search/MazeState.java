package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(Position pos){
        super(pos);
    }

    public String toString(){
        String s="";
        s+=this.position.toString();
        s+= ",cost: " + this.getCost() + ".";
        return s;
    }


    public int getRowPosition(){
        return this.position.getRowIndex();
    }
    public int getColumnPosition(){
        return this.position.getColumnIndex();
    }



}
