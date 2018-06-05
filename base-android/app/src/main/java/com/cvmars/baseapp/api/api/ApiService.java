package com.cvmars.baseapp.api.api;


import com.cvmars.baseapp.api.model.HttpResult;
import com.cvmars.baseapp.model.bean.TokenListModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by hehaifeng on 2018/5/17.
 */

public interface ApiService {

    /**
     * 首页信息
     */
    @GET("names")
    Observable<HttpResult<TokenListModel>> getHomeInfo();


    /**
     * 登录接口
     */
//    @Headers(Consts.VERSION_V1)
//    @FormUrlEncoded
//    @POST("auth/login")
//    Observable<HttpResult<UserInfoModel>> getUseLogin(@Field("loginname") String username, @Field("password") String password);


}
