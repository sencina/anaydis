package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;

import java.util.Comparator;
import java.util.List;

public class HSorter extends AbstractSorter{


    public HSorter() {
        super(SorterType.H);
        addSorterListener(new SorterListenerImplementation());
    }

    public <T> void sort(Comparator<T> comparator, List<T> list){
        sort(comparator, list, 1);
    }

    /**
     * H-Sort list. Basically a BubbleSort in sets of elements separated by h
     */
    public <T> void sort(Comparator<T> comparator, List<T> list, int h) {

        for (int i = h; i < list.size(); i++) {
            for (int j = i; j >=h; j-=h) {

                if (greater(comparator,list,j-h,j)){

                    swap(list,j,j-h);
                }else break;
            }
        }

    }
}
