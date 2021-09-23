package anaydis.search;

import anaydis.otherClass.QuijoteSearcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;

public class QuijoteTester {

    private int n;
    private QuijoteSearcher searcher;

    public QuijoteTester(QuijoteSearcher searcher,int n) {
        this.n = n;
        this.searcher = searcher;
        searcher.addWords(n,false);
    }

    public static void main(String[] args) {

        //QuijoteSearcher carlos = new QuijoteSearcher(new ArrayMap<String,Integer>(Comparator.naturalOrder()));
        //carlos.addWords(200000,true);

        int[] lengths = {5000, 50000, 100000, 150000, 200000};

        for (int i = 0; i < lengths.length; i++) {

            QuijoteTester tester = new QuijoteTester(new QuijoteSearcher(new RWayTrieMap<>()),lengths[i]);
            tester.messureTime();

        }

    }

    public void messureTime(){

        long time = 0;
        try {
            RandomAccessFile raf = new RandomAccessFile(new File("src/test/resources/books/quijoteReverse.txt"),"r");
            raf.seek(0);
            for (int i = 0; i < n; i++) {
                String word = raf.readUTF();
                word = checkWord(word);
                long startTime = System.currentTimeMillis();
                try {
                    searcher.getMap().containsKey(word);
                }catch (ArrayIndexOutOfBoundsException e ){}
                time+= System.currentTimeMillis()-startTime;
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(""+n+","+time);


    }

    private String checkWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i)<256) word.replace(word.charAt(i),'\0');
        }
        return word;
    }


}
