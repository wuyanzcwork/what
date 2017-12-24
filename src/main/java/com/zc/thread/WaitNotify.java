package com.zc.thread;

/**
 * Created by zhangchao7 on 16/10/20.
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread wait = new Thread(new Wait(),"wait thread.");
        wait.start();
        //Thread.sleep(1000);
        Thread notify = new Thread(new Notify(),"notify thread.");
        notify.start();
    }
static class Wait implements Runnable {

    public void run() {

        synchronized (lock) {
            System.out.println(" is doing....");
            while (flag) {
                System.out.println("waiting.........");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("running...........");
        }
    }
}

static class Notify implements Runnable {

    public void run() {

        synchronized (lock) {
            System.out.println("notify............");
            lock.notifyAll();
            flag = false;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (lock) {
            System.out.println("notify again.........");
        }
    }
}

}