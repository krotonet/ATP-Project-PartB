package IO;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private final int byteSize = Byte.SIZE;

    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * read from input stream, turn every byte into a binary number and place it into b array.
     * @param b, for read bytes
     * @return total bytes that have been read
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        int totalBytesRead = this.in.read(b, 0,1);

        int numberOfProperties = (b[0] == 0) ? 7 : 25;
        totalBytesRead = this.in.read(b, 1,numberOfProperties - 1);

        int bIndex = numberOfProperties;
        int bytesLeft = b.length - numberOfProperties;

        while (bytesLeft >= byteSize){
            bytesLeft -= byteSize;
            bIndex = writeValues(b, bIndex,byteSize);
            totalBytesRead += byteSize;
        }
        if(bytesLeft > 0){
            totalBytesRead += writeValues(b, bIndex, bytesLeft) - bIndex;
        }
        return totalBytesRead;
    }

    /**
     * the function read one byte from b array and turn it into a binary number.
     * write the binary number into b array.
     * @param b th array we write into
     * @param index where start writing into b array
     * @return the next index to write to.
     * @throws IOException
     */
    private int writeValues(byte[] b, int index, int arraySize) throws IOException {
        //read 1 byte from input
        byte value = (byte) this.in.read();
        int byteValue = Byte.toUnsignedInt(value);

        //turn byteValue number back to binary number, start write the bytes from the end of the array
        byte[] tempArray = getBytesArray(byteValue, arraySize);

        //update bytes of the original array-b
        for(byte bit : tempArray){
            b[index++] = bit;
        }
        return index;
    }

    /**
     * get bytes array of value read from file
     * @param value, value got from file representing 8-bit number from maze
     * @param arraySize, size is 8 or less, depends on bites left to complete the maze decompression
     * @return ,bytes array - represent the value in 8-bit
     */
    private byte[] getBytesArray(int value, int arraySize){
        byte[] tempArray = new byte[arraySize];
        int tempIndex = tempArray.length - 1;
        while (value > 0 && tempIndex >= 0) {
            tempArray[tempIndex--] = (byte) (value % 2);
            value = value / 2;
        }
        return tempArray;
    }

}

