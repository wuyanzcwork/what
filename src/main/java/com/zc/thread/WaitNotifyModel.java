package com.zc.thread;

/**
 * Created by zhangchao7 on 16/10/22.
 */
public class WaitNotifyModel {

    public Object result = new Object();

    public Object model(long mills) throws InterruptedException {

        long future = System.currentTimeMillis()+mills;
        long remain = mills;

        while( result==null && remain>0 ) {
            wait(mills);
            remain = future - System.currentTimeMillis();
        }
        return result;
    }
}
