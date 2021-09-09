package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSorterMedOfThree extends AbstractQuick{

    private static int m = 17;

    public QuickSorterMedOfThree() {
        super(SorterType.QUICK_MED_OF_THREE);
        getListeners().add(new SorterListenerImplementation());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        quickSort(comparator,list,0,list.size()-1);
        insertion(comparator,list,0,list.size()-1);
    }

    private <T> void quickSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo,int hi){
        if (hi-lo <= m) return;
        swap(list, (lo+hi)/2, hi-1);
        compExch(comparator,list, lo, hi-1);
        compExch(comparator,list, lo, hi);
        compExch(comparator,list, hi-1, hi);
        int i = partition(comparator,list, lo+1, hi-1);
        quickSort(comparator,list, lo, i-1);
        quickSort(comparator,list, i+1, hi);
    }

    private <T> void compExch(Comparator<T> comparator, List<T> list, int lo, int hi) {
        if (greater(comparator,list,lo,hi)){
            swap(list,lo,hi);
        }
    }

    private <T> void insertion(Comparator<T> comparator, List<T> list, int lo, int hi) {

        for (int i = lo+1; i <= hi; i++) {
            for (int j = i; j >0; j--) {

                if (greater(comparator,list,j-1,j)){

                    swap(list,j,j-1);
                }else break;
            }
        }

    }



}
