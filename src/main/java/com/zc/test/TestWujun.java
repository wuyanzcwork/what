package com.zc.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zc.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by zhangchao on 2017/7/13.
 */
public class TestWujun {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/zhangchao54/Downloads/未入住订单统计（筛选后）.csv")));

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/zhangchao54/Downloads/未入住订单分析后.csv")));

        String line = "";

        List<Result> list = Lists.newArrayList();

        int count = 0;
        while( StringUtils.isNotBlank(line=reader.readLine()) ) {
            count++;

            if( count==1 ) {
                System.out.println(line);
                continue;
            }

            String[] lineArr = line.split(",");

            String poiId = lineArr[4];
            String realRoomId = lineArr[12];
            String amount = lineArr[18];

            Result r = new Result();
            r.setPoiId(poiId);
            r.setRealRoomId(realRoomId);
            r.setAmount(amount);

            list.add(r);
        }

        Map<String, List<Result>> groupResult = list.stream().collect(Collectors.groupingBy(r -> r.getRealRoomId()));

        List<Result> realResultList = Lists.newArrayList();
        groupResult.forEach((key,value)->{

            System.out.println(value);
            Result realResult = new Result();

            Result minResult = value.stream().min((r1, r2) -> {
                try {
                    return Double.valueOf(r1.getAmount()).compareTo(Double.valueOf(r2.getAmount()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return 0;
            }).get();

            Result maxResult = value.stream().max((r1, r2) -> {
                try {
                    return Double.valueOf(r1.getAmount()).compareTo(Double.valueOf(r2.getAmount()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return 0;
            }).get();

            String poiId = minResult.getPoiId();
            BigDecimal ratio = BigDecimal.valueOf(Double.valueOf(minResult.getAmount())).divide(BigDecimal.valueOf(Double.valueOf(maxResult.getAmount())),2, RoundingMode.CEILING);

            realResult.setPoiId(poiId);
            realResult.setRealRoomId(minResult.getRealRoomId());
            realResult.setMinAmount(minResult.getAmount());
            realResult.setMaxAmount(maxResult.getAmount());
            realResult.setRatio(ratio);
            realResultList.add(realResult);
        });

        realResultList.forEach(r->{

            String writeStr = r.getPoiId()+","+r.getMinAmount()+","+r.getMaxAmount()+","+r.getRealRoomId()+","+r.getRatio();
            try {
                writer.write(writeStr);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
