package com.one.duanone.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by jj on 2016/7/26.
 */
public abstract class CenterFragment extends BaseFragment{
//shit
    public abstract View getLeftView();
    public abstract View getRightView();
    public abstract View getCenterView();
    public ChangeFrage changeFrage;

    public interface ChangeFrage {
        void showFragment(Fragment fragment);
    }

    public void setChangFragment(ChangeFrage changeFrage){
        this.changeFrage = changeFrage;
    }
}
