package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class RWayTrieMap <V> implements Map<String,V> {

    private Node<V>head=null;
    private int size;
    private V previousValue;

    public RWayTrieMap() {
        this.head = null;
        this.size = 0;
        this.previousValue = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(head,key,0)!=null;
    }

    @Override
    public V get(@NotNull String key) {
        Node<V> node=find(head,key,0);
        if (node.value==null)return null;
        return node.value;
    }

    @Override
    public V put(@NotNull String key, V value) {
        head=put(head,key,value,0);
        return previousValue;
    }

    private Node<V> put(Node<V>node, String key, V value, int level) {
        if (node==null){
            Node<V>result=new Node<V>(null);
            if (level < key.length()){
                int next=(int)key.charAt(level);
                result.next[next]=put(result.next[next],key,value,level+1);
            }else{
                previousValue=result.value;
                result.value=value;
                size++;
            }
            return result;
        }
        if (level==key.length()){
            previousValue=node.value;
            node.value=value;
            return node;
        }else {
            int next=(int)key.charAt(level);
            node.next[next]=put(node.next[next],key,value,level+1);
            return node;
        }
    }

    @Override
    public void clear() {
        head=null;
        size=0;
    }

    @Override
    public Iterator<String> keys() {
        ArrayList<String>keys=new ArrayList<>();
        createKlist(head,"",keys);
        return keys.iterator();
    }

    private void createKlist(Node<V> node,String key, ArrayList<String> keys) {
        Node<V>[]next=node.next;
        for (int i = 0; i < 256; i++) {
            if (next[i]!=null){
                Node<V>auxNode=next[i];
                String posKey=key+(char)i;
                if (auxNode.value!=null){
                    keys.add(posKey);
                }
                createKlist(auxNode,posKey,keys);
            }
        }
    }

    private Node<V> find(Node<V>node,String key,int level){
        if (node==null)return null;
        if (level==key.length())return node;
        int next=(int)key.charAt(level);
        return find(node.next[next],key,level+1 );
    }

    private class Node<V>{
        private V value;
        private Node<V>[]next;
        private Node(V value){
            this.value=value;
            next=(Node<V>[])new Node[256];
            for (int i = 0; i <256 ; i++) {
                next[i]=null;
            }
        }
    }

}