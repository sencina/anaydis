package anaydis.immutable;

import anaydis.search.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class BinaryTreeTest {

    private final Comparator<Integer> comparator = Integer::compare;

    @Test
    public void sizeTest(){

        Map<Integer,String> tree = new BinaryTree<>(comparator);
        Assert.assertEquals(0,tree.size());

        Map<Integer,String> tree2 = new BinaryTree<>(comparator,new Node<>(1,"ola",new Node<>(2,"ola"), new Node<>(3,"ola")),3);
        Assert.assertEquals(3,tree2.size());

        Map<Integer,String> tree3 = tree2.put(4,"ola");
        Assert.assertEquals(4,tree3.size());
    }

    @Test
    public void putTest(){

        Map<Integer,String> tree = new BinaryTree<>(comparator);
        Assert.assertEquals(0,tree.size());

        tree = tree.put(1,"ola");
        Assert.assertTrue(tree.containsKey(1));
        Assert.assertFalse(tree.isEmpty());
        Assert.assertEquals(1,tree.size());

        tree = tree.put(1,"ola2");
        Assert.assertTrue(tree.containsKey(1));
        Assert.assertFalse(tree.isEmpty());
        Assert.assertEquals(1,tree.size());

    }
}
