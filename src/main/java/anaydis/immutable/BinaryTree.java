package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

import anaydis.search.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<K,V> implements Map<K,V>{

    private final int size;
    private final Comparator<K> comparator;
    private final Node<K,V> root;

    public BinaryTree(Comparator<K> comparator, Node<K, V> root, int size) {
        this.size = size;
        this.comparator = comparator;
        this.root = root;
    }

    public BinaryTree(Comparator<K> comparator) {
        this(comparator,null,0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(root,key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        Node<K,V> toReturn = find(root,key);
        return (toReturn == null) ? null : toReturn.getValue();
    }

    @Override
    public Map<K, V> put(@NotNull K key, V value) {
        Result<K,V> result = put(root,key,value);
        return new BinaryTree<>(comparator,result.node,(result.inserted) ? this.size+1 : this.size);
    }

    @Override
    public Iterator<K> keys() {
        List<K> toReturn = new ArrayList<>();
        fillList(new ArrayList<>(),root);
        return toReturn.iterator();
    }

    private void fillList(ArrayList<K> list, Node<K,V> node) {
        if (node == null) return;
        else {

            fillList(list,node.getLeft());
            list.add(node.getKey());
            fillList(list,node.getRight());

        }
    }

    private Node<K,V> find(Node<K,V> node, K key) {
        if(node == null) return null;
        else {

            if (comparator.compare(key, node.getKey())<0) return find(node.getLeft(), key);
            else if (comparator.compare(key, node.getKey())>0) return find(node.getRight(), key);
            else return node;

        }
    }

    private Result<K,V> put(Node<K,V> node, K key, V value){

        if (node == null) return new Result<>(new Node<>(key, value),true);
        else {
            Result<K,V> result;
            if (comparator.compare(key,node.getKey()) < 0){
                Result<K,V> leftResult = put(node.getLeft(),key,value);
                result = new Result<>(new Node<>(node.getKey(), node.getValue(), leftResult.node, node.getRight()), leftResult.inserted);
            }
            else if (comparator.compare(key,node.getKey()) == 0){
                return new Result<>(new Node<>(node.getKey(), value, node.getLeft(), node.getRight()),false);
            }
            else {
                Result<K,V> rightResult = put(node.getRight(),key,value);
                result = new Result<>(new Node<>(node.getKey(), node.getValue(), node.getLeft(), rightResult.node), rightResult.inserted);
            }
            return result;
        }

    }

    private class Result<K,V>{

        private final Node<K,V> node;
        private final boolean inserted;

        public Result(Node<K, V> node, boolean inserted) {
            this.node = node;
            this.inserted = inserted;
        }
    }

}
