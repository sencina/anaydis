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
                            String toAdd = aux[i];
                            String cleanToAdd =  toAdd.replaceAll("[^A-Za-z0-9 ]", "");
                            if (condition){
                                writeResverse(cleanToAdd);
                            }
                            else {
                                int value = 1;
                                if (map.containsKey(cleanToAdd)){
                                    try {
                                        value += map.get(cleanToAdd);
                                    }catch (NullPointerException e){}
                                }
                                map.put(checkWord(cleanToAdd), value);
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
        reversedWord = checkWord(builder.reverse().toString());

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

    private String checkWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i)<256) word.replace(word.charAt(i),'\0');
        }
        return word;
    }


}
