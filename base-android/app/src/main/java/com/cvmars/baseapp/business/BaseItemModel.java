package com.cvmars.baseapp.business;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class BaseItemModel {

    public String handler, url, title;

    public BaseItemModel(String handler, String url) {
        this.handler = handler;
        this.url = url;
    }

    public BaseItemModel(String handler, String url, String title) {
        this.handler = handler;
        this.url = url;
        this.title = title;
    }
}
