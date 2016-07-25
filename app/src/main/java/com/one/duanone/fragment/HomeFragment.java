package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.duanone.R;
import com.one.duanone.adapter.FragPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  13:51.
 */
public class HomeFragment extends BaseFragment {

    private ViewPager viewPager;
    private OnPagerChangeListener listener;
    private View view;
    private List<BaseFragment> listData;
    private FragPagerAdapter viewPagerAdapter;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listData = new ArrayList<>();
        view = inflater.inflate(R.layout.fragment_inner_view_pager, null);
        viewPager = $(R.id.content_viewPager);
        return view;
    }

    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void initFragmentData() {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(viewPagerListener);
        }
        for (int i = 0; i < 10; i++) {
            InnerFragment innerFragment = new InnerFragment();
            listData.add(innerFragment);
        }
        viewPagerAdapter = new FragPagerAdapter(getChildFragmentManager(), listData);

        viewPager.setAdapter(viewPagerAdapter);

    }

    /**
     * 接口回调
     */
    public interface OnPagerChangeListener {
        void onPagerChange(int pager);
    }

    /**
     * findViewById;
     *
     * @param resId
     * @param <T>
     * @return
     */
    private <T extends View> T $(int resId) {
        return (T) view.findViewById(resId);
    }

    public void setListener(OnPagerChangeListener listener) {
        this.listener = listener;
    }

}
