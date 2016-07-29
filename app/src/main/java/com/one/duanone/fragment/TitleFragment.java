package com.one.duanone.fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.utils.LogUtils;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/24  20:26.
 */
public class TitleFragment extends BaseFragment {

    private LinearLayout leftLinear;
    private RelativeLayout centerRelative;
    private RelativeLayout rightRelative;
    private int count = 0;
    private static final String TAG = TitleFragment.class.getSimpleName();
    private View view;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.title_view, null);
        return view;
    }

    @Override
    public void initFragmentData() {
        leftLinear = (LinearLayout) view.findViewById(R.id.title_left_relative);
        centerRelative = (RelativeLayout) view.findViewById(R.id.title_center_relative);
        rightRelative = (RelativeLayout) view.findViewById(R.id.title_right_relative);
    }

    /**
     * 设置View
     *
     * @param view
     */
    public void setLeftView(View view) {
        if (leftLinear != null && view != null) {
            leftLinear.removeAllViews();
            leftLinear.addView(view);
        }
        boolean is = leftLinear == null;
        LogUtils.i(TAG, "setLeftView: " + is + ": count: " + count++);
    }

    public void setRightView(View view) {
        if (rightRelative!=null && view!=null){
            rightRelative.removeAllViews();
            rightRelative.addView(view);
        }

    }

    public void setCenterView(View view) {
        if (centerRelative != null && view !=null ){
            centerRelative.removeAllViews();
            centerRelative.addView(view);
        }
    }
}
