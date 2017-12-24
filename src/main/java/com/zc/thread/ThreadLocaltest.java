package com.zc.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangchao7 on 16/10/21.
 */
public class ThreadLocaltest {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>() {
      protected  Long intValue() {
          return System.currentTimeMillis();
      }
    };

    public static final void begin() {
        threadLocal.set(System.currentTimeMillis());
    }

    public static final Long end() {
        return System.currentTimeMillis()-threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {

        begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("costs=="+end());
    }
}
