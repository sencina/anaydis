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

    @Test
    public void shellSorterTest(){
        int[] sizes = {100,1000,10000};
        System.out.println("n,list,type,comparations,exchanges");

        for (int i = 0; i < sizes.length; i++) {
            List<Integer> ascending = integerDataSetGenerator.createAscending(sizes[i]);
            List<Integer> descending = integerDataSetGenerator.createDescending(sizes[i]);
            List<Integer> random = integerDataSetGenerator.createRandom(sizes[i]);

            shellSorter.sort(comparator,ascending);
            System.out.println(sizes[i]+",ascending,"+shellSorter.getType()+','+shellSorter.getSorter().getListeners().get(0).getComparations()+','+shellSorter.getSorter().getListeners().get(0).getSwaps());
            shellSorter.getSorter().getListeners().get(0).reset();

            shellSorter.sort(comparator,descending);
            System.out.println(sizes[i]+",descending,"+shellSorter.getType()+','+shellSorter.getSorter().getListeners().get(0).getComparations()+','+shellSorter.getSorter().getListeners().get(0).getSwaps());
            shellSorter.getSorter().getListeners().get(0).reset();

            shellSorter.sort(comparator,random);
            System.out.println(sizes[i]+",random,"+shellSorter.getType()+','+shellSorter.getSorter().getListeners().get(0).getComparations()+','+shellSorter.getSorter().getListeners().get(0).getSwaps());
            shellSorter.getSorter().getListeners().get(0).reset();


        }

    }

    @Test
    public void shellSortEstableTest(){
        List<FullName> names = new ArrayList<>();
        List<FullName> sortedNames = new ArrayList<>();
        names.add(new FullName("Santiago","Encina"));
        names.add(new FullName("Juan Martin","Encina"));
        names.add(new FullName("Franciso","Encina"));
        names.add(new FullName("Martina","Encina"));
        names.add(new FullName("Maria Eugenia","Budic"));



        quickSorter.sort(new Comparator<FullName>() {
            @Override
            public int compare(FullName o1, FullName o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        },names);

        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i).toString());
        }
    }
}



