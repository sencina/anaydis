package anaydis.compression;

import anaydis.search.Map;
import anaydis.search.RandomizedTreeMap;
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

        final Map<Integer,Integer> frequencies = new RandomizedTreeMap<>(integerComparator);
        fillFrequencies(input,frequencies);

        final Queue<Node> priorityQueue = new PriorityQueue<>();
        fillPriorityQueue(priorityQueue,frequencies);

        final Node trie = generateTrie(priorityQueue);



    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

    }

    private Node generateTrie(Queue<Node> priorityQueue) {

        while (priorityQueue.size() > 1){

            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            priorityQueue.add(new Node(null, left.frequency+ right.frequency,left,right));

        }

        return priorityQueue.poll();
    }

    private void fillPriorityQueue(Queue<Node> queue, Map<Integer, Integer> map) {

        List<Integer> keys = (List<Integer>) map.keys();

        for (int i = 0; i < keys.size(); i++) {

            Node aux = new Node(keys.get(i),map.get(keys.get(i)));
            queue.add(aux);

        }

    }

    private void fillFrequencies(InputStream input, Map<Integer, Integer> frequencies) throws IOException {

        int character = input.read();
        int length = 1;

        while (character != -1){

            int quantity = 1;

            if (frequencies.containsKey(character)){
                quantity += frequencies.get(character);
            }

            frequencies.put(character,quantity);
            length++;

        }

        Iterator<Integer> iterator = frequencies.keys();

    }


    private class Node implements Comparable<Node>{

        private final Integer value;
        private final Integer frequency;
        private final Node left;
        private final Node right;

        public Node(Integer value, Integer frequency, Node left, Node right) {
            this.value = value;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public Node(Integer value, Integer frequency){
            this(value,frequency,null,null);
        }

        @Override
        public int compareTo(@NotNull Node o) {
            return Integer.compare(frequency,o.frequency);
        }

    }
}
