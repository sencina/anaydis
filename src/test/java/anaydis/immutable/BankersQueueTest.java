package anaydis.immutable;

import org.junit.Assert;
import org.junit.Test;

public class BankersQueueTest {

    @Test
    public void dequeueTest(){

        Queue<Integer> queue = new BankersQueue(new Node(1,new Node(2,new Node(3,List.nil()))), List.nil());
        Queue.Result<Integer> result;
        result = queue.dequeue();
        Assert.assertEquals(3,(int) result.value);
        result = result.queue.dequeue();
        Assert.assertEquals(2,(int) result.value);
        result = result.queue.dequeue();
        Assert.assertEquals(1,(int) result.value);

    }

    @Test
    public void isEmptyTest(){

        Queue<Integer> queue = new BankersQueue(new Node(1,new Node(2,new Node(3,List.nil()))), List.nil());
        Queue<Integer> queue2 = new BankersQueue(List.nil(), List.nil());

        Assert.assertFalse(queue.isEmpty());
        Assert.assertTrue(queue2.isEmpty());
    }

    @Test
    public void queueTest(){

        Queue<Integer> queue = new BankersQueue(List.nil(), List.nil());
        Assert.assertTrue(queue.isEmpty());
        Queue<Integer> queue2 = queue.enqueue(1);
        Assert.assertFalse(queue2.isEmpty());

    }
}
