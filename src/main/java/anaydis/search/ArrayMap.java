package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ArrayMap<K,V> implements Map<K,V> {

    private final Comparator<K> comparator;
    private ArrayList<K> keys;
    private ArrayList<V> values;
    private int size;

    public ArrayMap(Comparator<K> comparator) {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.size = 0;
        this.comparator = comparator;
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
        return indexOf(key)>=0;
    }

    @Override
    public V get(@NotNull K key) {
        int index = indexOf(key);
        if (index>=0) return values.get(index);
        return null;
    }

    @Override
    public V put(@NotNull K key, V value) {
        int index = indexOf(key);
        if (index<0){
            index = -index -1;
            for (int i = index+1; i < size+1; i++) {
                keys.set(i,keys.get(i-1));
                values.set(i,values.get(i-1));
            }
            keys.set(index,key);
        }
        values.set(index,value);
        return value;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public Iterator<K> keys() {
        return keys.iterator();
    }

    private int indexOf(K key){
        return find(key,0,size-1);
    }

    private int find(K key, int lo, int hi){

        if (hi<lo) return -(lo+1);
        int middle = (hi+lo)/2;
        int comp = comparator.compare(key,keys.get(middle));

        if (comp<0) return find(key,lo,middle-1);
        else if (comp>0) return find(key,middle+1,hi);
        else return middle;
    }
}
