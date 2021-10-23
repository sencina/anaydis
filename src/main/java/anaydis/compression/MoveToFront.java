package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MoveToFront implements Compressor {


    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        List<Integer>dictionary=createDictionary();
        int current=input.read();


        while (current!=-1){

            int index=dictionary.indexOf(current);
            output.write(index);
            dictionary.remove(index);
            dictionary.add(0,current);
            current = input.read();

        }
    }


    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        List<Integer>dictionary=createDictionary();
        int current=input.read();


        while (current!=-1){

            int index=dictionary.indexOf(current);
            output.write(index);
            dictionary.remove(current);
            dictionary.add(0,index);
            current = input.read();

        }

    }

    private List<Integer> createDictionary(){
        List<Integer>dictionary=new ArrayList<>();
        for (int i = 0; i < 255; i++) {
            dictionary.add(i);
        }
        return dictionary;
    }
}
