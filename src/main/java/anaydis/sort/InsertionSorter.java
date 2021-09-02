package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class InsertionSorter extends AbstractSorter{

    public InsertionSorter() {
        super(SorterType.INSERTION);
        addSorterListener(new SorterListenerImplementation());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j >0; j--) {

                if (greater(comparator,list,j-1,j)){

                    swap(list,j,j-1);
                }else break;
            }
        }

    }


}
