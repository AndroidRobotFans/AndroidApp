package com.one.duanone.indicator;

import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.Animation;

import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/2  23:00.
 */
public abstract class BaseIndicatorController {

    private View mTarger;
    private List<Animator> mAnimators;

    public void setTarget(View target) {
        this.mTarger = target;
    }

    public View getTarger() {
        return mTarger;
    }

    public int getWidth() {
        return mTarger.getWidth();
    }

    public int getHeight() {
        return mTarger.getHeight();
    }

    public void postInvalidate() {
        mTarger.postInvalidate();
    }

    /**
     * draw indicator
     * @param canvas
     * @param paint
     */
    public abstract void draw(Canvas canvas, Paint paint);

    public abstract List<Animator> createAnimation();

    public void initAnimation(){
        mAnimators = createAnimation();
    }

    public void setAnimationStatus(AnimStatus animStatus){
        if (mAnimators ==null){
            return;
        }
        int count = mAnimators.size();
        for (int i = 0; i < count; i++) {
            Animator animator = mAnimators.get(i);
            boolean isRunning = animator.isRunning();
            switch (animStatus){
                case START:
                    if (!isRunning){
                        animator.start();
                    }
                    break;
                case END:
                    if (isRunning){
                        animator.end();
                    }
                    break;
                case CANCEL:
                    if (isRunning){
                        animator.cancel();
                    }
                    break;
            }
        }
    }

    /**
     * 状态枚举类
     */
    public  enum AnimStatus{
            START,END,CANCEL
        }
}
