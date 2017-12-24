package com.zc.guava;

import com.google.common.base.Objects;

/**
 * Created by zhangchao on 2017/7/1.
 */
public class ObjectsDemo {

    public static void main(String[] args) {

        System.out.println(Objects.equal(null,null));
        System.out.println(Objects.equal(null,"1"));
        System.out.println(Objects.equal("1","1"));
    }
}
