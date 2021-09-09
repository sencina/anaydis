package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSorterThreePartition extends AbstractQuick{


    public QuickSorterThreePartition() {
        super(SorterType.QUICK_THREE_PARTITION);
        getListeners().add(new SorterListenerImplementation());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator,list,0,list.size()-1);
    }


    private  <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if (hi<=lo) return;
        T pivot = list.get(lo);
        int i = lo+1;int p=lo;int q =hi;
        while(i<=q){
            if (less(list,comparator,pivot,list.get(i),lo,i))swap(list,i,q--);
            else if (equals(list,comparator,pivot,list.get(i),lo,i)) i++;
            else swap(list,p++,i++);
        }
        sort(comparator,list,lo,p-1);
        sort(comparator,list,q+1,hi);
    }

    private <T> boolean less(List<T> list,Comparator<T> comparator, T a,T b, int i, int j){
        greater(comparator,list,j,i);
        return comparator.compare(a,b)<0;
    }

    private <T> boolean equals(List<T> list, Comparator<T> comparator, T a,T b,int i,int j){
        equals(comparator,list,i,j);
        return comparator.compare(a,b) == 0;
    }


}
