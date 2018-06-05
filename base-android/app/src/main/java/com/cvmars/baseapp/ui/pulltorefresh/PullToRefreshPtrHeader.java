package com.cvmars.baseapp.ui.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cvmars.baseapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Cvmars on 2016/5/12.
 */
public class PullToRefreshPtrHeader extends FrameLayout implements PtrUIHandler {

    private TextView text_tag;
    private TextView text_time;
    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.header_pull_refresh,this);
        ImageView anim = (ImageView) view.findViewById(R.id.heard_refresh);
        text_tag = (TextView) view.findViewById(R.id.lvHeaderTipsTv);
        text_time = (TextView) view.findViewById(R.id.lvHeaderLastUpdatedTv);
        updateDate();
    }
    public PullToRefreshPtrHeader(Context context) {
        super(context);
        init();
    }

    public PullToRefreshPtrHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PullToRefreshPtrHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        updateDate();
        text_tag.setText("下拉刷新");


    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        text_tag.setText("释放刷新");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        text_tag.setText("正在刷新");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        text_tag.setText("刷新完成");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        if(isUnderTouch&&ptrIndicator.getCurrentPercent()>1.1){
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                text_tag.setText("释放刷新");

            }
        }else if(isUnderTouch&&ptrIndicator.getCurrentPercent()<1.1){
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                text_tag.setText("下拉刷新");
            }
        }
        if(onRefresh!=null){
            if(!isUnderTouch&&ptrIndicator.getCurrentPercent()<=0.4){
                onRefresh.onRefreshComplete();
            }else{
                if(ptrIndicator.getCurrentPercent()>=0.2){
                    onRefresh.onRefresh();
                }
            }
        }
    }

    public void updateDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        String t = format.format(new Date());
        text_time.setText("最后更新:" + t);
    }

    OnPullRefreshListener onRefresh;

    public void setOnRefreshListener(OnPullRefreshListener onRefresh){

        this.onRefresh = onRefresh;

    }

    public interface OnPullRefreshListener{

        void onRefresh();

        void onRefreshComplete();
    }
}
