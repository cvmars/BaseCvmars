package com.cvmars.baseapp.api.api;

/**
 * Created by hehaifeng on 2018/5/17.
 */

public class Consts {

    public final static String APP_HOST = "http://39.105.9.5:8080";


//    public final static String APP_IMG_HOST = "http://qimg3.youxiake.com/";

    /**
     * 取新token
     */
    public final static String Api_RefreshToken = "auth/refreshToken";

    //token错误代码
    public static final int Token_Has_Expired = 401; //token过期
    public static final int Token_Rquire = 400;  //请求新token
    public static final int Token_ReLogin = 403;  //重新登陆

    //api错误代码
    public static final int API_ERROR_TIMEOUT = 408;  //链接超时
    public static final int API_ERROR_PARSER = 400;  //链接错误
    public static final int API_ERROR_RELOGIN = 403; //需要重新登陆
    public static final int API_ERROR_UNKNOWN = 1000; //需要重新登陆

    public static final int API_UNCONNECTION_NET = 401; //网络已经断开

    //错误信息提示
    public final static String ErrorMsg = "网络繁忙";
    public final static String ErrorTimeOutMsg = "请求超时";
    public final static String ErrorReLogin = "请重新登陆";

//    public final static String VERSION_V1 = "Accept: application/vnd.yxk.v1+json";
//    public final static String VERSION_V2 = "Accept: application/vnd.yxk.v2+json";
}
