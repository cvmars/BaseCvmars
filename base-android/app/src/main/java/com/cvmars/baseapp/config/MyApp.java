package com.cvmars.baseapp.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class MyApp extends Application {

    static MyApp instance;

    public static Context getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
