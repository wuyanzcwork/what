package com.zc.test;

import com.google.common.collect.Lists;
import com.zc.util.GsonUtil;

/**
 * Created by zhangchao on 2017/7/13.
 */
public class TestMain {

    public static void main(String[] args) {

        GoodsMessage goodsMessage = new GoodsMessage();
        goodsMessage.setGoodsId("123");
        goodsMessage.setRoomFee(1000);
        goodsMessage.setSurchargeFee(2000);
        goodsMessage.setPartnerId(99999);
        PoiGoodsMessage poiGoodsMessage = new PoiGoodsMessage();
        poiGoodsMessage.setCheckoutDateStr("20170101");
        poiGoodsMessage.setPoiId(12345);
        poiGoodsMessage.setCheckinDateStr("20170101");
        poiGoodsMessage.setChildAges("0");
        poiGoodsMessage.setNumberOfAdults(2);
        poiGoodsMessage.setNumberOfChildren(0);
        poiGoodsMessage.setRoomNumber(1);
        poiGoodsMessage.setGoods(Lists.newArrayList(goodsMessage));
        System.out.println(GsonUtil.toJson(poiGoodsMessage));

        System.out.println(Lists.newArrayList().subList(0,100));
    }
}
