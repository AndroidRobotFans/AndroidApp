package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:20.
 */
public class MessageFragment extends CenterFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();
    private ImageView leftImage;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        leftImage = (ImageView) View.inflate(getContext(), R.layout.message_left_image, null);
        return textView;
    }

    @Override
    public void initFragmentData() {

    }

    @Override
    public View getLeftView() {
        return leftImage;
    }

    @Override
    public View getRightView() {
        return null;
    }

    @Override
    public View getCenterView() {
        return null;
    }
}
