package IO;
import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
    }

    /**
     * first byte in b array - 0 for identifiers lower than 225, 1 for over 225
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
            if(b[i] == currentByte)
                currentValueCounter++;
            else {
                writeCurrent(currentValueCounter);
                currentByte = b[i];
                currentValueCounter = 1;
            }
        }
        writeCurrent(currentValueCounter);
    }

    /**
     * if value bigger than 256 bit, write 255 first, then 0 for other value and notify current bit continues
     * @param currentValueCounter , value to write, representing the amount of 1/0 counted
     * @throws IOException
     */
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

}











