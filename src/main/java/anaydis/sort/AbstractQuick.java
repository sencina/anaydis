package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public abstract class  AbstractQuick extends AbstractSorter{


    AbstractQuick(SorterType type) {
        super(type);
    }

    <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi){
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
