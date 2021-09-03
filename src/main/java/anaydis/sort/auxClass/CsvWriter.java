package anaydis.sort.auxClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvWriter<T> {

    private final String path;

    public CsvWriter(String path) {
        this.path = path;
    }

    public void write(T element) throws IOException {
        String[] aux = element.toString().split(",");
        FileWriter writer = new FileWriter(path);
        for (int i = 0; i < aux.length; i++) {
            if (i == aux.length-1){
                writer.append(aux[i]);
                writer.append("\n");
            }
            else {writer.append(aux[i]);
            writer.append(',');}
        }
        writer.flush();
        writer.close();
    }


}
