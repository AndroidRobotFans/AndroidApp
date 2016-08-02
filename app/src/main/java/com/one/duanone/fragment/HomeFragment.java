package com.one.duanone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.one.duanone.R;
import com.one.duanone.adapter.FragPagerAdapter;
import com.one.duanone.bean.Pages;
import com.one.duanone.utils.JsonUtils;
import com.one.duanone.utils.NetUtils;
import com.one.duanone.utils.Utils;
import com.one.duanone.view.TabIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  13:51.
 */
public class HomeFragment extends CenterFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private ViewPager viewPager;
    private View view;
    private List<BaseFragment> listData;
    private FragPagerAdapter viewPagerAdapter;
    private TabIndicator indicator;
    private List<Pages> pageData;
    private ImageView image;
    private RadioGroup centerRadioGroup;
    private ImageView rightImage;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listData = new ArrayList<>();

        view = inflater.inflate(R.layout.fragment_inner_view_pager, null);

        viewPager = $(R.id.content_viewPager);
        indicator = $(R.id.content_indicator);
        pageData = new ArrayList<>();


        centerRadioGroup = (RadioGroup) View.inflate(getContext(), R.layout.home_center_radiogroup, null);
        image = (ImageView) View.inflate(getContext(), R.layout.home_left_image, null);
        rightImage = (ImageView) View.inflate(getContext(), R.layout.home_right_image, null);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    showUserFragment();
                    Log.i(TAG, "onClick: 444444444444444");
                }
            }
        });

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

        final NetUtils.NetCallBack callBack = new NetUtils.NetCallBack() {
            @Override
            public void succeed() {
                Log.i(TAG, "succeed: 成功");
                changeData();
            }

            @Override
            public void error(String e) {
                Log.i(TAG, "error: " + e);
            }
        };
        String jsonStr = Utils.openAssetsFile(context, "pageJson.txt");
        pageData = JsonUtils.getJsonBean(jsonStr, callBack);
        changeData();
    }

    /**
     * 改变数据
     */
    private void changeData() {

        for (int i = 0; i < pageData.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("url", pageData.get(i).getPageUrl());
            InnerFragment fragment = new InnerFragment();
            fragment.setArguments(bundle);
        }

        viewPagerAdapter = new FragPagerAdapter(getChildFragmentManager(), pageData);

        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.setPagesList(pageData);
//        indicator.setPagetListener(myListener);
        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    private OnPagerChangeListener myListener = new OnPagerChangeListener() {
        @Override
        public void onPagerChange(int pager) {
            viewPager.setCurrentItem(pager);
        }
    };

    @Override
    public View getLeftView() {
        return image;
    }

    @Override
    public View getRightView() {
        return rightImage;
    }

    @Override
    public View getCenterView() {
        return centerRadioGroup;
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


}
