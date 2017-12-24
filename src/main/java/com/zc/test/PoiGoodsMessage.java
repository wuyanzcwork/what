package com.zc.test;

import java.util.List;

/**
 * Created by zhangchao on 2017/12/20.
 */
public class PoiGoodsMessage {

    private long poiId;

    private String checkinDateStr;

    private String checkoutDateStr;

    private int numberOfAdults;

    private int numberOfChildren;

    private String childAges;

    private int roomNumber;

    private List<GoodsMessage> goods;

    public long getPoiId() {
        return poiId;
    }

    public void setPoiId(long poiId) {
        this.poiId = poiId;
    }

    public String getCheckinDateStr() {
        return checkinDateStr;
    }

    public void setCheckinDateStr(String checkinDateStr) {
        this.checkinDateStr = checkinDateStr;
    }

    public String getCheckoutDateStr() {
        return checkoutDateStr;
    }

    public void setCheckoutDateStr(String checkoutDateStr) {
        this.checkoutDateStr = checkoutDateStr;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getChildAges() {
        return childAges;
    }

    public void setChildAges(String childAges) {
        this.childAges = childAges;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<GoodsMessage> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsMessage> goods) {
        this.goods = goods;
    }
}
