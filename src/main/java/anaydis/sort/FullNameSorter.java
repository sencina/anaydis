package anaydis.sort;

import java.util.Comparator;
import java.util.List;

public class FullNameSorter {

    public void sort(Comparator<FullName> comparator, List<FullName> list){
        InsertionSorter insertionSorter = new InsertionSorter();
        insertionSorter.sort(comparator,list);
    }
}
