package anaydis.otherClass;

import anaydis.search.Map;

import java.io.*;

public class QuijoteSearcher {

    private Map<String,Integer> map;

    public QuijoteSearcher(Map<String, Integer> map) {
        this.map = map;
    }

    public void addWords(int n,boolean condition) {

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/books/quijote.txt"));
            int wordCount = 0;
            while (wordCount <= n) {
                String[] aux = bufferedReader.readLine().split(" ");
                if (aux.length>0) {
                    for (int i = 0; i < aux.length; i++) {
                        if (wordCount > n) break;
                        else {
                            if (condition){
                                writeResverse(aux[i]);
                            }
                            else {
                                int value = 1;
                                if (map.containsKey(aux[i])) value += map.get(aux[i]);
                                map.put(aux[i], value);
                            }
                            wordCount++;
                        }
                    }
                }
            }
            bufferedReader.close();

        } catch (IOException e){
            e.printStackTrace();

        }
    }

    private void writeResverse(String word) {
        String reversedWord = word;
        StringBuilder builder = new StringBuilder(reversedWord);
        reversedWord = builder.reverse().toString();

        try {
            RandomAccessFile raf = new RandomAccessFile(new File("src/test/resources/books/quijoteReverse.txt"),"rw");
            raf.seek(raf.length());
            raf.writeUTF(reversedWord);
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Map<String, Integer> getMap() {
        return map;
    }


}
