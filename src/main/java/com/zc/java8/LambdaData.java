package com.zc.java8;

/**
 * Created by zhangchao on 2017/8/20.
 */
public class LambdaData {

    private String s1;

    private String s2;

    public LambdaData() {
    }

    public LambdaData(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "LambdaData{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }
}
