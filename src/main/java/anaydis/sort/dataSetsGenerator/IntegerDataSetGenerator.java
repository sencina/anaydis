package anaydis.sort.dataSetsGenerator;

import anaydis.sort.DataSetGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class IntegerDataSetGenerator implements DataSetGenerator {


    public IntegerDataSetGenerator() {

    }


    @Override
    public @NotNull List createAscending(int length) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        return list;
    }

    @Override
    public @NotNull List createDescending(int length) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = length-1; i >= 0; i--) {
            list.add(i);
        }
        return list;
    }

    @Override
    public @NotNull List createRandom(int length) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random()*10);
            list.add(number);
        }
        return list;
    }

    public @NotNull List createRandom(int length, long seed) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random(seed);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt();
            list.add(number);
        }
        return list;
    }


    @Override
    public @NotNull Comparator getComparator() {
        return Comparator.naturalOrder();
    }
}
