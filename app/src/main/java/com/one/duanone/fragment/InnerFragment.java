package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/24  20:33.
 */
public class InnerFragment extends BaseFragment {

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inner_list_view,null);

        return view;
    }

    @Override
    public void initFragmentData() {

    }
}
