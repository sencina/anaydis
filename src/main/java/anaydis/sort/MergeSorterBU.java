package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSorterBU extends AbstractMerge {


    public MergeSorterBU() {
        super(SorterType.MERGE_BOTTOM_UP);
        getListeners().add(new SorterListenerImplementation());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator,list,0,list.size()-1);
    }

    private <T> void sort(Comparator<T> comparator,List<T> list, int low, int high) {
        for(int middle = 1; middle <= high - low; middle *= 2) {
            final int middleX2 = middle * 2;
            for (int low1 = low; low1 <= high - middle; low1 += middleX2) {
                final int high1 = Math.min(low1 - low + middleX2 - 1, high);
                merge(comparator,list, low1, low1 + middle - 1, high1);
            }
        }
    }

}
