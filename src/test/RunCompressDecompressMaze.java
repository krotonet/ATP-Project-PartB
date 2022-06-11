package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        byte b = (byte)1111;

        System.out.println(b);



        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(600, 555); //Generate new maze
        try {
            // save maze to a file
            OutputStream out2 = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));

//            out2.write(maze.toByteArray());
//            out2.flush();
//            out2.close();

            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] mazeBytesArray = maze.toByteArray();
        byte[] savedMazeBytes = new byte[0];
        try {
            //read maze from file
            InputStream in2 = new SimpleDecompressorInputStream(new FileInputStream(mazeFileName));
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));

            savedMazeBytes = new byte[maze.toByteArray().length];

//            in2.read(savedMazeBytes);
//            in2.close();
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        boolean bytesArrayEquals = Arrays.equals(mazeBytesArray, savedMazeBytes);
        System.out.println("---------------");
        for(int i = 0; i < mazeBytesArray.length; i++){
            if(mazeBytesArray[i] != savedMazeBytes[i]){
                System.out.println("index difference: " + i + " expected: " + mazeBytesArray[i] + " got: " + savedMazeBytes[i]);
            }
        }
        System.out.println(String.format("Mazes equal: %s",areMazesEquals)); //maze should be equal to loadedMaze
    }
}