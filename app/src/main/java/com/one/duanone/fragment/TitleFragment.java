package com.one.duanone.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.title_view, null);
        leftLinear = (LinearLayout) view.findViewById(R.id.title_left_relative);
        centerRelative = (RelativeLayout) view.findViewById(R.id.title_center_relative);
        rightRelative = (RelativeLayout) view.findViewById(R.id.title_right_relative);
        return view;
    }


    /**
     * 设置View
     *
     * @param view
     */


    private OnChangeTitleView listener = new OnChangeTitleView() {
        public void setLeftView(View view) {
            leftLinear.removeAllViews();
            if (view == null) return;
            if (leftLinear != null) {
                leftLinear.addView(view, lp);
            }
            boolean viws = view == null;
            boolean is = leftLinear == null;
            LogUtils.i(TAG, "setLeftView: " + is + ": count: " + count++ + ": View: " + viws);
        }

        public void setRightView(View view) {

            rightRelative.removeAllViews();
            if (view == null) return;
            if (rightRelative != null) {
                rightRelative.addView(view, lp);
            }
        }

        public void setCenterView(View view) {
            centerRelative.removeAllViews();
            if (view == null) return;
            if (centerRelative != null) {
                centerRelative.addView(view, lp);
            }
        }
    };

    public OnChangeTitleView getListener() {
        return listener;
    }

    public interface OnChangeTitleView {
        void setLeftView(View view);

        void setRightView(View view);

        void setCenterView(View view);
    }

}
