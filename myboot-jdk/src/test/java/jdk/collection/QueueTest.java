package jdk.collection;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTest {

    @Test
    public void testPriorityQueue(){
        PriorityQueue<String> queue=new PriorityQueue();
        queue.offer("ele1");//offer：向队尾插入一个元素
        queue.offer("ele2");
        queue.offer("ele3");
        queue.offer("ele4");
        queue.offer("ele5");
        queue.offer("ele6");

        System.out.println(queue);
        System.out.println(queue.poll());//poll：从队列的头部取出元素并删除

    }

    /**
     * 将deque当stack使用
     */
    @Test
    public void testDequeueAsStack(){
        Deque<String> stack=new ArrayDeque<>();
        stack.push("ele1");//push：压入栈
        stack.push("ele2");
        stack.push("ele3");
        stack.push("ele4");

        System.out.println(stack.peek());//peek:访问栈首元素，并不出栈

        System.out.println(stack.pop());//出栈
    }

    /**
     * 将deque当队列使用
     */
    @Test
    public void testDequeueAsQueue(){
        Deque<String> queue=new ArrayDeque<>();
        queue.offer("ele1");//offer：加入队尾
        queue.offer("ele2");
        queue.offer("ele3");
        queue.offer("ele4");

        System.out.println(queue.peek());//peek:访问队列首部元素，并不出队列

        System.out.println(queue.poll());//移出队列
    }

}
