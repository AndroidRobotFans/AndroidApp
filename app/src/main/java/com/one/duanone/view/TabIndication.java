package com.one.duanone.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  14:30.
 */
public class TabIndication extends HorizontalScrollView {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    public TabIndication(Context context) {
        this(context, null);
    }

    public TabIndication(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null){
            throw new NullPointerException("ViewPager is not!");
        }
    }
    class TabView extends TextView{

        public TabView(Context context) {
            super(context);
        }

    }
}

