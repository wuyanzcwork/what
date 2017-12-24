package com.zc.thread;

/**
 * Created by zhangchao7 on 16/10/10.
 */
public class ConcurrencyTest {

    private static int count = 100000;

    public static void main(String[] args) {
        concurrency();
        serial();
    }

    public static void concurrency() {

        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for ( int i=0;i<count;i++ ) {
                    a = a+5;
                }
            }
        });
        thread.start();

        int b=0;
        for( int i=0;i<count;i++ ) {
            b = b+5;
        }
        long delay = System.currentTimeMillis()-start;
        System.out.println( "concurrency delay:"+delay );
    }

    public static void serial() {
        long start = System.currentTimeMillis();

        int a = 0;
        for ( int i=0;i<count;i++ ) {
            a = a+5;
        }

        int b=0;
        for( int i=0;i<count;i++ ) {
            b = b+5;
        }
        long delay = System.currentTimeMillis()-start;
        System.out.println( "serial delay:"+delay );
    }
}
