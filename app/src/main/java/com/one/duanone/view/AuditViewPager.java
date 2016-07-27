package com.one.duanone.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以设置禁止滑动的 ViewPager(单向禁止：左滑动)
 * @author alan
 */
public class AuditViewPager extends ViewPager {
    //上一次x坐标
    private float beforeX;
    //是否可以滑动
    private boolean isCanScroll = false;

    public AuditViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AuditViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return super.dispatchTouchEvent(ev);
        }
        else {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    beforeX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float motionValue = ev.getX() - beforeX;
                    if (motionValue > 0) {
                        //禁止左滑
                        return false;
                    }
                    //手指移动时，再把当前的坐标作为下一次的‘上次坐标’
                    beforeX = ev.getX();
                    break;
                default:
                    break;
            }
            return super.dispatchTouchEvent(ev);
        }
    }

}