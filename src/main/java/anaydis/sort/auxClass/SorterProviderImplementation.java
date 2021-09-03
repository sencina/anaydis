package anaydis.sort.auxClass;

import anaydis.sort.*;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class SorterProviderImplementation implements SorterProvider {

    private final Map<SorterType, Sorter> sorters = new EnumMap<>(SorterType.class);

    public SorterProviderImplementation() {
        sorters.put(SorterType.SELECTION,new SelectionSorter());
        sorters.put(SorterType.INSERTION,new InsertionSorter());
        sorters.put(SorterType.BUBBLE,new BubbleSorter());
        sorters.put(SorterType.QUICK,new QuickSorter());
        sorters.put(SorterType.H,new HSorter());
        sorters.put(SorterType.SHELL,new ShellSorter());
        sorters.put(SorterType.QUICK_NON_RECURSIVE,new QuickSorterNonRecursive());
        sorters.put(SorterType.QUICK_CUT,new QuickCutSorter());
        sorters.put(SorterType.QUICK_MED_OF_THREE,new QuickSorterMedOfThree());
        sorters.put(SorterType.QUICK_THREE_PARTITION,new QuickSorterThreePartition());
        sorters.put(SorterType.MERGE_BOTTOM_UP, new MergeSorterBU());
        sorters.put(SorterType.MERGE_TOP_DOWN, new MergeSorterTD());

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
