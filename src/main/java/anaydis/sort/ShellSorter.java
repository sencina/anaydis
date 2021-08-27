package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShellSorter extends AbstractSorter{

    private List<Integer> hValues;
    private final HSorter sorter;

    public ShellSorter() {
        super(SorterType.SHELL);
        this.hValues = new ArrayList<>();
        this.sorter = new HSorter();
        hValues.add(1);//8 23 77 281 1073 4193 16577
        hValues.add(8);//8 23 77 281 1073 4193 16577
        hValues.add(23);//8 23 77 281 1073 4193 16577
        hValues.add(77);//8 23 77 281 1073 4193 16577
        hValues.add(281);//8 23 77 281 1073 4193 16577
        hValues.add(1073);//8 23 77 281 1073 4193 16577
        hValues.add(4193);//8 23 77 281 1073 4193 16577
        hValues.add(16577);//8 23 77 281 1073 4193 16577

    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int i = hValues.size()-1;

        while (i >=0){
            sorter.sort(comparator,list,hValues.get(i));
            --i;
        }
    }

    private int initialHsearch(int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            if (hValues.get(mid) <= x) return mid;


            if (hValues.get(mid) > x) return initialHsearch( l, mid - 1, x);

            return initialHsearch( mid + 1, r, x);
        }


        return hValues.get(hValues.size()-1);
    }

    public void sethValues(List<Integer> hValues) {
        this.hValues = hValues;
    }

    public HSorter getSorter() {
        return sorter;
    }


}
