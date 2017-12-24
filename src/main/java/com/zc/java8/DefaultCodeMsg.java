package com.zc.java8;

import java.io.Serializable;

/**
 * Created by zhangchao on 2017/11/9.
 */
public class DefaultCodeMsg implements Serializable {

    public int code;

    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
