package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(){ return 0; }

    /**
     *
     * @param b ,bytes array for decompressed data
     * @return , number of bytes read
     * @throws IOException
     */
    @Override
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

}
