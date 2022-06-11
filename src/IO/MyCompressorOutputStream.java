package IO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {
    public OutputStream out;
    private final int byteSize = Byte.SIZE;

    public MyCompressorOutputStream(OutputStream outputStream) {//get array of bytes
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
    }

    /**
     * the function turn every 8 bytes into a binary number in bytes, and write it into output stream.
     * @param b bytes array that represents the compressed maze.
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        int numberOfProperties = (b[0] == 0) ? 7 : 25;

        for(int i = 0; i < numberOfProperties; i++){
            this.out.write(b[i]);
        }
        //every 8 bytes turn into a binary number.
        byte[] toBinary;
        for(int i = numberOfProperties; i < b.length; i += byteSize){

            if(i + byteSize <= b.length - 1){
                toBinary = Arrays.copyOfRange(b, i, i + byteSize);
                this.out.write(toBinaryByte(toBinary));
            }
            else{ //when less than 8 bytes left.
                toBinary = Arrays.copyOfRange(b, i, b.length);
                this.out.write(toBinaryByte(toBinary));
            }
        }
    }

    /**
     * the function get a bytes array and turn bytes to it decimal representation.
     * @param binary bytes array(size 8 or less)
     * @return the number in bytes.
     */
    private byte toBinaryByte(byte[] binary){
        int ans = 0;
        for(int i = binary.length - 1; i >= 0; i--){
            if(binary[i] != 0)
                ans += Math.pow(2, binary.length - i - 1);
        }
        return (byte) ans;
    }

}
