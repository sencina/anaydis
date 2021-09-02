package anaydis.sort;

import anaydis.sort.auxClass.FullName;
import anaydis.sort.auxClass.SorterProviderImplementation;
import anaydis.sort.dataSetsGenerator.IntegerDataSetGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SorterCasesTest {


    private final InsertionSorter insertionSorter = new InsertionSorter();
    private final BubbleSorter bubbleSorter = new BubbleSorter();
    private final SelectionSorter selectionSorter = new SelectionSorter();
    private final QuickSorter quickSorter = new QuickSorter();
    private final ShellSorter shellSorter = new ShellSorter();
    private final SorterProviderImplementation provider = new SorterProviderImplementation();
    private final IntegerDataSetGenerator integerDataSetGenerator = new IntegerDataSetGenerator();
    private final Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };


    private int length = (int) Math.pow(10, 1) * 1;

    @Test
    public void bestCaseInsertion() {

        System.out.println("best");
        List list = integerDataSetGenerator.createAscending(length);
        List toCompare = integerDataSetGenerator.createAscending(length);

        insertionSorter.sort(comparator, list);

        Assert.assertTrue(list.equals(toCompare));

    }

    @Test
    public void worstCaseInsertion() {
        System.out.println("worst");
        List list = integerDataSetGenerator.createDescending(length);
        List toCompare = integerDataSetGenerator.createAscending(length);

        insertionSorter.sort(comparator, list);

        Assert.assertTrue(list.equals(toCompare));
    }

    @Test
    public void averageCaseInsertion() {

        System.out.println("average");
        List list = integerDataSetGenerator.createRandom(length);

        insertionSorter.sort(comparator, list);


    }

    @Test
    public void bestCaseBubble() {

        System.out.println("best");
        List list = integerDataSetGenerator.createAscending(length);
        List toCompare = integerDataSetGenerator.createAscending(length);

        bubbleSorter.sort(comparator, list);

        Assert.assertTrue(list.equals(toCompare));

    }

    @Test
    public void worstCaseBubble() {
        System.out.println("worst");
        List list = integerDataSetGenerator.createDescending(length);
        List toCompare = integerDataSetGenerator.createAscending(length);

        bubbleSorter.sort(comparator, list);

        Assert.assertTrue(list.equals(toCompare));
    }

    @Test
    public void averageCaseBubble() {
        System.out.println("average");

        List list = integerDataSetGenerator.createRandom(length);
        bubbleSorter.sort(comparator, list);


    }

    @Test
    public void bestCaseSelection() {

        System.out.println("best");
        List list = integerDataSetGenerator.createAscending(length);
        List toCompare = integerDataSetGenerator.createAscending(length);

        selectionSorter.sort(comparator, list);

        Assert.assertTrue(list.equals(toCompare));

    }

    @Test
    public void worstCaseSelection() {
        System.out.println("worst");
        List list = integerDataSetGenerator.createDescending(length);
        List toCompare = integerDataSetGenerator.createAscending(length);

        selectionSorter.sort(comparator, list);

        Assert.assertTrue(list.equals(toCompare));
    }

    @Test
    public void averageCaseSlection() {
        System.out.println("average");
        List list = integerDataSetGenerator.createRandom(length);
        selectionSorter.sort(comparator, list);
    }
    
}



