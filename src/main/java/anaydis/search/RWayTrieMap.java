package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RWayTrieMap<V> implements Map<String, V> {

    private int size;
    private Node<V> head;

    public RWayTrieMap() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(head,key,0) != null;
    }

    @Override
    public V get(@NotNull String key) {
        Node<V> toReturn = find(head,key,0);
        if (toReturn!=null)return toReturn.value;
        else return null;
    }

    @Override
    public V put(@NotNull String key, V value) {
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public Iterator<String> keys() {
        List<String> toReturn =  new ArrayList<String>();
        addKeys(head,toReturn);
        return toReturn.iterator();
    }

    private Node<V> find(Node<V> node, String key, int level) {
        if (node == null) return null;
        else if(level == key.length()) return node;
        else {
            int nextIndex = key.charAt(level);
            return find(node.next.get(nextIndex),key,level+1);
        }
    }

    private Node<V> put(Node<V> node, String key, V value, int level){
        if (node == null){

            Node<V> result = new Node<>();

            if (level<key.length()){
                int nextIndex = key.charAt(level);
                result.next.set(nextIndex,put(result.next.get(nextIndex),key,value,level+1));
            }
            else {
                result.value = value;
                size++;
            }

            return result;
        }

        else if(level == key.length()){
            node.value = value;
            return node;
        }

        else {
            int nextIndex = key.charAt(level);
            node.next.set(nextIndex,put(node.next.get(nextIndex),key,value,level+1));
            return node;
        }
    }

    private void addKeys(Node<V> node,List<String> list) {
        if (node == null) return;
        else {
            list.add(node.key);
            for (int i = 0; i < node.next.size(); i++) {
                addKeys(node.next.get(i),list);
            }
        }
    }

    private class Node<V>{

        private V value;
        private String key;
        private List<Node<V>> next;

        public Node(String key,V value) {
            this.value = value;
            this.key = key;
            this.next = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                next.add(null);
            }
        }

        public Node(){
            new Node<V>(null,null);
        }

    }
}
