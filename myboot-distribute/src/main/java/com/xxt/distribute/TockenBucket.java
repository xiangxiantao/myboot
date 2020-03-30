package com.xxt.distribute;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 令牌桶实验
 */
public class TockenBucket {

    public static void main(String[] args) {
        final RateLimiter rateLimiter = RateLimiter.create(100.0);//每秒放出两个令牌 QPS=2.0
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            //一直阻塞直到获取到令牌
            rateLimiter.acquire();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "任务正在执行");
                }
            });
        }
        executorService.shutdown();
    }

}
