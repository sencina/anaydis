package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractMerge extends AbstractSorter{


    AbstractMerge(SorterType type) {
        super(type);
    }

    protected <T> void merge(Comparator<T> comparator, @NotNull List<T> list, int low, int middle, int high) {

        List<T> aux = new ArrayList<>(list.size());

        for (int i = low; i <= middle; i++) {
            aux.add(list.get(i));
        }
        for (int j = high; j > middle; j--) {
            aux.add(list.get(j));
        }

        for (int k = low, i = 0, j = aux.size()-1; k <= high; k++) {
            if(greater(comparator,aux, i, j)) {
                copy(list, k, aux, j--);
            } else {
                copy(list, k, aux, i++);
            }
        }
    }

    private <T> void copy(@NotNull List<T> aux, int i, @NotNull List<T> list, int j){
        aux.set(i,list.get(j));
    }
}
