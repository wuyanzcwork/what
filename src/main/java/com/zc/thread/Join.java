package com.zc.thread;

/**
 * Created by zhangchao7 on 16/10/21.
 */
public class Join {

    public static void main(String[] args) {

        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10 ; i++) {
            Thread currentThread = new Thread(new Jointhread(previous),i+"");
            currentThread.start();
            previous = currentThread;
        }
    }
}

class Jointhread implements Runnable {

    private Thread previousThread = null;

    public Jointhread(Thread previousThread) {
        this.previousThread = previousThread;
    }

    public void run() {
        try {
            previousThread.join();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
