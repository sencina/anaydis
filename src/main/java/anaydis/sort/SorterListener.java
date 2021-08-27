package anaydis.sort;

public class SorterListener implements anaydis.sort.gui.SorterListener {

    private int comparations;
    private int swaps;

    public SorterListener() {
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
}
