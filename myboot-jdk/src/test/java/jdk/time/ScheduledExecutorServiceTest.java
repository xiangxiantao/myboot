package jdk.time;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> schedule = executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println(schedule.isDone());
    }
}
