package anaydis.sort;

import anaydis.sort.gui.SorterListener;

public class SorterListenerImplementation implements SorterListener {

    private int comparations;
    private int swaps;

    public SorterListenerImplementation() {
        this.comparations = 0;
        this.swaps = 0;
    }

    @Override
    public void box(int from, int to) {

    }

    @Override
    public void copy(int from, int to, boolean copyToAux) {

    }

    @Override
    public void equals(int i, int j) {

    }

    @Override
    public void greater(int i, int j) {
        comparations++;
    }

    @Override
    public void swap(int i, int j) {
        swaps++;
    }

    public void reset(){
        this.swaps = 0;
        this.comparations = 0;
    }

    public int getComparations() {
        return comparations;
    }

    public int getSwaps() {
        return swaps;
    }
}
