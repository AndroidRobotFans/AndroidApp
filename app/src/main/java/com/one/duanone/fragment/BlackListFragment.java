package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.duanone.R;

/**
 * Created by jj on 2016/7/30.
 */
public class BlackListFragment extends CenterFragment{
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

    @Override
    protected void initFragmentData() {

    }

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_blacklist,null);
        return view;
    }
}