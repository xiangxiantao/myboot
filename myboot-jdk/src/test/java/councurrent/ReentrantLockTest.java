package councurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/21 15:28
 * @description： 重入锁测试
 * @modified By：
 */
public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    /**
     * 测试重入锁
     *
     * @throws InterruptedException
     */
    @Test
    public void testCount() throws InterruptedException {
        Task task = new Task();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println(Task.i);
    }

    /**
     * 测试重入锁响应中断终结死锁
     *
     * @throws InterruptedException
     */
    @Test
    public void testInterrupt() throws InterruptedException {
        Thread t1 = new Thread(new DeadLockTask(1));
        Thread t2 = new Thread(new DeadLockTask(2));

        t1.start();
        t2.start();

        Thread.sleep(5000);
        t2.interrupt();
        t2.join();
        t1.join();


    }

    /**
     * 测试重入锁添加超时时间
     */
    @Test
    public void testTimeLock() throws InterruptedException {
        Thread t1 = new Thread(new TimeLockTask());
        Thread t2 = new Thread(new TimeLockTask());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * 测试Condition
     * @throws InterruptedException
     */
    @Test
    public void testWaitNotify() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println(System.currentTimeMillis() + "t1 start");
            try {
                System.out.println(System.currentTimeMillis() + "t1 wait for lock");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }

            System.out.println(System.currentTimeMillis() + "t1 end");

        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println(System.currentTimeMillis() + "t2 start and notify");
            //t2发出notify通知之后，需要等t2完全执行完，或者调用wait()之后，才会退出独占锁，
            // 所以当t1收到notify()的通知之后，还不能直接往下执行，需要等t2交出独占锁之后才能继续执行
            condition.signalAll();
            System.out.println(System.currentTimeMillis() + "t2 end");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }

        });

        t1.start();
        t2.start();

        //Thread.sleep(5000);
        t1.join();
        t2.join();
    }


    /**
     * 测试Semaphore
     * Junit测试线程池时，线程池中任务不执行
     */
    @Test
    public void testSemaphore(){
    }


    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(20);
        final SemaphoreTask task=new SemaphoreTask();
        for (int i = 0; i < 20; i++) {
            service.submit(task);
        }

        service.shutdown();
    }

}

class Task implements Runnable {

    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            reentrantLock.lock();
            try {
                i++;
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}

class DeadLockTask implements Runnable {

    private static ReentrantLock reentrantLock1 = new ReentrantLock();
    private static ReentrantLock reentrantLock2 = new ReentrantLock();

    private int id;

    public DeadLockTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            if (id == 1) {
                System.out.println(Thread.currentThread().getName() + "等待获取锁1");
                reentrantLock1.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "成功获取锁1，等待获取锁2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                reentrantLock2.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "成功获取锁2");
            }
            if (id == 2) {
                System.out.println(Thread.currentThread().getName() + "等待获取锁2");
                reentrantLock2.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "成功获取锁2，等待获取锁1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                reentrantLock1.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "成功获取锁1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock1.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + "释放锁1");
                reentrantLock1.unlock();
            }
            if (reentrantLock2.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + "释放锁2");
                reentrantLock2.unlock();
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "退出");
        }
    }
}

class TimeLockTask implements Runnable {
    private static ReentrantLock timeLock = new ReentrantLock();


    @Override
    public void run() {
        try {
            if (timeLock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "获取锁成功");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + "获取锁超时！");
            }
        } catch (InterruptedException e) {
        } finally {
            if (timeLock.isHeldByCurrentThread()) {
                timeLock.unlock();
            }
        }
    }
}

class SemaphoreTask implements Runnable{

    final Semaphore semaphore=new Semaphore(5);//同时允许5个线程进入的锁

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(System.currentTimeMillis()+Thread.currentThread().getName()+"执行一个耗时5秒的任务");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

    }
}

class ReadWriteLockTask{

    private static Lock lock=new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private static Lock readLock=reentrantReadWriteLock.readLock();
    private static Lock writeLock=reentrantReadWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);//模拟一个耗时的读操作
            return value;
        }finally {
            lock.unlock();
        }
    }
}