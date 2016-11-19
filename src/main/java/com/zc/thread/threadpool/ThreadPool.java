package com.zc.thread.threadpool;

/**
 * Created by zhangchao7 on 16/10/22.
 */
public interface ThreadPool<T extends Runnable> {

    public void execute(T job);

    void shutDown();

    void addWorker(int num);

    void removeWorker(int num);

    int getJobsize();
}
