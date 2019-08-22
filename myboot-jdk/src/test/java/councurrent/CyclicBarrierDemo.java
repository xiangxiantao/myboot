package councurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/22 10:28
 * @description：
 * @modified By：
 */

public class CyclicBarrierDemo {

    public static class Soldier implements Runnable{

        private String soldier;
        private CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                dowork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void dowork() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("mission success");
        }
    }

    public static class AfterCyc implements Runnable{

        boolean flag;
        int N;

        public AfterCyc(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag){
                System.out.println(N+"个士兵集合完成");
            }else {
                System.out.println(N+"个士兵任务完成");
            }
        }
    }

    public static void main(String[] args) {
        int N=10;
        boolean flag=true;
        AfterCyc afterCyc = new AfterCyc(flag, N);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, afterCyc);
        Thread[] soldierThred=new Thread[10];
        System.out.println("集合");
        Soldier soldier = new Soldier("sodier", cyclicBarrier);
        for (int i = 0; i < 10; i++) {

            System.out.println("sodier"+i+"报道");
            soldierThred[i]=new Thread(soldier);
            soldierThred[i].start();
        }


    }
}
