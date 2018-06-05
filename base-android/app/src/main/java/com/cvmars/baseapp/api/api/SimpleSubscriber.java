package com.cvmars.baseapp.api.api;

import android.support.annotation.NonNull;

import com.cvmars.baseapp.utils.LogUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Cvmars on 2017/6/14.
 */

public abstract class SimpleSubscriber<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        _onNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            String message = exception.response().message();
            int code = exception.response().code();
            LogUtils.d("HttpException code :" + code + "  message:" + message);
        } else if (e instanceof ConnectException) {
            String message = "网络已经断开";
            _onError(message);
            return;
        }
        LogUtils.e("error :" + e.getMessage());
        _onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
