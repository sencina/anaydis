package anaydis.immutable;

import org.junit.Assert;
import org.junit.Test;

public class ListTest {

    @Test
    public void creationTest(){

        List<Integer> list = new Node<>(1,new Node<>(2, new Node<>(3, List.nil())));
        Assert.assertEquals(2, (int) list.tail().head());
        Assert.assertEquals(3,size(list));

    }

    @Test
    public void reverseTest(){

        List<Integer> list = new Node<>(1,new Node<>(2, new Node<>(3, List.nil())));
        List<Integer> reverse = list.reverse();
        Assert.assertFalse(reverse.isEmpty());
        int i = 3;
        while (!reverse.isEmpty()){
            Assert.assertEquals(i,(int)reverse.head());
            reverse = reverse.tail();
            i--;
        }
    }

    private int size(List<Integer> list) {
        return (list.isEmpty()) ? 0 : 1+size(list.tail());
    }
}
