package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickSorter extends AbstractQuick{

    public QuickSorter() {
        super(SorterType.QUICK);
        addSorterListener(new SorterListenerImplementation());
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


}
