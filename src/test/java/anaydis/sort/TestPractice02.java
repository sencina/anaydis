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

    /** Test QuickNonRecursiveSorter with String generator. */
    @Test public void testQuickNonRecursiveWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 10);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 50);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 100);
    }

    /** Test QuickNonRecursiveSorter with Integer generator. */
    @Test public void testQuickNonRecursiveWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_NON_RECURSIVE, 100);
    }

    /** Test QuickCut with String generator. */
    @Test public void testQuickCutWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_CUT, 10);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_CUT, 50);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_CUT, 100);
    }

    /** Test QuickCutSorter with Integer generator. */
    @Test public void testQuickCutWithIntegerGenerator() {
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_CUT, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_CUT, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_CUT, 100);
    }

    /** Test QuickMedOf3 with String generator. */
    @Test public void testQuickMedOf3WithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 10);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 50);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 100);
    }

    /** Test QuickMedOf3 with Integer generator. */
    @Test public void testQuickMedOf3WithIntegerGenerator(){
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_MED_OF_THREE, 100);
    }

    /** Test QuickThreePartition with String generator. */
    @Test public void testQuickThreePartitionWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 10);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 50);
        testSorter(createStringDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 100);
    }

    /** Test QuickThreePartition with Integer generator. */
    @Test public void testQuickThreePartitionWithIntegerGenerator(){
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.QUICK_THREE_PARTITION, 100);
    }

    /** Test Merge Top Down with String generator. */
    @Test public void testMergeSortTDWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.MERGE_TOP_DOWN, 10);
        testSorter(createStringDataSetGenerator(), SorterType.MERGE_TOP_DOWN, 50);
        testSorter(createStringDataSetGenerator(), SorterType.MERGE_TOP_DOWN, 100);
    }

    /** Test Merge Top Down with Integer generator. */
    @Test public void testMergeSortTDWithIntegerGenerator(){
        testSorter(createIntegerDataSetGenerator(), SorterType.MERGE_TOP_DOWN, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.MERGE_TOP_DOWN, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.MERGE_TOP_DOWN, 100);
    }

    /** Test Merge Bottom Up with String generator. */
    @Test public void testMergeSortBUWithStringGenerator() {
        testSorter(createStringDataSetGenerator(), SorterType.MERGE_BOTTOM_UP, 10);
        testSorter(createStringDataSetGenerator(), SorterType.MERGE_BOTTOM_UP, 50);
        testSorter(createStringDataSetGenerator(), SorterType.MERGE_BOTTOM_UP, 100);
    }

    /** Test Merge Bottom Up with Integer generator. */
    @Test public void testMergeSortBUWithIntegerGenerator(){
        testSorter(createIntegerDataSetGenerator(), SorterType.MERGE_BOTTOM_UP, 10);
        testSorter(createIntegerDataSetGenerator(), SorterType.MERGE_BOTTOM_UP, 50);
        testSorter(createIntegerDataSetGenerator(), SorterType.MERGE_BOTTOM_UP, 100);
    }


}