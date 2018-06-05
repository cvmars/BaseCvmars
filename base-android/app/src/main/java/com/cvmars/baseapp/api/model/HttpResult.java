package com.cvmars.baseapp.api.model;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class HttpResult<T> {

    private T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T subjects) {
        this.data = subjects;
    }

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


    public boolean isSucc() {
        return this.code == 200;
    }
}
