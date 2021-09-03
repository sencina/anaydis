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
            copy(aux, i, list, i);
        }
        for (int j = middle + 1; j <= high; j++) {
            copy(aux, high + (middle + 1) - j, list, j);
        }
        list.clear();
        for (int k = low, i = low, j = high; k <= high; k++) {
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
