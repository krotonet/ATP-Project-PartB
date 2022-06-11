package Server;
import java.io.*;
import IO.*;
import algorithms.mazeGenerators.*;


public class ServerStrategyGenerateMaze implements IServerStrategy{

    /**
     *
     * @param inputStream
     * @param outputStream
     * read arguments(row,col) from client input stream, create maze, compress it, and write it to client output stream.
     */
    @Override
    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        try{
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);

            OutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream myCompressorOutputStream = new MyCompressorOutputStream(out);

            int[] arrayForMaze = (int[])fromClient.readObject();
            int rows = arrayForMaze[0];
            int cols = arrayForMaze[1];
            AMazeGenerator aMazeGenerator = Configurations.mazeGeneratingAlgorithm();  //get generate algorithm
            Maze clientMaze = aMazeGenerator.generate(rows,cols);

            byte[] byteMaze = clientMaze.toByteArray();

            myCompressorOutputStream.write(byteMaze);
            toClient.writeObject(((ByteArrayOutputStream)myCompressorOutputStream.out).toByteArray());
            toClient.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
