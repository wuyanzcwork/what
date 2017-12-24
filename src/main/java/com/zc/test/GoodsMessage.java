package com.zc.test;

/**
 * Created by zhangchao on 2017/12/20.
 */
public class GoodsMessage {

    private long partnerId;

    private String goodsId;

    private int surchargeFee;

    private int roomFee;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getSurchargeFee() {
        return surchargeFee;
    }

    public void setSurchargeFee(int surchargeFee) {
        this.surchargeFee = surchargeFee;
    }

    public int getRoomFee() {
        return roomFee;
    }

    public void setRoomFee(int roomFee) {
        this.roomFee = roomFee;
    }

    public long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(long partnerId) {
        this.partnerId = partnerId;
    }
}
