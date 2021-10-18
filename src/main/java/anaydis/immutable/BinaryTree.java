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
        int size = containsKey(key) ? this.size : this.size + 1;
        Node<K,V> aux = put(root,key,value);
        return new BinaryTree<>(comparator,aux,size);
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

    private Node<K,V> put(Node<K,V> node, K key, V value){

        if (node == null) return new Node<>(key,value);
        else {
            Node<K,V> result;
            if (comparator.compare(key,node.getKey()) < 0){
                Node<K,V> left = put(node.getLeft(),key,value);
                result = new Node<>(key,value,left,node.getRight());
            }
            else if (comparator.compare(key,node.getKey()) == 0){
                Node<K,V> aux = node.getCopy();
                aux.setValue(value);
                return aux;
            }
            else {
                Node<K,V> right = put(node.getRight(),key,value);
                result = new Node<>(key,value,node.getLeft(),right);
            }
            return result;
        }

    }

}
