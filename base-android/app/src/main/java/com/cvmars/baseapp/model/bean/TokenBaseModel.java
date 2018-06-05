package com.cvmars.baseapp.model.bean;

/**
 * Created by hehaifeng on 2018/6/4.
 */

public class TokenBaseModel {


    public String token_name;
    /**
     * quantity : 1.94949294E8
     * percentage : 71.23
     * insert_date : 2018-6-04 15:00:00
     * addressCount : 20852.0
     */

    public String quantity;
    public String percentage;
    public String insert_date;
    public String addressCount;

    @Override
    public String toString() {
        return "TokenBaseModel{" +
                "token_name='" + token_name + '\'' +
                '}';
    }
}
