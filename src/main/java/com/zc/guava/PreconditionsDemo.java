package com.zc.guava;

import com.google.common.base.Preconditions;

/**
 * Created by zhangchao on 2017/7/1.
 */
public class PreconditionsDemo {

    public static void main(String[] args) {

        boolean trueValue = true;
        Preconditions.checkArgument(trueValue);

        try {
            String nullValue = null;
            Preconditions.checkNotNull(nullValue,"nullValue is null");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
