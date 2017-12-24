package com.zc.thread.dbpool;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangchao7 on 16/10/22.
 */
public class ConnectionpoolTest {

    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end = null;
    static ConnectionPool pool = new ConnectionPool(10);

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 10;
        int count = 100;
        AtomicInteger get = new AtomicInteger();
        AtomicInteger notGet = new AtomicInteger();
        end = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionpoolRunner(count,get,notGet),"connectionpoolRunner."+i+1);
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println(" total invoke:" + (threadCount * count));
        System.out.println(" got connection:"+get);
        System.out.println(" not got connection:"+notGet);
    }

    static class ConnectionpoolRunner implements Runnable {

        int count;
        AtomicInteger get;
        AtomicInteger notGet;

        public ConnectionpoolRunner( int count,AtomicInteger get,AtomicInteger notGet ) {
            this.count = count;
            this.get = get;
            this.notGet = notGet;
        }

        public void run() {
            try {
                System.out.println( " await...thread:"+Thread.currentThread().getName() );
                start.await();
                System.out.println( " thread running:"+Thread.currentThread().getName() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count>0) {
                try {
                    Connection conn = pool.fetchConnection(1000);
                    if( conn!=null ) {
                        try {
                            conn.createStatement();
                            conn.commit();
                        } finally {
                            pool.releaseConnection(conn);
                            get.incrementAndGet();
                        }
                    } else {
                        notGet.incrementAndGet();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
