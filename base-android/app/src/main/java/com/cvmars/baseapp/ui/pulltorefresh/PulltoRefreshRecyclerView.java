package com.cvmars.baseapp.ui.pulltorefresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.cvmars.baseapp.R;
import com.cvmars.baseapp.utils.DensityUtils;
import com.cvmars.baseapp.utils.LogUtils;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by Cvmars for LiTing  on 2017/6/14
 */

public class PulltoRefreshRecyclerView extends FrameLayout {

    private  PtrPullRefreshLayout ptrlayout;
    private RecyclerView recyclerView;
    private RecyPtrHandler ptrHandler;
    private LinearLayoutManager layoutManager;
    private BaseQuickAdapter baseQuickAdapter;
    public int mCurPager =1; //当前列表页数
    public int mTotalPage = Integer.MAX_VALUE;//总页数
    SimpleLoadViewMore loadViewMore;
    public PulltoRefreshRecyclerView(Context context) {
        super(context);
        setUpView();
    }

    public PulltoRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();


    }

    public PulltoRefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    public RecyclerView getRecyclerView(){

        return recyclerView;
    }

    private void setUpView() {
        LayoutInflater.from(getContext()).inflate(R.layout.pulltorefresh_layout, this, true);
        ptrlayout = (PtrPullRefreshLayout) findViewById(R.id.ptr_frame);
        recyclerView = (RecyclerView) findViewById(R.id.mRecycler);
        if(layoutManager != null ){
            recyclerView.setLayoutManager(layoutManager);
        }else{
            recyclerView.setLayoutManager(new RefreshLinearLayoutManager(getContext()));
        }
        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtils.dp2px(getContext(),15), 0, DensityUtils.dp2px(getContext(),10));
        ptrlayout.setHeaderView(header);
        ptrlayout.disableWhenHorizontalMove(true);
        ptrlayout.addPtrUIHandler(header);
        ptrlayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if(ptrHandler != null){
                    mCurPager = 1;
                    LogUtils.d("refresh curPager :" + mCurPager);
                    if(baseQuickAdapter != null){


                    }
                    mIsRefreshing = true;
                    ptrHandler.onRefresh();
                }
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        recyclerView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (mIsRefreshing) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        );
    }
    private boolean mIsRefreshing=false;

    /**
     * 如果上拉结束后，下拉刷新需要再次开启上拉监听，需要使用setNewData方法填充数据。
     * @param newData
     */
    public <T> void setNewData(@Nullable List<T> newData){

        if(baseQuickAdapter != null){
            baseQuickAdapter.setNewData(newData);
        }
    }



    /**
     * 设置自动刷新
     */
    public void  setAutoRefresh() {
        ptrlayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrlayout.autoRefresh(false);
            }
        }, 100);
    }

        /**
         * 设置适配器
         * @param baseQuickAdapters
         */
    public void setAdapter(BaseQuickAdapter baseQuickAdapters){
        this.baseQuickAdapter = baseQuickAdapters;
        recyclerView.setAdapter(baseQuickAdapter);
//        baseQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        baseQuickAdapter.setEnableLoadMore(true);
        baseQuickAdapter.setUpFetchEnable(true);
        baseQuickAdapter.disableLoadMoreIfNotFullPage(recyclerView);
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if(ptrHandler != null){
                    mCurPager++;
                    LogUtils.d("refresh curPager :" + mCurPager);
                    ptrHandler.onLoadMore();
                }
            }
        },recyclerView);
        loadViewMore = new SimpleLoadViewMore();
        baseQuickAdapter.setLoadMoreView(loadViewMore);
    }


    class SimpleLoadViewMore extends LoadMoreView {

         @Override
         public int getLayoutId() {
             return R.layout.footer_layout_loading;
         }

         @Override
         protected int getLoadingViewId() {
             return R.id.load_more_loading_view;
         }

         @Override
         protected int getLoadFailViewId() {
             return R.id.load_more_load_fail_view;
         }

         @Override
         protected int getLoadEndViewId() {
             return R.id.load_more_load_end_view;
         }

     }


    /**
     * 添加头部
     * @param headerView
     */
    public void addHeaderView(View headerView){

        if(baseQuickAdapter != null){
            baseQuickAdapter.addHeaderView(headerView);
        }
    }

    /**
     * 添加尾部
     * @param footView
     */
    public void addFooterView(View footView){

        if(baseQuickAdapter != null){
            baseQuickAdapter.addFooterView(footView);
        }
    }

    /**
     * 设置空视图
     * @param layoutResId
     */
    public void setEmptyView(int layoutResId){

        if(baseQuickAdapter != null){
            baseQuickAdapter.setEmptyView(layoutResId);
        }
    }

    /**
     * 判断下拉刷新是否结束
     * @param totalPage 后台返回的totalPage
     * @return
     */
    public boolean  isLoadMoreEnd(int totalPage){
        if(totalPage < mCurPager || totalPage == 1){
            loadMoreEnd();
            if(totalPage == 1){
                loadViewMore.setLoadMoreEndGone(true);
            }
            return true;
        }
        return false;
    }


    /** 设置 LayoutManager
     * @param layout
     */
    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        this.recyclerView.setLayoutManager(layout);
    }

    /** 设置监听器
     * @param _ptrHandler
     */
    public void setPullRefreshListener(RecyPtrHandler _ptrHandler) {

        this.ptrHandler = _ptrHandler;
    }


    /**
     * 刷新完成
     */
    public void refreshComplete() {
        mIsRefreshing = false;
        ptrlayout.refreshComplete();
    }


    /**
     * 分页加载完成
     */
    public void loadMoreComplete() {
        if(baseQuickAdapter != null){
            baseQuickAdapter.loadMoreComplete();
        }
    }



    public  interface RecyPtrHandler  {

        void onLoadMore();

        void onRefresh();
    }

    /**
     * 加载完成
     */
    private void loadMoreEnd() {
        if(baseQuickAdapter != null){
            baseQuickAdapter.loadMoreEnd();
        }
    }
//
//    /**
//     * 是否可以加载更多
//     * @param enableLoadMore
//     */
//    private void setEnableLoadMore(boolean enableLoadMore){
//
//        if(baseQuickAdapter != null){
//            baseQuickAdapter.setEnableLoadMore(enableLoadMore);
//        }
//    }
}
