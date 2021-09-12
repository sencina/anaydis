package anaydis.search;


import org.jetbrains.annotations.NotNull;

import java.util.*;


public class RandomizedTreeMap<K,V> implements Map<K,V> {

    private int size;
    private Node<K,V> head;
    private Comparator<K> comparator;
    private V previousValue;
    private final Random random;

    public RandomizedTreeMap(Comparator<K> comparator) {
        this.size = 0;
        this.head = null;
        this.comparator = comparator;
        this.previousValue = null;
        this.random = new Random();
    }

    @Override
    public boolean isEmpty() {
        return Map.super.isEmpty();
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
        if (toReturn!=null)return toReturn.getValue();
        else return null;
    }

    @Override
    public V put(@NotNull K key, V value) {
        if (random.nextDouble()>0.5) head = rootPut(head,key,value);
        else head = put(head,key,value);

        V toReturn = previousValue;
        previousValue = null;
        return toReturn;
    }

    @Override
    public void clear() {
        this.size =0;
        this.head = null;
    }

    @Override
    public Iterator<K> keys() {
        List<K> list = new ArrayList<>();
        fillList(list,head);
        return list.iterator();
    }

    private Node<K,V> put(Node<K,V> node,K key, V value) {
        if (node == null){
            size++;
            return new Node<>(key,value);
        }

        else {

            int comp = comparator.compare(key,node.getKey());

            if (comp>0) node.setRight(put(node.getRight(),key,value));
            else if (comp<0) node.setLeft(put(node.getLeft(),key,value));
            else {
                previousValue = node.getValue();
                node.setValue(value);
            }

            return node;
        }
    }

    private Node<K,V> rootPut(Node<K,V> node, @NotNull K key, V value) {
        if (node == null){
            size++;
            return new Node<>(key,value);
        }

        else {

            int comp = comparator.compare(key,node.getKey());

            if (comp > 0){
                node.setRight(rootPut(node.getRight(),key,value));
                return rotateLeft(node);
            }

            else if (comp < 0){
                node.setLeft(rootPut(node.getLeft(),key,value));
                return rotateRight(node);
            }

            else {
                previousValue = node.getValue();
                node.setValue(value);
                return node;
            }

        }
    }

    private Node<K,V> find(Node<K,V> node,@NotNull K key) {
        if (node == null) return null;

        else{
            int comp = comparator.compare(key,node.getKey());

            if (comp>0) return find(node.getRight(),key);
            else if (comp<0) return find(node.getLeft(),key);
            else return node;
        }
    }

    private void fillList(List<K> list, Node<K,V> node) {
        if (node != null){
            // orden ascendente
            fillList(list,node.getLeft());
            list.add(node.getKey());
            fillList(list,node.getRight());
        }
    }

    private Node<K,V> rotateRight(Node<K,V> node) {
        Node<K,V> toReturn = new Node<>(node.getLeft().getKey(), node.getLeft().getValue(), node.getLeft().getLeft(), node.getLeft().getLeft());
        node.setLeft(toReturn.getRight());
        toReturn.setRight(node);
        return toReturn;
    }

    private Node<K,V> rotateLeft(Node<K,V> node) {
        Node<K,V> toReturn = new Node<>(node.getRight().getKey(), node.getRight().getValue(), node.getRight().getLeft(), node.getRight().getRight());
        node.setRight(toReturn.getLeft());
        toReturn.setLeft(node);
        return toReturn;
    }

}
