package com.zc.thread.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangchao7 on 16/10/22.
 */
public class DefaultThreadpool<T extends Runnable> implements ThreadPool<T> {

    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private final LinkedList<T> jobs = new LinkedList<T>();

    private AtomicInteger threadNum = new AtomicInteger();

    private static final int DEFAULT_WORKER_NUMS = 10;

    private static final int MAX_WORKER_NUMS = 100;

    private static final int MIN_WORKER_NUMS = 1;

    private int workernum = DEFAULT_WORKER_NUMS;

    public DefaultThreadpool() {
        initWorkers(DEFAULT_WORKER_NUMS);
    }

    public DefaultThreadpool(int nums) {

        nums = nums>MAX_WORKER_NUMS?MAX_WORKER_NUMS:(nums<MIN_WORKER_NUMS?MIN_WORKER_NUMS:nums);
        initWorkers(nums);
    }

    public void execute(T job) {
        if( job!=null ) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    public void shutDown() {
        for (int i = 0; i < workers.size(); i++) {
            workers.get(i).shutdown();
        }
    }

    public void addWorker(int num) {

        synchronized (jobs) {
            if( num+this.workernum>MAX_WORKER_NUMS ) {
                num = MAX_WORKER_NUMS-workers.size();
            }
            initWorkers(num);
            this.workernum += num;
        }
    }

    public void removeWorker(int num) {
        synchronized (jobs) {
            if( num>this.workernum ) {
                throw new IllegalArgumentException("beyond worknum.");
            }
            int count = 0;
            while( count<num ) {
                Worker worker = workers.get(count);
                if( workers.remove(worker) ) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workernum -= count;
        }
    }

    public int getJobsize() {
        return jobs.size();
    }

    private void initWorkers(int workerNums) {

        for (int i = 0; i < workerNums ; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"worker."+i+1);
            threadNum.incrementAndGet();
            thread.start();
        }
    }

    class Worker implements Runnable {

        private volatile boolean isRunning = true;

        public void run() {

            while ( isRunning ) {
                T job = null;
                synchronized (jobs) {
                    if( jobs.isEmpty() ) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if( job!=null ) {
                    job.run();
                }
            }
        }

        public void shutdown() {
            isRunning=false;
        }
    }
}