package anaydis.compression;



import anaydis.bit.Bits;
import anaydis.bit.BitsOutputStream;
import anaydis.search.ArrayMap;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


public class Huffman implements Compressor{

    Comparator<Integer> integerComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1,o2);
        }
    };

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        ArrayMap<Integer, Integer> frequencies = new ArrayMap<Integer, Integer>(Comparator.naturalOrder());
        Queue<Integer> text = new LinkedList<>();
        int length = fillFrequencies(input,frequencies,text);

        PriorityQueue<Node> queue = fillPriorityQueue(frequencies);
        Node tree = generateTree(queue);

        ArrayMap<Integer,Bits> table = generateTable(tree);

        writeTable(table,output);
        output.write(0xFF);
        writeInt(length,output);
        output.write(0xFF);
        writeEncode(table,text,output);

    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        ArrayMap<Bits, Integer> table = readTable(input);
        int length = readInt(input);
        int i = 0;
        int current = input.read();
        Bits pivot = new Bits();

        while (current != -1 && i < length){

            for (int j = 7; j >= 0 && i<length; j--) {

                pivot.add(bitAt(current,j));

                if (table.containsKey(pivot)){
                    output.write(table.get(pivot));
                    pivot = new Bits();
                    i++;
                }

            }

            current = input.read();
        }

    }

    private boolean bitAt(int bit, int pos) {
        return pos < 8 && (bit >>> pos & 1) != 0;
    }

    private int readInt(InputStream input) throws IOException {

        int length = 0;
        int character = input.read();

        while (character != 0xFF){

            length+= character;
            character = input.read();

        }

        return length;
    }

    private ArrayMap<Bits, Integer> readTable(InputStream input) throws IOException {

        ArrayMap<Bits,Integer> table = new ArrayMap<>(new BitsComparator());

        int character = input.read();

        while (character != 0xFF){

            int length = input.read();
            int value = input.read();

            table.put(new Bits(value,length),character);

            character = input.read();

        }

        return table;

    }

    private void writeEncode(@NotNull ArrayMap<Integer, Bits> table,@NotNull Queue<Integer> queue,@NotNull OutputStream output) throws IOException {

        Bits actual = new Bits();
        Bits aux = new Bits();

        while (!queue.isEmpty()){

            if (actual.isFull()){
                output.write(actual.value);
                actual = aux;
            }

            aux = actual.add(table.get(queue.poll()));


        }

        if (!actual.isEmpty()){
            output.write(actual.value << 8 - actual.length);

            if (actual.isFull()){
                output.write(aux.value << 8-aux.length);
            }

        }

    }

    private void writeInt(int length, OutputStream output) throws IOException {


        while (length-127>0){
            output.write(127);
            length-= 127;
        }

        output.write(length);

    }

    private void writeTable(ArrayMap<Integer, Bits> table, @NotNull OutputStream output) throws IOException {

        Iterator<Integer> iterator = table.keys();

        while (iterator.hasNext()){

            int character = iterator.next();
            Bits bits = table.get(character);

            output.write(character);
            output.write(bits.length);
            output.write(bits.value);

        }

    }

    private ArrayMap<Integer, Bits> generateTable(Node node) {

        ArrayMap<Integer,Bits> map = new ArrayMap<Integer, Bits>(Comparator.naturalOrder());

        generateTableRecursive(node,map,new Bits());
        return map;
    }

    private void generateTableRecursive(Node node, ArrayMap<Integer, Bits> map, Bits bits){

        if (node.isLeaf()) map.put(node.value, bits);
        else {

            Bits left = bits.copy();
            Bits right= bits.copy();

            generateTableRecursive(node.left,map,left.add(false));
            generateTableRecursive(node.right,map,right.add(true));

        }

    }

    private Node generateTree(PriorityQueue<Node> queue) {

        while (queue.size()>1){

            Node left = queue.poll();
            Node right = queue.poll();

            queue.add(new Node(null,left.frequency+ right.frequency,left,right));

        }

        return queue.poll();

    }

    private PriorityQueue<Node> fillPriorityQueue(ArrayMap<Integer, Integer> frequencies) {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        Iterator<Integer> iterator = frequencies.keys();

        while (iterator.hasNext()){

            int key = iterator.next();
            int value = frequencies.get(key);

            queue.add(new Node(key,value));

        }

        return queue;
    }

    private int fillFrequencies(InputStream input, ArrayMap<Integer, Integer> frequencies, Queue<Integer> text) throws IOException {

        int character = input.read();


        while (character != -1){

            text.add(character);

            int quantity = 1;


            if (frequencies.containsKey(character)){
                quantity+= frequencies.get(character);
            }

            frequencies.put(character,quantity);

            character = input.read();

        }

        return text.size();
    }


    private class Node implements Comparable<Node>{

        private final Integer value;
        private final Integer frequency;
        private Node left;
        private Node right;
        private int height;

        public Node(Integer value, Integer frequency, Node left, Node right) {
            this.value = value;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
            this.height = (right.height> left.height) ? right.height + 1 : left.height + 1;
        }

        public Node(Integer value, Integer frequency){
            this.value = value;
            this.frequency = frequency;
        }



        @Override
        public int compareTo(@NotNull Node o) {
            int cmp = frequency.compareTo(o.frequency);

            int f1 = cmp > 0 ? frequency : o.frequency;
            int f2 = cmp > 0 ? o.frequency : frequency;

            if(f2*4 < f1) return cmp;

            int r = height - o.height;
            int hcmp = r > 1 ? 1 : r < -1 ? -1 : 0;
            return hcmp != 0 ? hcmp : cmp;
        }

        public boolean isLeaf(){
            return this.left == null && this.right == null;
        }

    }

    private class BitsComparator implements Comparator<Huffman.Bits>{

        @Override
        public int compare(Huffman.Bits o1, Huffman.Bits o2) {
            int cmp = Integer.compare(o1.getValue(), o2.getValue());
            return cmp == 0 ? Integer.compare(o1.getLength(), o2.getLength()) : cmp;
        }

    }

    private class Bits extends anaydis.bit.Bits {

        private int value;
        private byte length;

        public Bits(int value, int length) {
            this.value = value;
            this.length = (byte) length;
        }

        public Bits() {
        }

        public Bits add(boolean bit) {
            value = (value << 1) | (bit ? 1 : 0);
            length++;
            return this;
        }

        public int getValue() { return value; }

        public int getLength() { return length; }

        public Bits copy() {
            final Bits bits = new Bits();
            bits.value = value;
            bits.length = length;
            return bits;
        }

        @Override public String toString() {
            final StringBuilder builder = new StringBuilder();
            int aux = 1 << length;
            while(aux > 1) {
                aux = aux >> 1;
                builder.append((value & aux) == 0 ? "0":"1");
            }
            return builder.toString();
        }

        Bits add(@NotNull Bits b){
            if(length == 8) return b;
            int emptyBits = 8 - length;
            if(emptyBits >= b.length){
                value = value << b.length | b.value;
                length += b.length;
                return new Bits();
            }
            int k = b.length - emptyBits;
            length = 8;
            value = value << emptyBits | b.value >>> k;
            return new Bits((b.value << (8-k)) >>> (8-k) , k);
        }

        boolean isEmpty(){return length == 0;}
        boolean isFull(){return length == 8;}

    }

}
