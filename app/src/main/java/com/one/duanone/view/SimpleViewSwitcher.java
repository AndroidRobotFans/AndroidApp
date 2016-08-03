package com.one.duanone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/2  22:24.
 */
public class SimpleViewSwitcher extends ViewGroup {

    public SimpleViewSwitcher(Context context) {
        super(context);
    }

    public SimpleViewSwitcher(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleViewSwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = this.getChildCount();
        int maxHeight = 0;
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            int cw = child.getMeasuredWidth();
//            int ch = child.getMeasuredHeight();

            maxWidth = child.getMeasuredWidth();
            maxHeight = child.getMeasuredHeight();
        }
        setMeasuredDimension(maxWidth, maxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(0, 0, r - 1, b - 1);
            }
        }
    }

    public void setView(View view) {
        if (this.getChildCount() != 0) {
            this.removeViewAt(0);
        }
        this.addView(view, 0);
    }
}
