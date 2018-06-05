package com.cvmars.baseapp.api.exception;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;


    private static String message;
    private static int code;


    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
        message = detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public int  getCode() {
        return code;
    }
    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        switch (code) {
            case 432:
                message = "";
                break;
            default:
                message = "未知错误";
        }
        return message;
    }
}

