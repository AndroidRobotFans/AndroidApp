package com.one.duanone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Masterr_Robot on 2016/7/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private FragmentManager manger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manger = getSupportFragmentManager();
    }

    /**
     * 添加fragment
     *
     * @param fragmentId 添加的目标 FragmentLayout ID
     * @param fragment 添加的Fragment
     */
    public void addFragment(int fragmentId, Fragment fragment) {

        FragmentTransaction transaction = manger.beginTransaction();
        //判断是否添加过,,没有添加过在添加
        if (!isHave(fragment)) {
            Log.i(TAG, "addFragment: 添加成成功");
            transaction.add(fragmentId, fragment).commit();
        }
        Log.i(TAG, "addFragment: ");
    }

    /**
     * 显示fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        //如果fragment为空, 或者没有添加过直接返回
        if (fragment == null || !isHave(fragment)) {
            Log.i(TAG, "showFragment:fragment为空, 或者没有添加过 ");
            return;
        }
        if (!fragment.isHidden()) {
            //已经显示
            Log.i(TAG, "showFragment:已经显示 ");
            return;
        }
        Log.i(TAG, "showFragment: 显示成功");
        manger.beginTransaction().show(fragment).commit();
    }

    /**
     * 判断fragment是否添加过
     *
     * @param fragment
     * @return
     */
    private boolean isHave(Fragment fragment) {
        if (manger.getFragments() == null) {
            //manger.getFragments() 为空说明没有添加过,返回false
            return false;
        }
        //getFragments()返回是所有已经添加过的Fragment的List集合
        return manger.getFragments().contains(fragment);
    }

}
