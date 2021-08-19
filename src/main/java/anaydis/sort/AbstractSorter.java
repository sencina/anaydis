package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements Sorter {

    private final SorterType type;

    AbstractSorter(SorterType type){
        this.type = type;
    }

    <T> void swap(List<T> list, int i, int j) {
        list.set(j, list.set(i, list.get(j)));
    }

     <T> boolean greater(Comparator<T> comparator, List<T> list, int i, int j) {
        return comparator.compare(list.get(i), list.get(j))>0;
    }

    @Override
    public @NotNull SorterType getType() {
        return type;
    }

}
