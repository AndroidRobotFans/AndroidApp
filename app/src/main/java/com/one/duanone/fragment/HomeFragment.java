package com.one.duanone.fragment;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.activity.PersonalActivity;
import com.one.duanone.adapter.FragPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  13:51.
 */
public class HomeFragment extends CenterFragment {

    private ViewPager viewPager;
    private OnPagerChangeListener listener;
    private View view;
    private List<BaseFragment> listData;
    private FragPagerAdapter viewPagerAdapter;
    //    左上角头像
    private View leftView;
    private static final String TAG = AuditFragment.class.getSimpleName();

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listData = new ArrayList<>();
        view = inflater.inflate(R.layout.fragment_inner_view_pager, null);
        viewPager = $(R.id.content_viewPager);
        initView();
        leftView =  view.inflate(getContext(),R.layout.home_left_view,null);
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView() {
//        View view =LayoutInflater.from(getContext()).inflate(R.layout.home_left_view,null);
//        leftView = view.findViewById(R.id.home_left_view_ll);
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

    @Override
    public View getLeftView() {


        return leftView;
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


    public void setListener(OnPagerChangeListener listener) {
        this.listener = listener;
    }

    public <T extends View> T $(int resId) {
        return (T) view.findViewById(resId);
    }
}
