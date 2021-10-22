package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BurrowsWheeler implements Compressor{



    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        int character = input.read();
        List<Integer> inputList = new ArrayList<>();
        List<Integer> rotations = new ArrayList<>();
        int i = 0;

        while(character != -1){

            inputList.add(character);
            character = input.read();
            rotations.add(i);
            i++;
        }

        rotations.sort(new Comparator(inputList));
        int index = 0;
        for (int j = 0; j < rotations.size(); j++) {
            output.write(getLastBytes(j,rotations));
            if (rotations.get(j) == 1) index = j;
        }
        output.write(0xFF);
        while (index > 127){
            output.write(127);
            index-=127;
        }
        output.write(index);
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        List<Integer> l = getL(input);
        int index = getIndex(input);

        Integer[] t = getT(l);

        for (int i = 0; i < t.length; i++) {
            output.write(l.get(index));
            index = t[index];
        }

    }

    private Integer[] getT(List<Integer> l) {

        Integer[] t = new Integer[l.size()];
        List<Integer> f = l;
        f.sort(java.util.Comparator.naturalOrder());

        for (int i = 0; i < l.size(); i++) {
            t[i] =  indexOf(f.get(i),l,t);
        }

        return t;
    }

    private Integer indexOf(Integer integer, List<Integer> l, Integer[] t) {

        for (int i = 0; i < l.size(); i++) {

            if (l.get(i).equals(integer) && !contains(t,i)) return i;

        }

        return -1;

    }

    private boolean contains(Integer[] t, Integer integer){

        for (int i = 0; i < t.length; i++) {
            if (t[i].equals(integer)) return true;
        }
        return false;
    }

    private int getIndex(InputStream input) throws IOException {

        int index = 0;
        int character  = input.read();

        while (character != -1){
            index+=character;
            character = input.read();
        }
        return index;
    }

    private List<Integer> getL(InputStream input) throws IOException {

        List<Integer> l = new ArrayList<>();
        int character = input.read();
        while (character != 0xFF){
            l.add(character);
            character = input.read();
        }
        return l;

    }

    private int getLastBytes(Integer integer, List<Integer> list) {

        if (integer>0) return list.get(integer-1);
        else return list.get(list.size()-1);

    }



    private class Comparator implements java.util.Comparator<Integer>{

        final private List<Integer> list;

        public Comparator(List<Integer> list) {
            this.list = list;
        }


        @Override
        public int compare(Integer o1, Integer o2) {
            for(int i = 0; i < list.size(); i++){
                int cmp = Integer.compare(byteAt(o1, list, i), byteAt(o2, list, i));
                if (cmp != 0)
                    return cmp;
            }
            return 0;
        }

        private int byteAt(int offset, List<Integer> array, int pos){
            return array.get((pos + offset) % array.size());
        }
    }
}
