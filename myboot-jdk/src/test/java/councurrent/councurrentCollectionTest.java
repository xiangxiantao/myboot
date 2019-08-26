package councurrent;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/22 17:06
 * @description：并发容器
 * @modified By：
 */

public class councurrentCollectionTest {

    @Test
    public void testMap(){
        ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();
        System.out.println(1 << 30);//一个int四个字节所能表达的最大正整数
        System.out.println(1 << 31);
        System.out.println(1 << 32);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void testConcurrentLinkedQueue(){
        ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();
    }

    /**
     * 当进行写操作时，copy一个原list的副本进行写，不会阻塞读操作
     */
    @Test
    public void testCopyOnWriteArrayList(){
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
    }

    @Test
    public void testBlockingQueue(){



    }

    /**
     * blockingQueue的take()/put()方法
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue queue=new ArrayBlockingQueue(10);
        Consumer consumer = new Consumer(queue);

        ExecutorService consumerService=Executors.newFixedThreadPool(5);
        ExecutorService providerService=Executors.newFixedThreadPool(1);
        consumerService.submit(consumer);

        for (int i = 0; i < 5; i++) {
            providerService.submit(new Provider(queue,i+""));
        }


    }

}

/**
 * 一个消费者
 */
class Consumer implements Runnable{
    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String msg = queue.take();
            //Thread.sleep(3000);//模拟一个耗时的消费
            System.out.println(Thread.currentThread().getName()+"get msg:"+msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Provider implements Runnable{
    private final BlockingQueue<String> queue;

    private ReentrantLock lock=new ReentrantLock();

    private String msg;

    public Provider(BlockingQueue<String> queue,String msg) {
        this.queue = queue;
        this.msg=msg;
    }


    @Override
    public void run() {
        try {
            queue.put(msg);
            Thread.sleep(3000);//模拟一个耗时的生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
