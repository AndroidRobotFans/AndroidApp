package com.one.duanone.interfaces;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/2  22:32.
 */
public interface BaseRefreshHeader {
    int STATE_NORMAL = 0;
    int STATE_RELEAS_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplete();
}
