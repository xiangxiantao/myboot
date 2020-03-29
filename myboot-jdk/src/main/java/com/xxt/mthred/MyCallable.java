package com.xxt.mthred;

import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(6000);
        return "ok";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable=new MyCallable();
        FutureTask<String> futureTask=new FutureTask<>(myCallable);
        new Thread(futureTask).run();

        //这个方法将一直阻塞等待futureTask执行完成获取返回值
        String s = null;
        try {
            s = futureTask.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("等待超时");
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
