package com.cvmars.baseapp.api.api;

import com.cvmars.baseapp.api.interceptor.HeaderInterceptor;
import com.cvmars.baseapp.api.interceptor.LogInterceptor;
import com.cvmars.baseapp.api.interceptor.TokenInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/13 0013.
 * http://www.cnblogs.com/liushilin/p/6164901.html
 */

public class Api {

    private static ApiService SERVICE;
    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 60;

    private static final String versionCodeV1 = "v1";

    private static final String TAG = "youxiake_guide";

    /**
     * v1请求
     *
     * @return
     */
    public static ApiService getInstance() {
        return getDefault();
    }


    public static ApiService getDefault() {
        if (SERVICE == null) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            /**
             *  拦截器
             */
            httpClientBuilder.addInterceptor(new HeaderInterceptor());
            httpClientBuilder.addInterceptor(new TokenInterceptor());
            httpClientBuilder.addInterceptor(new LogInterceptor());
            // HttpLoggingInterceptor httpLoggingInterceptor
//            }

            SERVICE = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Consts.APP_HOST)
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }

}
