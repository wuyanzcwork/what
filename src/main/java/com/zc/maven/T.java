package com.zc.maven;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zhangchao7 on 16/11/5.
 */
public class T {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        Future<Integer> futureNew = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            System.out.println(future.get());
            System.out.println(futureNew.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
