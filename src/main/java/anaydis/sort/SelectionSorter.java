package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SelectionSorter extends AbstractSorter{

    public SelectionSorter(){
        super(SorterType.SELECTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i+1; j < list.size(); j++) {
                comparaciones++;
                if (greater(comparator,list,min,j)){
                    min = j;
                }
            }
            if (min != i) {
                swap(list, i, min);
                intercambios++;
            }
        }

        System.out.println("Comparaciones = " +comparaciones);
        System.out.println("Intercambios = " +intercambios);
    }


}
