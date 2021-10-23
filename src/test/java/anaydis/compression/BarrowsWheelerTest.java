package anaydis.compression;

import org.junit.Test;

import java.io.*;

public class BarrowsWheelerTest {

    Compressor compressor = new BurrowsWheeler();

    @Test
    public void barrowsWheelerTest(){

        String drDobbs = "DRDOBBS";
        InputStream inputStream = new ByteArrayInputStream(drDobbs.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            compressor.encode(inputStream,outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream encoded = new ByteArrayInputStream(outputStream.toByteArray());
        OutputStream decoded = new ByteArrayOutputStream();

        try {
            compressor.decode(encoded,decoded);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
