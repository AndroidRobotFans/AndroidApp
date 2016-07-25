package com.one.duanone.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  13:49.
 */
public class NoTouchImage extends ImageView {

    public NoTouchImage(Context context) {
        super(context);
    }

    public NoTouchImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
