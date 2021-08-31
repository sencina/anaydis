package anaydis.sort;

import anaydis.sort.auxClass.SorterProviderImplementation;
import anaydis.sort.dataSetsGenerator.IntegerDataSetGenerator;

/**
 * Sorter tests should subclass this abstract implementation
 */
abstract class SorterTest extends AbstractSorterTest {

    private SorterProviderImplementation provider = new SorterProviderImplementation();

    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Override protected SorterProvider getSorterProvider() {
        return provider;
    }
}
