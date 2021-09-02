package anaydis.sort;


import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;


public class QuickSorterNonRecursive extends AbstractQuick{

    public QuickSorterNonRecursive() {
        super(SorterType.QUICK_NON_RECURSIVE);
        getListeners().add(new SorterListenerImplementation());
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        quicksortNonRecursive(comparator,list,0,list.size()-1);
    }

    private <T> void quicksortNonRecursive(Comparator<T> comparator,List<T> a, int l, int r) {
        Stack<Integer> stack = new Stack();
        stack.push(l);
        stack.push(r);
        while (!stack.isEmpty()) {
            r = stack.pop();
            l = stack.pop();
            if (r <= l) {
                continue;
            }
            int i = partition(comparator,a, l, r);
            if (i - l > r - i) {
                stack.push(l);
                stack.push(i - 1);
            }
            stack.push(i + 1);
            stack.push(r);
            if ((r-i)>=(i-l)){
                stack.push(l);
                stack.push(i - 1);
            }
        }
    }

}
