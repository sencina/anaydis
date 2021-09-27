package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class BinaryTrieMap<V> implements Map<String,V> {

    private int size;
    private Node<V> head;

    public BinaryTrieMap() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(key, head,0) != null;
    }

    @Override
    public V get(@NotNull String key) {
        Node<V> toReturn = find(key, head,0);
        if (toReturn !=null) return toReturn.value;
        else return null;
    }

    @Override
    public V put(@NotNull String key, V value) {
        V previousValue = get(key);
        head = put(head,new Node<>(key,value),0);
        return previousValue;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public Iterator<String> keys() {
        return null;
    }

    private Node<V> put(Node<V> node, Node<V> value, int lvl) {
        if (node == null){
            size++;
            return value;
        }
        else if (node.isLeaf()){

            if (node.key.equals(value.key)){
                node.value = value.value;
                return node;
            }
            else{
                size++;
                return split(value,node,lvl);
            }

        }

        else {

            if (bitAt(value.key,lvl)) node.right = put(node.right,value,lvl+1);
            else node.left = put(node.left,value,lvl+1);
            return node;
        }

    }

    private Node<V> split(Node<V> a, Node<V> b, int lvl) {

        Node<V> result = new Node<>();

        int switchCase = toInt(bitAt(a.key,lvl))*2+ toInt(bitAt(b.key,lvl));
        switch (switchCase) {
            case 0: result.left = split(a, b, lvl + 1); break;
            case 3: result.right = split(a, b, lvl + 1); break;
            case 1: result.left = a; result.right = b; break;
            case 2: result.left = b; result.right = a; break;
        }

        return result;

    }

    private Node<V> find(String key, Node<V> node, int lvl) {
        if (node == null) return null;
        else if(node.isLeaf()) return (key.equals(node.key)) ? node : null;
        else return find(key,(bitAt(key,lvl)) ? node.right : node.left,lvl+1);
    }

    private boolean bitAt(String word, int nth) {
        int pos = nth/8;
        return pos < word.length() && (byte) (word.charAt(pos) >> (nth %8) & 1) !=0;
    }

    private int toInt(boolean a) {
        return a ? 1 : 0;
    }

    private class Node<V> {


        private final String key;
        private V value;
        private Node<V> left;
        private Node<V> right;

        public Node(String key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(){
            this(null,null);
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }
    }
}
