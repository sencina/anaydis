package anaydis.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SorterCasesTest {


    private final InsertionSorter insertionSorter = new InsertionSorter();
    private final BubbleSorter bubbleSorter = new BubbleSorter();
    private final SelectionSorter selectionSorter = new SelectionSorter();

    private final IntegerDataSetGenerator integerDataSetGenerator = new IntegerDataSetGenerator();
    private final Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1,o2);
        }
    };

    @Test
    public void bestCaseInsertion(){

            int i = 2;
            List list = integerDataSetGenerator.createAscending((int) Math.pow(10,i));
            List toCompare = integerDataSetGenerator.createAscending((int) Math.pow(10,i));

            insertionSorter.sort(comparator,list);

            Assert.assertTrue(list.equals(toCompare));

    }

    @Test
    public void worstCaseInsertion(){
        int i = 1;
        List list = integerDataSetGenerator.createDescending((int) Math.pow(10,i));
        List toCompare = integerDataSetGenerator.createAscending((int) Math.pow(10,i));

        insertionSorter.sort(comparator,list);

        Assert.assertTrue(list.equals(toCompare));
    }

    @Test
    public void averageCaseInsertion(){

        int i = 4;
        List list = integerDataSetGenerator.createRandom((int) Math.pow(10,i));
        //Revisar el insertion sorter
        insertionSorter.sort(comparator,list);


    }

    @Test
    public void bestCaseBubble(){

        int i = 2;
        List list = integerDataSetGenerator.createAscending((int) Math.pow(10,i));
        List toCompare = integerDataSetGenerator.createAscending((int) Math.pow(10,i));

        bubbleSorter.sort(comparator,list);

        Assert.assertTrue(list.equals(toCompare));

    }

    @Test
    public void worstCaseBubble(){
        int i = 6;
        List list = integerDataSetGenerator.createDescending((int) Math.pow(10,i));
        List toCompare = integerDataSetGenerator.createAscending((int) Math.pow(10,i));

        bubbleSorter.sort(comparator,list);

        Assert.assertTrue(list.equals(toCompare));
    }

    @Test
    public void averageCaseBubble(){

        int i = 1;
        List list = integerDataSetGenerator.createRandom((int) Math.pow(10,i));
        //Revisar el bubble sorter
        bubbleSorter.sort(comparator,list);


    }

    @Test
    public void bestCaseSelection(){

        int i = 1;
        List list = integerDataSetGenerator.createAscending((int) Math.pow(10,i));
        List toCompare = integerDataSetGenerator.createAscending((int) Math.pow(10,i));

        selectionSorter.sort(comparator,list);

        Assert.assertTrue(list.equals(toCompare));

    }

    @Test
    public void worstCaseSelection(){
        int i = 6;
        List list = integerDataSetGenerator.createDescending((int) Math.pow(10,i));
        List toCompare = integerDataSetGenerator.createAscending((int) Math.pow(10,i));

        selectionSorter.sort(comparator,list);

        Assert.assertTrue(list.equals(toCompare));
    }

    @Test
    public void averageCaseSlection(){

        int i = 1;
        List list = integerDataSetGenerator.createRandom((int) Math.pow(10,i));
        //Revisar el selection sorter
        selectionSorter.sort(comparator,list);


    }
}
