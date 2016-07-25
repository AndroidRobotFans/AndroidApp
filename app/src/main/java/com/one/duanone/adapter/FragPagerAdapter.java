package com.one.duanone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.one.duanone.fragment.BaseFragment;

import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  22:02.
 */
public class FragPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;

    public FragPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "标题" + position;
    }
}
