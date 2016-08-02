package com.one.duanone.fragment;

import android.util.Log;

/**
 * Created by jj on 2016/8/2.
 */
public abstract class NotableFragment extends CenterFragment {

    private static final String TAG = NotableFragment.class.getSimpleName();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (backLisener != null) {
            backLisener.onBack();
            Log.i(TAG, "onHiddenChanged:----------- ");
        }
    }



    public OnBackListener backLisener;

    public void setBackLisener(OnBackListener backLisener) {
        this.backLisener = backLisener;
    }

    public interface OnBackListener {
        void onBack();
    }

}
