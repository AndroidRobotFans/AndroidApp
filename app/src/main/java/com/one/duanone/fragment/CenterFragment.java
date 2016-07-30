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
 * Created by jj on 2016/7/26.
 */
public abstract class CenterFragment extends BaseFragment {
    public MessageFragment.ShowCenterFragment listener;
    private TitleFragment.OnChangeTitleView changListener;
    private boolean isSecond = false;

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
//  每次点击bottom按钮回调这个方法
    public void setListener(TitleFragment.OnChangeTitleView listener) {
        this.changListener = listener;
        //第一次不调用
        //后面每次点击都会调用
        if (isSecond){
            changeState();
        }
    }

//    创建完成后 自动回调设置title的三个view
//    onActivityCreated在生命周期里只会执行一次（创建之后不再执行）
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changeState();
//        第一次之后调用将isSecond设置为true
        isSecond = true;
    }

    private void changeState() {
        if (changListener != null) {
            changListener.setRightView(getRightView());
            changListener.setCenterView(getCenterView());
            changListener.setLeftView(getLeftView());
        }
    }

    protected abstract void initFragmentData();

    public interface ChangeFrage {
        void showFragment(Fragment fragment);
    }

    public void setChangFragment(ChangeFrage changeFrage) {
        this.changeFrage = changeFrage;
    }

    public void setShowListener(MessageFragment.ShowCenterFragment listener) {
        this.listener = listener;
    }
}
