package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public class TSTMap<V> implements Map<String,V> {

    private Node<V> head;
    private int size;

    public TSTMap() {

        this.head  = null;
        this.size = 0;

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
        if (toReturn != null){
            return toReturn.value;
        }
        else return null;
    }

    @Override
    public V put(@NotNull String key, V value) {
        V previousValue = get(key);
        this.head = put(head,key,value,0);
        return previousValue;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public Iterator<String> keys() {

        List<String> toReturn = new ArrayList<>();
        fillList(toReturn,head,"");
        return toReturn.iterator();

    }

    public List<String> autoComplete(String prefix){
        List<String> toRetrun = new ArrayList<>();
        autoComplete(toRetrun,prefix,head,0,"");
        return toRetrun;
    }

    public List<String> wildCard(String prefix) {

        List<String> toReturn = new ArrayList<>();
        fillWildList(toReturn,head,prefix,"",0);
        return toReturn;

    }

    private void fillWildList(List<String> list, Node<V> node, String prefix, String current,int lvl) {

        if (node == null) return;

        else if (lvl == 0){

            if (node.chr == prefix.charAt(lvl)) fillWildList(list,node.middle,prefix,current+node.chr,lvl+1);
            else if (node.chr < prefix.charAt(lvl)) fillWildList(list,node.right,prefix,current,lvl);
            else fillWildList(list,node.left,prefix,current,lvl);
        }

        else if (lvl > 0 && lvl < prefix.length()-1){

            fillWildList(list,node.left,prefix,current,lvl);
            fillWildList(list,node.middle,prefix,current+node.chr,lvl+1);
            fillWildList(list,node.right,prefix,current,lvl);

        }

        else {

            if (node.chr == prefix.charAt(lvl)){
                if (node.value != null){
                    list.add(current+node.chr);
                }
            }

            else if (node.chr < prefix.charAt(lvl)) fillWildList(list,node.right,prefix,current,lvl);
            else fillWildList(list,node.left,prefix,current,lvl);

        }

    }

    private Node<V> find(Node<V> node, String key, int lvl) {

        if (node == null || lvl>=key.length()) return null;
        else {
            if (node.chr == key.charAt(lvl)){
                if (key.length() == lvl+1) return node;
                else return find(node.middle,key,lvl+1);
            }
            else{
                Node<V> next = (key.charAt(lvl) > node.chr) ? node.right : node.left;
                return find(next,key,lvl);
            }
        }

    }

    private void autoComplete(List<String> list, String prefix, Node<V> node, int lvl, String current) {

        if (node == null) return;
        else if (lvl < prefix.length()){

            char aux = prefix.charAt(lvl);

            if (node.chr> aux) autoComplete(list,prefix,node.left,lvl,current);
            else if(node.chr == aux) autoComplete(list,prefix,node.middle,lvl+1,current+aux);
            else autoComplete(list,prefix,node.right,lvl,current);
        }
        else {

            autoComplete(list,prefix,node.left,lvl,current);
            if (node.value != null) list.add(current + node.chr);
            autoComplete(list,prefix,node.middle,lvl+1,current + node.chr);
            autoComplete(list,prefix,node.right,lvl,current);

        }


    }

    private void fillList(List<String> list, Node<V> node, String current) {

        if (node == null) return;
        else {
            String aux = current+node.chr;
            fillList(list,node.left,current);
            if (node.value !=null){
                list.add(aux);
            }
            fillList(list,node.middle,aux);
            fillList(list,node.right,current);
        }


    }

    private Node<V> put(Node<V> node, String key, V value,int lvl){

        if (node == null){

            Node<V> toReturn = new Node<>(key.charAt(lvl));
            if (lvl+1 < key.length()) toReturn.middle = put(toReturn.middle,key,value,lvl+1);
            else {
                toReturn.value = value;
                size++;
            }
            return toReturn;
        }
        else if(key.charAt(lvl) == node.chr){

            if (lvl+1 == key.length()){
                if (node.value == null) size++;
                node.value = value;
            }
            else node.middle = put(node.middle,key,value,lvl+1);
            return node;
        }
        else if (key.charAt(lvl)>node.chr){
            node.right = put(node.right,key,value,lvl);
            return node;
        }
        else {
            node.left = put(node.left,key,value,lvl);
            return node;
        }

    }

    private class Node<V>{

        private final char chr;
        private V value;
        private Node<V> left;
        private Node<V> middle;
        private Node<V> right;

        public Node(char chr, V value) {
            this.chr = chr;
            this.value = value;
            this.left = null;
            this.middle = null;
            this.right = null;
        }

        public Node(char key){
            this(key,null);
        }
    }
}
