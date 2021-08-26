package anaydis.sort;

import java.util.Comparator;

public class FullNameComparator implements Comparator<FullName> {

    @Override
    public int compare(FullName o1, FullName o2) {
        int value = o1.getLastName().compareTo(o2.getLastName());
        if (value != 0) return value;
        else return o1.getFirstName().compareTo(o2.getFirstName());
    }
}
