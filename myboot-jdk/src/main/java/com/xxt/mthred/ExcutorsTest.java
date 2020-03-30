package com.xxt.mthred;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class ExcutorsTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Executors
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //执行完一个之后执行下一个
        //CompletableFuture.runAsync(new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("job1 work");
        //    }
        //}, executorService).thenRun(new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("job2 work");
        //    }
        //});

        //将第一个任务的执行结果传递到下一个任务
        //CompletableFuture<String> allComplate = CompletableFuture.supplyAsync(new Supplier<String>() {
        //    @Override
        //    public String get() {
        //        return "job1 res";
        //    }
        //}, executorService).thenApplyAsync((String str) -> {
        //    return str + " job2 res";
        //});
        //String s = allComplate.get();
        //System.out.println(s);


        //将两个任务的结果组合起来，通过函数进行计算并返回最终最终结果
        CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 99999;
            }
        }, executorService);

        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 99999;
            }
        }, executorService);

        CompletableFuture<Integer> objectCompletableFuture = integerCompletableFuture1.thenCombine(integerCompletableFuture2, (i1, i2) -> {
            return i1 + i2;
        });

        Integer integer = objectCompletableFuture.join();
        System.out.println(integer);


        executorService.shutdown();
    }
}
