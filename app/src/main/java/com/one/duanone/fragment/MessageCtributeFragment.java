package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;

public class MessageCtributeFragment extends NotableFragment {

    private ImageView leftImage;
    private TextView centerText;
    private TextView rightText;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
    @Override
    protected void initFragmentData() {
        leftImage= (ImageView) View.inflate(getContext(), R.layout.message_left_contribute,null);
    }

    @Override
    public View getLeftView() {
        return null;
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
