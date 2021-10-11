package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RWayTrieMap<V> implements Map<String, V> {

    private int size;
    private Node<V> head;
    private V previousValue;

    public RWayTrieMap() {
        this.size = 0;
        this.head = null;
        this.previousValue = null;
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
        head = put(head,key,value,0);
        return previousValue;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public Iterator<String> keys() {
        List<String> toReturn =  new ArrayList<>();
        addKeys(head,"",toReturn);
        return toReturn.iterator();
    }

    public List<String> autoComplete(String prefix){
        List<String> toReturn = new ArrayList<>();
        autoComplete(toReturn,head,"",prefix,0);
        return toReturn;
    }

    private Node<V> find(Node<V> node, @NotNull String key, int level) {
        if (node == null) return null;
        else if(level == key.length()) return node;
        else {
            int nextIndex = key.charAt(level);
            return find(node.next[nextIndex],key,level+1);
        }
    }

    private Node<V> put(Node<V> node, @NotNull String key, V value, int level){

        if (node == null){

            Node<V> result = new Node<>();

            if (level<key.length()){
                int nextIndex = key.charAt(level);
                result.next[nextIndex] = put(result.next[nextIndex],key,value,level+1);
            }
            else {
                previousValue = result.value;
                result.value = value;
                size++;
            }

            return result;
        }

        else if (level == key.length()) {

            previousValue = node.value;
            node.value = value;
            return node;

        }

        else {
            int nextIndex = key.charAt(level);
            node.next[nextIndex] = put(node.next[nextIndex], key, value, level + 1);
            return node;
        }

    }

    private void addKeys(Node<V> node,String key,List<String> list) {

        Node<V>[] aux = node.next;
        for (int i = 0; i < 256; i++) {
            if (aux[i] != null){
                Node<V> otherNode = aux[i];
                String otherKey = key + (char)i;
                if (otherNode.value != null) list.add(otherKey);
                addKeys(otherNode,otherKey,list);
            }
        }


    }

    private void autoComplete(List<String> list, Node<V> node, String current, String prefix, int lvl){

        if (node == null) return;

        else if (lvl< prefix.length()){

                int index = prefix.charAt(lvl);

                if (node.next[index] != null) autoComplete(list,node.next[index],current+(char) index,prefix,lvl+1);

        }

        else {

            for (int i = 0; i < node.next.length; i++) {

                if (node.next[i] != null ){

                    if (node.next[i].value != null){
                        list.add(current+(char)i);
                    }

                    autoComplete(list,node.next[i],current+(char) i,prefix,lvl+1);
                }

            }

        }

    }

    private class Node<V>{

        private V value;
        private Node<V>[] next;

        public Node(V value) {
            this.value = value;
            this.next = (Node<V>[]) new Node[256];
            for (int i = 0; i < 256; i++) {
                next[i] = null;
            }
        }

        public Node(){
            this(null);
        }

    }
}