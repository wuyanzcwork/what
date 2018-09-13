package com.zc.test;

import java.math.BigDecimal;

/**
 *
 * @author zhangchao54
 * @date 2018/4/24
 */
public class Result {

    private String poiId;

    private String realRoomId;

    private String amount;

    private String minAmount;

    private String maxAmount;

    private BigDecimal ratio;

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getRealRoomId() {
        return realRoomId;
    }

    public void setRealRoomId(String realRoomId) {
        this.realRoomId = realRoomId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "poiId='" + poiId + '\'' +
                ", realRoomId='" + realRoomId + '\'' +
                ", amount='" + amount + '\'' +
                ", minAmount='" + minAmount + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                ", ratio=" + ratio +
                '}';
    }
}
