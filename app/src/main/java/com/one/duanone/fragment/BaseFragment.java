package com.one.duanone.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Masterr_Robot on 2016/7/22.
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    public Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = getFragmentView(inflater, container, savedInstanceState);
        return view;
    }


    public abstract View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


}
