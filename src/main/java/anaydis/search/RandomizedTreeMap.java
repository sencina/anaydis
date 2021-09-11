package anaydis.search;


import org.jetbrains.annotations.NotNull;

import java.util.*;


public class RandomizedTreeMap<K,V> implements Map<K,V> {

    private int size;
    private Node<K,V> head;
    private final Comparator<K> comparator;
    private final Random random;
    private V previousValue;

    public RandomizedTreeMap(Comparator<K> comparator) {
        this.size = 0;
        this.head = null;
        this.comparator = comparator;
        this.random = new Random();
        this.previousValue = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head,key) != null;
    }

    @Override
    public V get(@NotNull K key) {

        Node<K,V> toReturn = find(head,key);
        if (toReturn == null) return null;
        return toReturn.getValue();
    }

    @Override
    public V put(@NotNull K key, V value) {

        if (random.nextDouble()<0.5) head = put(head,key,value);
        else head = rootPut(head,key,value);

        V toReturn = previousValue;
        previousValue = null;
        return toReturn;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public Iterator<K> keys() {
        List<K> toReturn = new ArrayList<>();
        if(head != null) {
            fillList(toReturn, head);
        }
        return toReturn.iterator();
    }

    private void fillList(List<K> list, Node<K,V> node) {
        if (node != null) {
            fillList(list, node.getLeft());
            list.add(head.getKey());
            fillList(list, node.getLeft());
        }
    }

    private Node<K,V> rootPut(Node<K,V> node,@NotNull K key, V value){

        if (node == null){
            size++;
            return new Node<>(key,value);
        }

        else {

            int comp = comparator.compare(key, node.getKey());

            if (comp<0){
                node.setLeft(rootPut(node.getLeft(),key,value));
                return rotateRight(node);}
            else if (comp>0){
                node.setRight(rootPut(node.getRight(),key,value));
                return rotateLeft(node);}
            else previousValue = node.getValue();node.setValue(value);return node;
        }
    }

    private Node<K,V> put(Node<K,V> node,K key, V value){
        if (node == null){
            size++;
            return new Node<>(key,value);
        }
        else {
            int comp = comparator.compare(key, node.getKey());
            if (comp<0) node.setLeft(put(node.getLeft(),key,value));
            if (comp>0) node.setRight(put(node.getRight(),key,value));
            else previousValue = node.getValue();node.setValue(value);
        }
        return node;
    }

    private Node<K,V> find(Node<K,V> node, K key){
        if (node == null) return null;
        int comp = comparator.compare(key,node.getKey());
        if (comp > 0) return find(node.getRight(),key);
        else if (comp < 0) return find(node.getLeft(),key);
        else return node;
    }



    private Node<K,V> rotateLeft(Node<K,V> node){
        Node<K,V> toReturn = node.getRight().copy();
        node.setRight(toReturn.getLeft());
        toReturn.setLeft(node);
        return toReturn;
    }

    private Node<K,V> rotateRight(Node<K,V> node){
        Node<K,V> toReturn = node.getLeft().copy();
        node.setLeft(toReturn.getRight());
        toReturn.setRight(node);
        return toReturn;
    }


}
