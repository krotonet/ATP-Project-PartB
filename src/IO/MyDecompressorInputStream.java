package IO;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * read from input stream, turn every byte into a binary number and place it into b array.
     * @param b
     * @return total bytes that have been read
     * @throws IOException
     */
    @Override
    public int read(byte[] b) throws IOException {
        int totalBytesRead = 0;
        try {
            //totalBytesRead += this.in.read(b, 0,1);
            int numberOfProperties = (b[0] == 0) ? 7 : 25;
            totalBytesRead = this.in.read(b, 1,numberOfProperties - 1) + 1;

            int index = numberOfProperties;
            int bytesLeft = b.length - numberOfProperties;

            while (bytesLeft >= 8){
                bytesLeft -= 8;
                index = writeVal(b, index,8);
            }
            if(bytesLeft > 0){
                writeVal(b, index, bytesLeft);
            }
        }catch (IOException io){
            throw io;
        }catch (Exception e){
            e.printStackTrace();
        }
        return totalBytesRead;
    }

    /**
     * the function read one byte from b array and turn it into a binary number.
     * write the binary number into b array.
     * @param b th array we write into
     * @param index where start writing into b array
     * @param arraySize
     * @return the next index to write to.
     * @throws IOException
     */
    private int writeVal(byte[] b, int index, int arraySize) throws IOException {
        byte value;
        int temp;
        byte[] tempArray = new byte[arraySize];
        int tempIndex = tempArray.length - 1;

        value = (byte) this.in.read(); //read 1 byte from input
        temp = Byte.toUnsignedInt(value); //turn it into int

        //turn temp number back to binary number, start write the bytes from the end of the array.
        while (temp > 0 && tempIndex>=0) {
            tempArray[tempIndex--] = (byte) (temp % 2);
            temp = temp / 2;
        }

        //update bytes of the original array-b.
        for(byte bit : tempArray){
            b[index++] = bit;
        }
        return index;
    }


    private int writeValues(byte[] b,int bytesLeft, int index) throws IOException {
        int tempIndex = 7;
        byte[] tempArray;
        if(bytesLeft > 0){
            tempIndex = bytesLeft - 1;
            tempArray = new byte[bytesLeft];
        }
        else{
            tempArray = new byte[8];
        }
        byte value = (byte) this.in.read();
        int temp = Byte.toUnsignedInt(value);
        while (temp > 0) {
            tempArray[tempIndex--] = (byte) (temp % 2);
            temp = temp / 2;
        }
        for(byte bit : tempArray){
            b[index++] = bit;
        }
        return index;
    }

}

