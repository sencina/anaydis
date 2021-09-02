package anaydis.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class TestPractice02 extends SorterTest {

    //~ Methods ..................................................................................................................

    /** Test BubbleSorter with String generator. */
    @Test
    public void testBubbleWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.BUBBLE, 10);
        testSorter(createStringDataSetGenerator(), SorterType.BUBBLE, 50);
        testSorter(createStringDataSetGenerator(), SorterType.BUBBLE, 100);
    }

    /** Test BubbleSorter with Integer generator. */
    @Test public void testBubbleWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.BUBBLE, 100);
    }

    /** Test InsertionSorter with String generator. */
    @Test public void testInsertionWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.INSERTION, 10);
        testSorter(createStringDataSetGenerator(), SorterType.INSERTION, 50);
        testSorter(createStringDataSetGenerator(), SorterType.INSERTION, 100);
    }

    /** Test InsertionSorter with Integer generator. */
    @Test public void testInsertionWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.INSERTION, 100);
    }

    /** Test SelectionSorter with String generator. */
    @Test public void testSelectionWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.SELECTION, 10);
        testSorter(createStringDataSetGenerator(), SorterType.SELECTION, 50);
        testSorter(createStringDataSetGenerator(), SorterType.SELECTION, 100);
    }

    /** Test SelectionSorter with Integer generator. */
    @Test public void testSelectionWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.SELECTION, 100);
    }

    /** Test QuickSorter with String generator. */
    @Test public void testQuickWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.QUICK, 10);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK, 50);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK, 100);
    }

    /** Test QuickSorter with Integer generator. */
    @Test public void testQuickWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK, 100);
    }

    /** Test HSorter with String generator. */
    @Test public void testHWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.H, 10);
        testSorter(createStringDataSetGenerator(), SorterType.H, 50);
        testSorter(createStringDataSetGenerator(), SorterType.H, 100);
    }

    /** Test HSorter with Integer generator. */
    @Test public void testHWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.H, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.H, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.H, 100);

    }

    /** Test ShellSorter with String generator. */
    @Test public void testShellWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.SHELL, 10);
        testSorter(createStringDataSetGenerator(), SorterType.SHELL, 50);
        testSorter(createStringDataSetGenerator(), SorterType.SHELL, 100);
    }

    /** Test ShellSorter with Integer generator. */
    @Test public void testShellWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.SHELL, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.SHELL, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.SHELL, 100);

    }

    /** Test QuickSorter with String generator. */
    @Test public void testQuickNonRecursiveWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 10);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 50);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 100);
    }

    /** Test QuickSorter with Integer generator. */
    @Test public void testQuickNonRecursiveWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 100);
    }


}