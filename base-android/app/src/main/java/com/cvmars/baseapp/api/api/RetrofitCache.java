package com.cvmars.baseapp.api.api;

import android.text.TextUtils;

import com.cvmars.baseapp.utils.LogUtils;
import com.orhanobut.hawk.Hawk;

import java.util.NoSuchElementException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class RetrofitCache {


    public static <T> Observable<T> load(Observable<T> fromNetwork,String cacheKey) {

        boolean isCacheKey = true;
        if(TextUtils.isEmpty(cacheKey)){
            isCacheKey = false;
        }
        return load(cacheKey, fromNetwork, isCacheKey, false);
    }


    /**
     * @param cacheKey     缓存的Key
     * @param fromNetwork
     * @param isSave       是否缓存
     * @param forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */
    public static <T> Observable<T> load(final String cacheKey,
                                         Observable<T> fromNetwork,
                                         boolean isSave, boolean forceRefresh) {

        Observable<T> fromCaches = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                T cache = (T) Hawk.get(cacheKey);
                LogUtils.d("cache " + cacheKey + " add");
                if (cache != null) {
                    subscriber.onNext(cache);
                } else {
                    subscriber.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        if (isSave) {
            fromNetwork = fromNetwork.map(new Function<T, T>() {
                @Override
                public T apply(@NonNull T result) throws Exception {
                    Hawk.put(cacheKey, result);
                    LogUtils.d("cache " + cacheKey + " get");
                    return result;
                }
            });
        }
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCaches, fromNetwork).switchIfEmpty(new Observable<T>() {
                @Override
                protected void subscribeActual(Observer<? super T> observer) {
                    observer.onError(new NoSuchElementException());
                }
            });
        }
    }
}
