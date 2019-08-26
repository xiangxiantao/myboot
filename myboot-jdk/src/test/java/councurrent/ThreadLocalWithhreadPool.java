package councurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/26 17:39
 * @description： 在线程池中使用threadLocal的问题
 * ThreadLocal 称为线程本地存储，一般作为静态域使用，它为每一个使用它的线程提供一个其值（value）的副本。
 * 通常对数据库连接（Connection）和事务（Transaction）使用线程本地存储。
 * <p>
 * 可以简单地将 ThreadLocal<T> 理解成一个容器，它将 value 对象存储在 Map<Thread, T> 域中，即使用当前线程为 key 的一个 Map，
 * ThreadLocal 的 get() 方法从 Map 里取与当前线程相关联的 value 对象。ThreadLocal 的真正实现并不是这样的，但是可以简单地这样理解。
 * <p>
 * 线程池中的线程在任务执行完成后会被复用，所以在线程执行完成时，要对 ThreadLocal 进行清理（清除掉与本线程相关联的 value 对象）。
 * 不然，被复用的线程去执行新的任务时会使用被上一个线程操作过的 value 对象，从而产生不符合预期的结果。
 * @modified By：
 */

public class ThreadLocalWithhreadPool {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static int getValue() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void increment() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            service.execute(()->{
                System.out.println(Thread.currentThread().getName()+"--"+getValue());
                increment();
                System.out.println(Thread.currentThread().getName()+"--"+getValue());
                //remove();
            });
        }
    }
}
