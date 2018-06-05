package com.cvmars.baseapp.api.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zsm on 2017/6/26.
 */

public class TokenInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);


        //这里进行Token 过期处理 根据返回的code
//        if(response.code()== Consts.Token_Has_Expired){
//
//            Gson gson = new Gson();
//            try{
//                ResponseBody newResponseBodys = response.peekBody(1024 * 1024);
//                HttpResult objectResult = gson.fromJson(newResponseBodys.string(),HttpResult.class);
//                if(objectResult.getCode() == Consts.Token_Has_Expired){
//
//                    LogUtils.d("Token_Has_Expired 401");
//                    UserOp op = UserOp.getInstance();
//                    //同步请求方式，获取最新的Token
//                    String newSession = getNewToken("Bearer " + op.getUserToken());
//
//                    Request newRequest = request.newBuilder()
//                            .method(request.method(), request.body())
//                            .headers(request.headers())
//                            .header("Authorization", "Bearer " + newSession)
//                            .build();
//                    return chain.proceed(newRequest);
//                }else if(objectResult.getCode() == Consts.Token_ReLogin){
//
//                    LogUtils.d("Token_Has_Expired 403");
//                    UserOp.getInstance().onUserExit();
//                    EventBusWrap.post(new EventAppExit(true));
//                    if(MyApp.getApplication() != null){
//                        MyApp.getApplication().removeAllActivity();
//                        MyApp.getApplication().startActivity(new
//                                Intent(MyApp.getApplication(), LoginActivity.class));
//                    }
//                    return response;
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//                return response;
//            }
//        }


        return response;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getNewToken(String oldtoken) throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求

//        Call<HttpResult<UserInfoModel>> newToken = Api.getInstance().getNewToken(oldtoken);
//        retrofit2.Response<HttpResult<UserInfoModel>> execute = newToken.execute();
//        HttpResult<UserInfoModel> body = execute.body();
//        if (body.isSucc()) {
//            UserInfoModel data = body.getData();
//            if (data != null) {
//                UserOp op = UserOp.getInstance();
//                UserInfoModel userModel = op.getUserModel();
//                userModel.token = data.token;
//                LogUtils.d("Token_Has_Expired token :" + data.token);
//                op.onLogin(userModel);
//                return data.token;
//            }
//        }
        return "";
    }
}
