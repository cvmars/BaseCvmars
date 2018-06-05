package com.cvmars.baseapp.ui.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class RefreshLinearLayoutManager extends LinearLayoutManager {
    public RefreshLinearLayoutManager(Context context) {
        super(context);
    }

    public RefreshLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public RefreshLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler,
                                  RecyclerView.State state) {
        try{
            return super.scrollVerticallyBy(dy,recycler,state);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //override this method and implement code as below
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } }
