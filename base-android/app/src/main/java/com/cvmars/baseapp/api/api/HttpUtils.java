package com.cvmars.baseapp.api.api;


import com.cvmars.baseapp.api.exception.ApiException;
import com.cvmars.baseapp.api.model.HttpResult;
import com.cvmars.baseapp.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Cvmars on 2017/6/14.
 */

 public class HttpUtils {


    private HttpUtils(){}

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    /**
     * 获取单例
     */
    public static HttpUtils getInstance() {
        return HttpUtils.SingletonHolder.INSTANCE;
    }


    /**
     * 不带缓存
     * @param ob
     * @param subscriber
     * @param <T>
     */
    public <T> void toSubscribe(Observable<HttpResult<T>> ob, final SimpleSubscriber subscriber){

        ob.flatMap(new Function<HttpResult<T>, Observable<HttpResult<T>>>() {
            @Override
            public Observable<HttpResult<T>> apply(@NonNull final HttpResult<T> tHttpResult) throws Exception {
                if (tHttpResult.getCode() == 200) {
                    return createData(tHttpResult);
                }else {
                    return Observable.error(new ApiException(tHttpResult.getMsg()));
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }



    /**
     * 带缓存,
     * @param ob
     * @param subscriber
     * @param <T>
     */
    public <T> void toSubscribeCache(Observable<HttpResult<T>> ob, final SimpleSubscriber subscriber,String cacheKey ){

        Observable<T> resultObservable = ob.flatMap(new Function<HttpResult<T>, Observable<T>>() {
            @Override
            public Observable<T> apply(HttpResult<T> result) throws Exception {
                LogUtils.d(result.toString());
                if (result.getCode() == 200) {
                    return createData(result.getData());
                }else {
                    return Observable.error(new ApiException(result.getCode()));
                }
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
        RetrofitCache.load(resultObservable,cacheKey).subscribe(subscriber);
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {

        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter subscriber) throws Exception {
                try {
                    LogUtils.d("onNext"+ data);
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
