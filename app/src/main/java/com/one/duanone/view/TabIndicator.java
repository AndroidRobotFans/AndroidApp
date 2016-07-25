package com.one.duanone.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  14:30.
 */
public class TabIndicator extends HorizontalScrollView {

    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private int haveViewCount;

    public TabIndicator(Context context) {
        this(context, null);
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.indicator_parent, this);
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            throw new NullPointerException("ViewPager is not!");
        }
        pagerAdapter = viewPager.getAdapter();
        haveViewCount = pagerAdapter.getCount();
        createTabView(haveViewCount);
    }

    /**
     * 创建,TabView
     *
     * @param count 创建TabView的个数
     */
    private void createTabView(int count) {
        for (int i = 0; i < count; i++) {
            TabView tabView = new TabView(getContext());
            //添加到LinearLayout中
            linearLayout.addView(tabView);
        }
    }

    class TabView extends TextView {

        public TabView(Context context) {
            super(context);
        }
    }
}

