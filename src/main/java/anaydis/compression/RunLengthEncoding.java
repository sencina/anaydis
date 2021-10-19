package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RunLengthEncoding implements Compressor{
    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        int current  = input.read();
        int count = 1;
        int previous = current;


        while (current != -1){

            if (previous == current){
                count++;
            }
            else {
                if (count > 1) output.write(0xFF);output.write(count);
                output.write(current);
                count = 1;
            }

            previous = current;
            current = input.read();
        }

    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        int current = input.read();

        while (current != -1){

            if (current == 0xFF){

                int count = input.read();
                int character = input.read();

                for (int i = 0; i < count; i++) {
                    output.write(character);
                }
            }

            else output.write(current);

            current = input.read();

        }

    }
}
