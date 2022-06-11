package IO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * first byte in b array - 0 for identifiers lower than 225, 1 over 225
     * 2 bytes for start position and 2 bytes for goal position
     * 2 bytes for dimensions of maze rows and columns
     * @param b
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {

        int numberOfProperties = (b[0] == 0) ? 7 : 25;

        for(int i = 0; i < numberOfProperties; i++){
            this.out.write(b[i]);
        }

        byte currentByte = 0;
        int currentValueCounter = 0;
        for (int i = numberOfProperties; i < b.length; i++) {
//            if(i == b.length-1)
//                System.out.println(currentValueCounter + " -----");
            if(b[i] == currentByte)
                currentValueCounter++;
            else {
//                System.out.println("index: "+ i+"  value: " + currentByte + ", repeated: " + currentValueCounter);
                writeCurrent(currentValueCounter);
                currentByte = b[i];
                currentValueCounter = 1;
            }

        }
        writeCurrent(currentValueCounter);
    }

    private void writeCurrent(int currentValueCounter) throws IOException {
        while(currentValueCounter >= 0){
            if(currentValueCounter >= 255){
                this.out.write(255);
                currentValueCounter -= 255;
                this.out.write(0);
            }
            else {
                this.out.write(currentValueCounter);
                break;
            }
        }
    }

    @Override
    public void write(int b) throws IOException {
    }

}











