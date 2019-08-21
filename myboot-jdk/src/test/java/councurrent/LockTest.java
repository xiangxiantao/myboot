package councurrent;

import org.junit.Test;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/21 11:13
 * @description：
 * @modified By：
 */

public class LockTest {

    final static Object lock=new Object();


    /**
     * 测试中断的使用
     * @throws InterruptedException
     */
    @Test
    public void testInterrupt() throws InterruptedException {
        Thread t1=new Thread(()->{
           while (true){
               if (Thread.currentThread().isInterrupted()){
                   System.out.println("线程被中断");
                   break;
               }
               Thread.yield();
           }
        });

        t1.start();
        Thread.sleep(5000);
        t1.interrupt();


    }

    /**
     * 测试在sleep方法中出发中断的正确方法
     */
    @Test
    public void testInterrupt2(){
        Thread thread=new Thread(()->{
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("线程被中断");
                    break;
                }
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    System.out.println("interrupt when sleep");
                    //睡眠中的中断被异常捕获，不会向外抛出，所以在捕获异常后重新设置中断位
                    //如果不抛出，则线程不会输出“线程被中断”
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 等待/通知 模型
     * 运行结果：
     * 1566370656043t1 start
     * 1566370656043t1 wait for lock
     * 1566370656043t2 notify
     * 1566370656043t2 end
     * 1566370658046t1 end
     * @throws InterruptedException
     */
    @Test
    public void testWaitNotify() throws InterruptedException {
        Thread t1=new Thread(()->{
            synchronized (lock){
                System.out.println(System.currentTimeMillis()+"t1 start");

                try {
                    System.out.println(System.currentTimeMillis()+"t1 wait for lock");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis()+"t1 end");
            }
        });

        Thread t2=new Thread(()->{
            synchronized (lock){
                System.out.println(System.currentTimeMillis()+"t2 notify");
                //t2发出notify通知之后，需要等t2完全执行完，或者调用wait()之后，才会退出独占锁，
                // 所以当t1收到notify()的通知之后，还不能直接往下执行，需要等t2交出独占锁之后才能继续执行
                lock.notify();
                System.out.println(System.currentTimeMillis()+"t2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        //Thread.sleep(5000);
        t1.join();
        t2.join();
    }


}
