package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickSorter extends AbstractSorter{

    public QuickSorter() {
        super(SorterType.QUICK);
        addSorterListener(new SorterListener());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator,list,0, list.size()-1);
    }

    private <T> void sort(@NotNull Comparator<T> comparator,@NotNull List<T> list,int lo, int hi){
        if(hi <= lo) return;
        int i = partition(comparator,list, lo, hi);
        sort(comparator,list, lo, i - 1);
        sort(comparator,list, i + 1, hi);
    }

    private <T> int partition(@NotNull Comparator<T> comparator,@NotNull List<T> list,int lo, int hi){
        int i = lo - 1;
        int j = hi;
        while(true) {
            while(greater(comparator,list,hi, ++i))
                if (i == hi) break;
            while( greater(comparator,list,--j,hi) )
                if (j == lo) break;
            if (i >= j) break;
            swap(list, i, j);
        }
        swap(list, i, hi);
        return i;
    }
}
