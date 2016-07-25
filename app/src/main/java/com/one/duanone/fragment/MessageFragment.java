package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:20.
 */
public class MessageFragment extends BaseFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        return textView;
    }

    @Override
    public void initFragmentData() {

    }
}
