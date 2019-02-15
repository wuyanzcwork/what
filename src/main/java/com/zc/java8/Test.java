package com.zc.java8;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zc.model.SignalInfo;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangchao54 on 2018/9/17.
 */
public class Test {

    private SignalInfo signalInfo;

    public Test(SignalInfo signalInfo) {
        this.signalInfo = signalInfo;
    }

    private static LoadingCache<Integer, Integer> poiRsCacheForDayRoom = null;

    private static void reload(int maximumSize,long seconds) {
        poiRsCacheForDayRoom = CacheBuilder.newBuilder()
                           .maximumSize(maximumSize)
                           .expireAfterAccess(seconds, TimeUnit.SECONDS)
                           .recordStats()
                           .build(new CacheLoader<Integer, Integer>() {
                               @Override
                               public Integer load(Integer key) throws Exception {
                                   return 1;
                               }

                               @Override
                               public Map<Integer, Integer> loadAll(Iterable<? extends Integer> keys) throws Exception {

                                   Thread.sleep(100);
                                   HashMap<Integer, Integer> map = Maps.newHashMap();
                                   keys.forEach(key -> {
                                       map.put(key, 1);
                                   });
                                   return map;
                               }
                           });
    }


    public static void main(String[] args) throws ExecutionException {

        reload(6000,100);

        ExecutorService pool = Executors.newFixedThreadPool(1000);

        for( int i=0;i<100;i++ ) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reload(8000,200);
                }
            });
        }
        ExecutorService pool2 = Executors.newFixedThreadPool(1000);

        for( int i=0;i<10000;i++ ) {
            pool2.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        try {
                            Thread.sleep(100);
                            reload(100,100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(poiRsCacheForDayRoom.getAll(Lists.newArrayList(RandomUtils.nextInt(0,100))));
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public SignalInfo getSignalInfo() {
        return signalInfo;
    }

    public void setSignalInfo(SignalInfo signalInfo) {
        this.signalInfo = signalInfo;
    }
}
