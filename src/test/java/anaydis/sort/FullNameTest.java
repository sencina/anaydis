package anaydis.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FullNameTest {

    private final List<FullName> names = new ArrayList<>();
    private final List<FullName> sortedNames = new ArrayList<>();
    private final BubbleSorter bubbleSorter = new BubbleSorter();
    private final SelectionSorter selectionSorter = new SelectionSorter();
    private final InsertionSorter insertionSorter = new InsertionSorter();

    public void generateDefaultLists(){
        names.add(new FullName("Paula","Alonso"));
        names.add(new FullName("Diego","Alonso"));
        names.add(new FullName("Diego","Diaz"));
        sortedNames.add(new FullName("Diego","Alonso"));
        sortedNames.add(new FullName("Paula","Alonso"));
        sortedNames.add(new FullName("Diego","Diaz"));
    }

    public void generateWorstCaseList(){
        names.add(new FullName("Carlosa","Maslaton"));
        names.add(new FullName("Carlos","Maslaton"));
        names.add(new FullName("Carlosd","Maslaton"));
        names.add(new FullName("Carlosc","Maslaton"));
        names.add(new FullName("Carlosb","Maslaton"));
        sortedNames.add(new FullName("Carlos","Maslaton"));
        sortedNames.add(new FullName("Carlosa","Maslaton"));
        sortedNames.add(new FullName("Carlosb","Maslaton"));
        sortedNames.add(new FullName("Carlosc","Maslaton"));
        sortedNames.add(new FullName("Carlosd","Maslaton"));
    }

    @Test
    public void defaultNamesBubbleTest(){
        generateDefaultLists();
        bubbleSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                int value = o1.getLastName().compareTo(o2.getLastName());
                if (value != 0) return value;
                else return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }, names);
        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).equals(sortedNames.get(i)));
        }
        names.clear();
        sortedNames.clear();
    }

    @Test
    public void defaultNamesSelectionTest(){
        generateDefaultLists();
        selectionSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                int value = o1.getLastName().compareTo(o2.getLastName());
                if (value != 0) return value;
                else return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }, names);

        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).equals(sortedNames.get(i)));
        }

        names.clear();
        sortedNames.clear();
    }
    @Test
    public void defaultNamesInsertionTest(){
        generateDefaultLists();
        insertionSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                int value = o1.getLastName().compareTo(o2.getLastName());
                if (value != 0) return value;
                else return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }, names);

        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).equals(sortedNames.get(i)));
        }

        names.clear();
        sortedNames.clear();
    }

    @Test
    public void worstCaseBubbleTest(){
        generateWorstCaseList();
        bubbleSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                int value = o1.getLastName().compareTo(o2.getLastName());
                if (value != 0) return value;
                else return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }, names);
        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).equals(sortedNames.get(i)));
        }

        names.clear();
        sortedNames.clear();
    }

    @Test
    public void worstCaseSelectionTest(){
        generateWorstCaseList();
        selectionSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                int value = o1.getLastName().compareTo(o2.getLastName());
                if (value != 0) return value;
                else return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }, names);
        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).equals(sortedNames.get(i)));
        }

        names.clear();
        sortedNames.clear();
    }

    @Test
    public void worstCaseInsertionTest(){
        generateWorstCaseList();
        insertionSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                int value = o1.getLastName().compareTo(o2.getLastName());
                if (value != 0) return value;
                else return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }, names);
        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).equals(sortedNames.get(i)));
        }

        names.clear();
        sortedNames.clear();
    }

}
