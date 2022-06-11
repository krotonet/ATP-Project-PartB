package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    private final Configurations configurations = Configurations.getInstance();
    @Override
    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        try {
            //read data from input
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            int[] mazeDimensions = (int[]) (fromClient.readObject());

            int rows = mazeDimensions[0];
            int columns = mazeDimensions[1];
            IMazeGenerator mazeGenerator = configurations.getMazeGeneratingAlgorithm();
            //generate maze
            Maze newMaze = mazeGenerator.generate(rows, columns);

            //get maze in bytes array
            byte[] mazeInByte = newMaze.toByteArray();

            //apply strategy on array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MyCompressorOutputStream myCompressorOutputStream = new MyCompressorOutputStream(byteArrayOutputStream);
            myCompressorOutputStream.write(mazeInByte);
            myCompressorOutputStream.flush();
            myCompressorOutputStream.close();

            //send compressed array to client
            byte[] compressedMaze = byteArrayOutputStream.toByteArray();
            toClient.writeObject(compressedMaze);
            toClient.flush();
            byteArrayOutputStream.close();
            toClient.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}