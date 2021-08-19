package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class SorterProviderImplementation implements SorterProvider{

    private final Map<SorterType, Sorter> sorters = new EnumMap<>(SorterType.class);

    public SorterProviderImplementation() {
        sorters.put(SorterType.SELECTION,new SelectionSorter());
        sorters.put(SorterType.INSERTION,new InsertionSorter());
        sorters.put(SorterType.BUBBLE,new BubbleSorter());
    }

    @Override
    public @NotNull Iterable<Sorter> getAllSorters() {
        return sorters.values();
    }

    @Override
    public @NotNull Sorter getSorterForType(@NotNull SorterType type) {
        return sorters.get(type);
    }
}
