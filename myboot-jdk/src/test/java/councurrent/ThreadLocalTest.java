package councurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/26 15:30
 * @description：
 * @modified By：
 */

public class ThreadLocalTest {

    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ReentrantLock lock=new ReentrantLock();
    static ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<>();
    private static CountDownLatch countDownLatch=new CountDownLatch(1000);


    public static class ParseDate implements Runnable{
        int i=0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            //lock.lock();
            try {
                if (threadLocal.get()==null){
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date date=threadLocal.get().parse("2019-08-26 15:48:"+i%60);

                //Date date=sdf.parse("2019-08-26 15:40:"+i%60);
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                threadLocal.remove();//在使用线程池时使用threadLocal可能同一个Thread会
                countDownLatch.countDown();
                //lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        ExecutorService service= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            service.execute(new ParseDate(i));
        }
        countDownLatch.await();
        System.out.println("cust time="+(System.currentTimeMillis()-begin));
    }

}
