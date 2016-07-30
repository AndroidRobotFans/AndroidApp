package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jj on 2016/7/26.
 */
public abstract class CenterFragment extends BaseFragment{
//shit
    public abstract View getLeftView();
    public abstract View getRightView();
    public abstract View getCenterView();
    public ChangeFrage changeFrage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("TAG", "onCreateView: shishsihsihsishi");
        View view = getFragmentView(inflater, container, savedInstanceState);
        initFragmentData();
        return view;
    }
    public void setListener(TitleFragment.OnChangeTitleView listener) {

         listener.setRightView(getRightView());
         listener.setCenterView(getCenterView());
         listener.setLeftView(getLeftView());
    }


    protected abstract void initFragmentData();

    public interface ChangeFrage {
        void showFragment(Fragment fragment);
    }

    public void setChangFragment(ChangeFrage changeFrage){
        this.changeFrage = changeFrage;
    }
}
