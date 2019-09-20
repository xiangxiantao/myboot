package councurrent;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ：xxt
 * @date ：Created in 2019/9/4 16:46
 * @description：
 * @modified By：
 */

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch=new CountDownLatch(5);
        List<Future<String>> futures=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyTask myTask=new MyTask();
            myTask.setMsg("msg"+i);
            myTask.setCountDownLatchl(countDownLatch);
            //FutureTask<String> futureTask= new FutureTask<String>(myTask);
            Future<String> submit = service.submit(myTask);
            futures.add(submit);
        }
        //countDownLatch.await();
        System.out.println("主线程获取全部submit");
        for (Future<String> submit:futures){

            System.out.println("get msg:"+submit.get());
        }
        service.shutdown();
    }

}

@Data
class MyTask implements Callable<String>{

    private String msg;
    private CountDownLatch countDownLatchl;

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName()+":mission complet,msg:"+msg);
        //countDownLatchl.countDown();
        return msg;
    }
}
