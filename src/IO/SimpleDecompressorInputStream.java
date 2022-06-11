package IO;


//import java.io.IOException;
//import java.io.InputStream;
//
//public class SimpleDecompressorInputStream extends InputStream {
//    private InputStream in;
//
//    public SimpleDecompressorInputStream(InputStream in) {
//        this.in = in;
//    }
//
//    @Override
//    public int read(byte[] b) throws IOException {
//
//        return 0;
//    }
//
//    @Override
//    public int read() throws IOException {
//
//        return 0;
//    }
//}

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {

    InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(){ return 0; }

    //    @Override
    public int read(byte[] b) throws IOException {
        int numberOfProperties = this.in.read(b, 0,1);

        numberOfProperties = (b[0] == 0) ? 7 : 25;
        int totalBytesRead = this.in.read(b, 1,numberOfProperties - 1) + 1;


        byte currentValue = 0;
        int index = numberOfProperties;
        int occurrences;


        while ((occurrences = this.in.read()) != -1){
            if(occurrences == 0) {
                currentValue = (currentValue == 0) ? (byte) 1 : 0;
                totalBytesRead++;
                continue;
            }
            while(occurrences != 0){
                b[index++] = currentValue;
                occurrences--;
                totalBytesRead++;
            }
            currentValue = (currentValue == 0) ? (byte) 1 : 0;
        }

        return totalBytesRead;
    }

    /**
     * @param b an empty array that will hold the data we read.
     * @return int represent the number of bytes we read
     */
    public int read1(byte[] b) throws IOException {
        int numOfBytes = 0;
        try {
            numOfBytes += this.in.read(b, 0, 1);
            int startIndex;
            byte byteToWrite = (byte) 0;
            //set the start index for maze data
            if (b[0] == 0) {
                startIndex = 7;
            } else if (b[0] == 1) {
                startIndex = 25;
            } else {
                throw new IOException();
            }
            //read maze info bytes to b
            numOfBytes += this.in.read(b, 1, startIndex - 1);
            //read maze data bytes to b
            int data = this.in.read();
            while (data != -1) {
                for (int i = 0; i < data; i++) {
                    b[startIndex++] = byteToWrite;
                    numOfBytes++;
                }

                data = this.in.read();
                if (data == 0) {
                    data = this.in.read();
                } else if (data != -1) {
                    byteToWrite = (byte) ((byteToWrite) ^ (1));
                }
            }
        }catch (IOException io){
            throw io;
        }catch (Exception e){
            e.printStackTrace();
        }
        return numOfBytes;
    }
}
