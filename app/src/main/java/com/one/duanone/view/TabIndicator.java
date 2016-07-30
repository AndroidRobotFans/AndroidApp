package com.one.duanone.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.one.duanone.R;
import com.one.duanone.adapter.FragPagerAdapter;
import com.one.duanone.bean.Pages;
import com.one.duanone.fragment.HomeFragment;
import com.one.duanone.utils.LogUtils;

import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  14:30.
 */
public class TabIndicator extends HorizontalScrollView {

    private static final String TAG = TabIndicator.class.getSimpleName();
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TextView currentTextView ;
    //含有View的个数,根据ViewPage的Adapter;来确定,然后生成count个TextView来做指示器
    private int haveViewCount;
    private HomeFragment.OnPagerChangeListener pagerChangeListener;

    public TabIndicator(Context context) {
        this(context, null);
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        linearLayout = (LinearLayout) View.inflate(context, R.layout.indication_layout, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        //设置LinearLayout的样式
        linearLayout.setLayoutParams(lp);
        this.addView(linearLayout);
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            throw new NullPointerException("ViewPager is not!");
        }
        if (viewPager.getAdapter() == null) {
            throw new NullPointerException("ViewPager is not Adapter");
        }
        this.viewPager = viewPager;
        pagerAdapter = viewPager.getAdapter();
        haveViewCount = viewPager.getAdapter().getCount();
        createTabView(haveViewCount);
    }

    /**
     * 创建,TabView
     *
     * @param count 创建TabView的个数
     *              dasd
     */
    private void createTabView(int count) {
        for (int i = 0; i < count; i++) {
            String title = pagerAdapter.getPageTitle(i).toString();
            final TabView tabView = new TabView(getContext(), title);
            //添加到LinearLayout中
            linearLayout.addView(tabView);
            final int finalI = i;

                tabView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (currentTextView !=null){
                            currentTextView.setTextColor(getContext().getResources().getColor(R.color.coffee2));
                        }
                        if (pagerChangeListener != null) {
                            pagerChangeListener.onPagerChange(finalI);
                        }
                        currentTextView=tabView;
                        currentTextView.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
                    }
                });

        }
    }

    public void setPagetListener(HomeFragment.OnPagerChangeListener pagerListener){
        this.pagerChangeListener = pagerListener;
    }

    class TabView extends TextView {
        public TabView(Context context, String title) {
            super(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            lp.gravity = Gravity.CENTER;
            lp.weight = 1;
            lp.setMargins(20, 5, 20, 5);
            Log.i(TAG, "TabView: 创建TextView" + title);
            this.setGravity(Gravity.CENTER);
            this.setText(title);
            this.setLayoutParams(lp);

        }
    }

}

