package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class ShellSorter extends AbstractSorter{

    private int[] hValues;
    private final HSorter sorter;

    public ShellSorter() {
        super(SorterType.SHELL);
        this.hValues = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841};
        //this.hValues = new int[]{1, 8, 23, 77, 281, 1073, 4193, 16577};
        this.sorter = new HSorter();
        addSorterListener(new SorterListenerImplementation());


    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int i = hValues.length-1;

        while (i >=0){
            sorter.sort(comparator,list,hValues[i]);
            --i;
        }
    }


    public HSorter getSorter() {
        return sorter;
    }


}
