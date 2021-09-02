package anaydis.sort;

import anaydis.sort.auxClass.SorterListenerImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorter extends AbstractQuick {


    private static int m = 15;

    public QuickCutSorter() {
        super(SorterType.QUICK_CUT);
        getListeners().add(new SorterListenerImplementation());
    }

    public int getM() {
        return m;
    }

    public void setM(int m){
        this.m = m;
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator,list,0, list.size()-1);
    }

    private <T> void sort(@NotNull Comparator<T> comparator,@NotNull List<T> list,int lo, int hi){
        if (hi - lo <= m) {
            insertion(comparator,list, lo, hi);
            return;
        }
        int i = partition(comparator,list, lo, hi);
        sort(comparator,list, lo, i - 1);
        sort(comparator,list, i + 1, hi);
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
