package com.one.duanone.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.one.duanone.adapter.LRecyclerViewAdapter;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/2  19:36.
 */
public class LRecyclerView extends RecyclerView {

    private boolean pullRefershEnabled = true;

    private LScrollListener mLScrollListener;
    private ArrowRefreshHeader mRefreshHeader;
    private View mEmptyView;
    private final RecyclerView.AdapterDataObserver mDataObserver = new DataObserver();
    private float mLastY = -1;
    private static final float DRAG_RATE = 3;
    private LRecyclerViewAdapter mAdapter;


    public LRecyclerView(Context context) {
        super(context);
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public interface LScrollListener {
        void onRefresh();// pull down to refresh

        void onScrollUp();//scroll down to up

        void onScrollDown();//scroll down up to down

        void onBottom();//load next page

        void onScrolled(int distanceX, int distanceY);//moving state you can get the move distance
    }

    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            super.onChanged();
        }
    }

}
