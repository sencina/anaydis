package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class MergeSorterTD extends AbstractMerge{


    public MergeSorterTD() {
        super(SorterType.MERGE_TOP_DOWN);
        getListeners().add(new SorterListenerImplementation());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator,list,0,list.size()-1);
    }

    private <T> void sort(Comparator<T> comparator, List<T> list, int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2;
            sort(comparator,list, low, mid);
            sort(comparator,list, mid + 1, high);
            merge(comparator,list, low, mid, high);
        }
    }
}
