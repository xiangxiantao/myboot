package councurrent.cas;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author ：xxt
 * @date ：Created in 2019/9/4 15:05
 * @description：无锁字段封装测试
 * @modified By：
 */

public class AtomicIntergerFiledUpdaterTest {

    @Data
    public static class Candidate{
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater=AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    public static AtomicInteger allScore =new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1000);
        final Candidate stu=new Candidate();
        Thread[] t=new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            t[i]=new Thread(()->{
                if(Math.random()>0.4){
                    scoreUpdater.incrementAndGet(stu);
                    allScore.incrementAndGet();
                }
                countDownLatch.countDown();
            });
            t[i].start();
        }
        countDownLatch.await();
        System.out.println("score="+stu.score);
        System.out.println("allScore="+allScore);


    }

}
