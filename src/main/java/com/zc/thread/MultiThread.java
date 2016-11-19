package com.zc.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by zhangchao7 on 16/10/10.
 */
public class MultiThread {

    public static void main(String[] args) {

        ThreadMXBean threadMxbean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadsinfo = threadMxbean.dumpAllThreads(false, false);
        for (int i = 0; i < threadsinfo.length; i++) {

            System.out.println(" threadsinfo: "+threadsinfo[i].getThreadName());
        }
    }
}
