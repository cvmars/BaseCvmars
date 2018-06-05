package com.cvmars.baseapp.api.interceptor;

import android.text.TextUtils;


import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zsm on 2017/6/26
 * <p>
 * 拦截header中的数据
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Headers.Builder headers = request.headers().newBuilder();
        headers.add("os", "android");
//        headers.add("version", DeviceUtils.installVersion());
//        headers.add("User-Agent", "android/" + DeviceUtils.installVersion() + "/" + DeviceUtils.deviceName());
//        headers.add("deviceid", DeviceUtils.SerialNumber());

        String userToken = "";
//        = UserOp.getInstance().getUserToken();
        if (!TextUtils.isEmpty(userToken)) {
            headers.add("Authorization", "Bearer " + userToken);
        }
        Request rqNew = request.newBuilder().headers(headers.build())
                .build();

        Response response = chain.proceed(rqNew);


        return response;
    }
}
