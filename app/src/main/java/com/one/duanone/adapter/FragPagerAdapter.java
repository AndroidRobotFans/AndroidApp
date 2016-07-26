package com.one.duanone.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.one.duanone.bean.Pages;
import com.one.duanone.fragment.BaseFragment;
import com.one.duanone.fragment.InnerFragment;

import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  22:02.
 */
public class FragPagerAdapter extends FragmentPagerAdapter {

    private List<Pages> pagesList;

    public FragPagerAdapter(FragmentManager fm, List<Pages> pagesList) {
        super(fm);
        this.pagesList = pagesList;
    }

    @Override
    public Fragment getItem(int position) {
        if (pagesList.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", pagesList.get(position).getPageUrl());
        InnerFragment fragment = new InnerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return pagesList.size();
    }

    public void setPagesList(List<Pages> pagesList) {
        this.pagesList = pagesList;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (pagesList.isEmpty()) {
            return "";
        }
        return pagesList.get(position).getPageTitle();
    }
}
