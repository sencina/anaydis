package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class InsertionSorter extends AbstractSorter{

    public InsertionSorter() {
        super(SorterType.INSERTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int comparaciones = 0;
        int intercambios = 0;
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j >0; j--) {
                comparaciones++;
                if (greater(comparator,list,j-1,j)){
                    intercambios++;
                    swap(list,j,j-1);
                }else break;
            }
        }
        System.out.println(intercambios);
        System.out.println(comparaciones);
    }
}
