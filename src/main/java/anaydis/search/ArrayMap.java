package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayMap<K,V> implements Map<K,V> {

    private ArrayList<K> keys;
    private ArrayList<V> values;
    private int size;

    public ArrayMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return Map.super.isEmpty();
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return false;
    }

    @Override
    public V get(@NotNull K key) {
        return null;
    }

    @Override
    public V put(@NotNull K key, V value) {
        return null;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public Iterator<K> keys() {
        return null;
    }

    private int indexOf(){
        return -1;
    }

    private int find(Comparator<K> comparator,K key, int lo, int hi){

        if (hi<lo) return -(lo+1);
        int middle = (hi+lo)/2;
        int comp = comparator.compare(key,keys.get(middle));

        if (comp<0) return find(comparator,key,lo,middle-1);
        else if (comp>0) return find(comparator,key,middle+1,hi);
        else return middle;
    }
}
