package com.one.duanone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.one.duanone.R;
import com.one.duanone.activity.PersonalActivity;
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
    private OnPagerChangeListener listener;
    private View view;
    private List<BaseFragment> listData;
    private FragPagerAdapter viewPagerAdapter;
    private TabIndicator indicator;
    private List<Pages> pageData;
    private ImageView image;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listData = new ArrayList<>();

        view = inflater.inflate(R.layout.fragment_inner_view_pager, null);

        viewPager = $(R.id.content_viewPager);
        indicator = $(R.id.content_indicator);
        pageData = new ArrayList<>();

        image = (ImageView) View.inflate(getContext(), R.layout.home_left_image, null);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
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
        for (int i = 0; i < 10; i++) {
            InnerFragment innerFragment = new InnerFragment();
            listData.add(innerFragment);
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
    }

    /**
     * 改变数据
     */
    private void changeData() {
        viewPagerAdapter = new FragPagerAdapter(getChildFragmentManager(), pageData);

        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.setPagesList(pageData);
        indicator.setViewPager(viewPager);
    }

    @Override
    public View getLeftView() {
        return image;
    }

    @Override
    public View getRightView() {
        return null;
    }

    @Override
    public View getCenterView() {
        return null;
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
