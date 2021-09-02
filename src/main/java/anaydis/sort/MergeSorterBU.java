package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSorterBU extends AbstractSorter {


    MergeSorterBU() {
        super(SorterType.MERGE_BOTTOM_UP);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

    }


}
