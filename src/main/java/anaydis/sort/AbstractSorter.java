package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements ObservableSorter {

    private final SorterType type;
    private final List<SorterListenerImplementation> listeners;

    AbstractSorter(SorterType type){
        this.type = type;
        this.listeners = new ArrayList<>();
    }

    <T> void swap(List<T> list, int i, int j) {
        for (int k = 0; k < listeners.size(); k++) {
            listeners.get(k).swap(i,j);
        }
        list.set(j, list.set(i, list.get(j)));
    }

    <T> boolean greater(Comparator<T> comparator, List<T> list, int i, int j) {
         for (int k = 0; k < listeners.size(); k++) {
             listeners.get(k).greater(i,j);
         }
        return comparator.compare(list.get(i), list.get(j))>0;
    }

    @Override
    public @NotNull SorterType getType() {
        return type;
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        listeners.add((SorterListenerImplementation) listener);

    }

    public List<SorterListenerImplementation> getListeners() {
        return listeners;
    }
}
