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
        return toReturn.value;
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
            fillList(list, node.left);
            list.add(head.key);
            fillList(list, node.right);
        }
    }

    private Node<K,V> rootPut(Node<K,V> node,@NotNull K key, V value){

        if (node == null){
            size++;
            return new Node<>(key,value);
        }

        else {

            int comp = comparator.compare(key, node.key);

            if (comp<0){
                node.left = rootPut(node.left,key,value);
                return rotateRight(node);}
            else if (comp>0){
                node.right = rootPut(node.right,key,value);
                return rotateLeft(node);}
            else previousValue = node.value;node.value = value;return node;
        }
    }

    private Node<K,V> put(Node<K,V> node,K key, V value){
        if (node == null){
            size++;
            return new Node<>(key,value);
        }
        else {
            int comp = comparator.compare(key, node.key);
            if (comp<0) node.left = put(node.left,key,value);
            if (comp>0) node.right = put(node.right,key,value);
            else previousValue = node.value;node.value = value;
        }
        return node;
    }

    private Node<K,V> find(Node<K,V> node, K key){
        if (node == null) return null;
        int comp = comparator.compare(key,node.key);
        if (comp > 0) return find(node.right,key);
        else if (comp < 0) return find(node.left,key);
        else return node;
    }

    private class Node<K, V> {

        private K key;
        private V value;
        private Node<K,V> left;
        private Node<K,V> right;

        public Node() {
            new Node<>(null,null);
        }

        public Node(K key, V value) {
            new Node<>(key,value,null,null);
        }
        public Node(K key, V value, Node<K, V> left, Node<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node<K,V> copy() {
            return new Node<>(this.key,this.value,this.left,this.right);
        }

        @Override
        public String toString() {
            return key+"= "+value;
        }
    }

    private Node<K,V> rotateLeft(Node<K,V> node){
        Node<K,V> toReturn = node.right.copy();
        node.right = toReturn.left;
        toReturn.left = node;
        return toReturn;
    }

    private Node<K,V> rotateRight(Node<K,V> node){
        Node<K,V> toReturn = node.left.copy();
        node.left = toReturn.right;
        toReturn.right = node;
        return toReturn;
    }


}
