package com.zc.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zc.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by zhangchao on 2017/7/13.
 */
public class TestMain {


    private static List<String> spePrefixList = Lists.newArrayList("-888", "-900");

    private static Object o = new Object();

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(20);
        int[] count = {0};
        String[] writeFile = new String[10];
        String[] file = new String[10];

        for( int i=0;i<10;i++ ) {

            file[i] = "/Users/zhangchao54/Desktop/test"+(i+1)+".txt";
            writeFile[i] = "/Users/zhangchao54/Desktop/result"+(i+1)+".csv";
        }
        for (int i = 0; i < 10; i++) {

            final int temp = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile[temp]), "UTF-8"));
                        writer.write("poiId,partnerId,hotelId,logicHotelMinPrice,logicHotelMaxPrice,poiMinPrice,poiMaxPrice");
                        writer.newLine();

                        String poiId = "";
                        BufferedReader reader = new BufferedReader(new FileReader(new File(file[temp])));
                        while (StringUtils.isNotBlank((poiId = reader.readLine()))) {

                            String url = "http://10.4.104.88:8418/api/poi?time=343434&poiid=" + poiId + "&checkinDateStr=04/26/2018&checkoutDateStr=04/27/2018&numberOfAdults=2&numberOfChildren=0&childAges=&roomNumber=1";
                            System.out.println("poiId：" + poiId + "," + (count[0]++));

                            String httpResult = null;
                            try {
                                httpResult = HttpUtil.get(url);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            JSONObject result = JSONObject.parseObject(httpResult);
                            JSONObject goodsInPoi = result.getJSONObject("goodsInPoi");
                            if (goodsInPoi == null) {
                                continue;
                            }
                            JSONArray goodsList = goodsInPoi.getJSONArray("goods");

                            Map<String, List<Object>> groupResult = goodsList.stream()
                                    .collect(Collectors.groupingBy((Object oneGoods) -> {
                                        JSONObject convertOneGoods = (JSONObject) oneGoods;
                                        String accessCode = convertOneGoods.getString("accessCode");
                                        String[] accessCodeList = accessCode.split("#");

                                        String hotelId = "";

                                        if (StringUtils.isBlank(accessCode)) {
                                            System.out.println("accessCode错误！");
                                        } else if (spePrefixList.contains(accessCodeList[0])) {
                                            System.out.println("港澳台或同舟");
                                        } else if ("-901".equals(accessCodeList[0])) {
                                            hotelId = accessCodeList[2];
                                        } else {
                                            hotelId = accessCodeList[1];
                                        }

                                        return convertOneGoods.getString("partnerId") + "#" + hotelId;
                                    }));

                            List<Object> poiMin = groupResult.values().stream()
                                    .map(objList -> {
                                        return objList.stream().min((obj1, obj2) -> {
                                            JSONObject obj1JsonObject = (JSONObject) obj1;
                                            JSONObject obj2JsonObject = (JSONObject) obj2;
                                            Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                            Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                            return price1-price2;
                                        }).get();
                                    }).collect(Collectors.toList());

                            JSONObject poiMinMin = (JSONObject) poiMin.stream().min((obj1, obj2) -> {
                                JSONObject obj1JsonObject = (JSONObject) obj1;
                                JSONObject obj2JsonObject = (JSONObject) obj2;
                                Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                return price1-price2;
                            }).get();

                            JSONObject poiMinMax = (JSONObject) poiMin.stream().max((obj1, obj2) -> {
                                JSONObject obj1JsonObject = (JSONObject) obj1;
                                JSONObject obj2JsonObject = (JSONObject) obj2;
                                Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                return price1-price2;
                            }).get();


                            List<Object> poiMax = groupResult.values().stream()
                                    .map(objList -> {
                                        return objList.stream().max((obj1, obj2) -> {
                                            JSONObject obj1JsonObject = (JSONObject) obj1;
                                            JSONObject obj2JsonObject = (JSONObject) obj2;
                                            Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                            Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                            return price1-price2;
                                        }).get();
                                    }).collect(Collectors.toList());

                            JSONObject poiMaxMin = (JSONObject) poiMax.stream().min((obj1, obj2) -> {
                                JSONObject obj1JsonObject = (JSONObject) obj1;
                                JSONObject obj2JsonObject = (JSONObject) obj2;
                                Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                return price1-price2;
                            }).get();

                            JSONObject poiMaxMax = (JSONObject) poiMax.stream().max((obj1, obj2) -> {
                                JSONObject obj1JsonObject = (JSONObject) obj1;
                                JSONObject obj2JsonObject = (JSONObject) obj2;
                                Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                return price1-price2;
                            }).get();


                            groupResult.values().forEach(eachGroup -> {

                                Object eachMax = eachGroup.stream().max((obj1, obj2) -> {
                                    JSONObject obj1JsonObject = (JSONObject) obj1;
                                    JSONObject obj2JsonObject = (JSONObject) obj2;
                                    Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                    Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                    return price1-price2;
                                }).get();
                                JSONObject eachMaxObj = (JSONObject) eachMax;

                                Object eachMin = eachGroup.stream().min((obj1, obj2) -> {
                                    JSONObject obj1JsonObject = (JSONObject) obj1;
                                    JSONObject obj2JsonObject = (JSONObject) obj2;
                                    Integer price1 = (obj1JsonObject.getJSONObject("price").getInteger("roomFee"));
                                    Integer price2 = (obj2JsonObject.getJSONObject("price").getInteger("roomFee"));

                                    return price1-price2;
                                }).get();


                                JSONObject eachMinObj = (JSONObject) eachMin;

                                String hotelId = "";
                                String accessCode = eachMaxObj.getString("accessCode");
                                String[] accessCodeList = accessCode.split("#");

                                if (StringUtils.isBlank(accessCode)) {
                                    System.out.println("accessCode错误！");
                                } else if (spePrefixList.contains(accessCodeList[0])) {
                                    hotelId = "gangaotai";
                                } else if ("-901".equals(accessCodeList[0])) {
                                    hotelId = accessCodeList[2];
                                } else {
                                    hotelId = accessCodeList[1];
                                }
                                String minRoomFee = eachMinObj.getJSONObject("price").getString("roomFee");

                                String maxRoomFee = eachMaxObj.getJSONObject("price").getString("roomFee");

                                Integer minMaxPrice = poiMinMax.getJSONObject("price").getInteger("roomFee");
                                Integer minMinPrice = poiMinMin.getJSONObject("price").getInteger("roomFee");


                                Integer maxMaxPrice = poiMaxMax.getJSONObject("price").getInteger("roomFee");
                                Integer maxMinPrice = poiMaxMin.getJSONObject("price").getInteger("roomFee");

                                String str = eachMaxObj.getString("poiId") + "," + eachMaxObj.getString("partnerId") + "," + hotelId + "," + minRoomFee + "," + maxRoomFee + "," + (minMaxPrice - minMinPrice) + "," + (maxMaxPrice - maxMinPrice);

                                try {
                                    writer.write(str);

                                    writer.newLine();
                                    writer.flush();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}
