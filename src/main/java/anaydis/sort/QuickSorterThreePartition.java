package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

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
        T v = list.get(hi);
        int i = lo-1;int j=hi;int p=i;int q =j;
        for (;;){
            while (greater(comparator,list,hi,++i));
            while (greater(comparator,list,--j,hi)) break;
            if (i>=j) break;
            swap(list,i,j);
            if (v.equals(list.get(i))){p++;swap(list,p,i);}
            if (v.equals(list.get(j))){p++;swap(list,p,j);}
        }
        swap(list,i,hi);j=i-1;i=i+1;
        for (int k = lo; k <= p; k++,j--) swap(list,k,j);
        for (int k = hi-1; k >=q; k--,i++) swap(list,k,i);
        sort(comparator,list,lo,j);
        sort(comparator,list,i,hi);
    }
}
