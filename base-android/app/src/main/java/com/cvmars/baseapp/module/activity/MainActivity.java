package com.cvmars.baseapp.module.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cvmars.baseapp.R;
import com.cvmars.baseapp.api.api.Api;
import com.cvmars.baseapp.api.api.ApiService;
import com.cvmars.baseapp.api.api.HttpUtils;
import com.cvmars.baseapp.api.api.SimpleSubscriber;
import com.cvmars.baseapp.api.model.HttpResult;
import com.cvmars.baseapp.model.bean.TokenListModel;
import com.cvmars.baseapp.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpUtils.getInstance().toSubscribe(Api.getInstance().getHomeInfo(),
                new SimpleSubscriber<HttpResult<TokenListModel>>() {
                    @Override
                    protected void _onNext(HttpResult<TokenListModel> o) {

                        if(o.isSucc()){

                            TokenListModel data = o.getData();

                            ToastUtils.show(data.toString());

                        }
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }
}
