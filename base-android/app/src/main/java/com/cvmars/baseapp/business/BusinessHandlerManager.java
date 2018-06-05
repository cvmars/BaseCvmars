package com.cvmars.baseapp.business;

import com.cvmars.baseapp.business.implement.TestHandler;

import java.util.HashMap;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class BusinessHandlerManager {


    private static BusinessHandlerManager sInstance = new BusinessHandlerManager();


    private HashMap<String, IBusinessHandler> mbusList;

    private BusinessHandlerManager() {

    }

    public BusinessHandlerManager getInstance() {

        return sInstance;
    }

    //在应用进入地方进行初始化
    public void init() {

        mbusList = new HashMap<>();
        getInstance().register("TestHandler", new TestHandler());
    }


    //注册业务
    private void register(String key, IBusinessHandler handler) {
        mbusList.put(key, handler);
    }


    //操作基本业务
    protected void doHandler(BaseItemModel model) {

        for (String key : mbusList.keySet()) {
            IBusinessHandler handler = mbusList.get(key);
            if (handler.enabled(model)) {
                handler.doHandler(model);
                break;
            }
        }
    }


}
