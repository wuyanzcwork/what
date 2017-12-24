package com.zc.thread;

/**
 * Created by zhangchao7 on 16/10/16.
 */
public class Shutdown {

    public static void main(String[] args) {

        Thread thread1 = new Thread( new Runner(),"countThread1" );
        thread1.start();
    }
}

class Runner implements Runnable {

    private long i;
    public static volatile boolean on = false;

    public void run() {

    }

    public void cancel() {
        on = true;
    }
}