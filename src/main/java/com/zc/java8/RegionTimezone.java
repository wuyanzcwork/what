package com.zc.java8;


/**
 * Created by zhangchao on 2017/8/8.
 */
public class RegionTimezone {

    public static final RegionTimezone EMPTY = new RegionTimezone("","");

    private String rawOffset="";

    private String dstOffset="";

    public RegionTimezone(String rawOffset, String dstOffset) {
        this.rawOffset = rawOffset;
        this.dstOffset = dstOffset;
    }

    public RegionTimezone() {
    }

    public String getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(String rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(String dstOffset) {
        this.dstOffset = dstOffset;
    }

    public boolean isEmpty() {
        return this==EMPTY;
    }

    public static void main(String[] args) {
        RegionTimezone regionTimezone = new RegionTimezone();
        System.out.println(regionTimezone);
    }
}
