package anaydis.compression;

import anaydis.immutable.BinaryTree;
import anaydis.search.Map;
import anaydis.search.RandomizedTreeMap;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman implements Compressor{

    Comparator<Integer> integerComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1,o2);
        }
    };


    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        final Map<Integer,Double> frequencies = new RandomizedTreeMap<>(integerComparator);
        fillFrequencies(input,frequencies);

        Queue<Node> priorityQueue = new PriorityQueue<>();
        fillPriorityQueue(priorityQueue,frequencies);

        



    }

    private void fillPriorityQueue(Queue<Node> queue, Map<Integer, Double> map) {

        List<Integer> keys = (List<Integer>) map.keys();

        for (int i = 0; i < keys.size(); i++) {

            Node aux = new Node(keys.get(i),map.get(keys.get(i)));
            queue.add(aux);

        }

    }

    private void fillFrequencies(InputStream input, Map<Integer, Double> frequencies) throws IOException {

        int length = input.available();

        for (int i = 0; i < length-1; i++) {
            int code = input.read();
            if (frequencies.containsKey(code)) continue;
            int frequency = 0;
            for (int j = i+1; j < length-1; j++) {
                int aux = input.read();
                if (code == aux) frequency++;
            }

            frequencies.put(code, (double) (frequency/length));
        }


    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

    }

    private class Node implements Comparable<Node>{

        private final Integer value;
        private final Double frequency;

        public Node(Integer value, Double frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(@NotNull Node o) {
            return Double.compare(frequency,o.frequency);
        }

    }
}
