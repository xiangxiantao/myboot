package councurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/22 14:24
 * @description：
 * @modified By：
 */

public class ThreadPollTest {

    public static class  SimpleTask implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"execute");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DivTask implements Runnable{

        private int a,b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            System.out.println(a/b);
        }
    }

    //自定义一个线程池
/*    public static void main(String[] args) {
        //ExecutorService service= Executors.newFixedThreadPool(5);
        //ExecutorService service=Executors.newCachedThreadPool();
        //ExecutorService service=Executors.newSingleThreadExecutor();
        ExecutorService service=new ThreadPoolExecutor(5
                , 10
                , 0L
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<Runnable>(1000)
                //, Executors.defaultThreadFactory()
                , new ThreadFactory() {
            //自定义线程创建方式
            @Override
            public Thread newThread(Runnable r) {
                Thread t=new Thread(r);
                t.setDaemon(true);//设置为守护线程
                System.out.println("create thread"+t.getName());
                return t;
            }
        }
                  //自定义拒绝策略
                , new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + "线程池已满，不做处理");
            }
        }){

            //线程池的前置处理
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
            }

            //线程池的后置处理
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }

        };

        SimpleTask simpleTask=new SimpleTask();
        for (int i = 0; i < 1000; i++) {
            service.submit(simpleTask);
        }
        service.shutdown();
    }*/

    /**
     * 获取CPU核心数量
     */
    @Test
    public void testGetCpu(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /**
     * 获取线程池中运行线程的异常堆栈
     */
    @Test
    public void testGetException(){
        ExecutorService service=Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(new DivTask(1000,i));
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService service=Executors.newFixedThreadPool(10);
        ExecutorService service=new TraceThreadPollExecutor(5,5,0,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 5; i++) {
            //service.submit(new DivTask(1000,i));
            //Future<?> future = service.submit(new DivTask(1000, i));
            //future.get();
            service.execute(new DivTask(1000,i));
        }
    }

}
