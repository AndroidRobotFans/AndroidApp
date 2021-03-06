package com.one.duanone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.one.duanone.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Masterr_Robot on 2016/7/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private FragmentManager manger;
    public Fragment currentShowFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manger = getSupportFragmentManager();
    }

    /**
     * 添加fragment
     *
     * @param fragmentId 添加的目标 FragmentLayout ID
     * @param fragment   添加的Fragment
     */
    public void addFragment(int fragmentId, Fragment fragment) {
        //判断是否添加过,,没有添加过再添加
        if (!isHave(fragment)) {
            Log.i(TAG, "addFragment: 添加成成功");
            manger.beginTransaction().add(fragmentId, fragment).commit();
        }
    }

    /**
     * 显示fragment
     *
     * @param fragment
     * @param isShowCenter    是否标记为当前显示的Fragment, 值用于Content中的Fragment
     */
    public void showFragment(Fragment fragment, boolean isShowCenter) {
        //如果fragment为空
        if (fragment == null) {
            Log.i(TAG, "showFragment:fragment为空");
            return;
        }
        if (currentShowFragment == fragment) {
            //已经显示, 就直接return
            return;
        }
        if (currentShowFragment != null && isShowCenter) {
            //不为空就直接隐藏当前的
            hidden(currentShowFragment);
        }
        Log.i(TAG, "showFragment: 显示成功");
        manger.beginTransaction().show(fragment).commit();
        if (isShowCenter) {
            currentShowFragment = fragment;
        }
    }

    /**
     * 隐藏Fragment
     *
     * @param fragment
     */
    private void hidden(Fragment fragment) {
        Log.i(TAG, "hidden: 隐藏");
        manger.beginTransaction().hide(fragment).commit();
    }

    /**
     * 替换fragment
     * @param show   要显示的fragment
     * @param hidden 要隐藏的fragment
     * @param fragmentId 中间的framelayout
     */
    public void showCenterAndHidt(Fragment show,Fragment hidden,int fragmentId){
       if (!isHave(show)){
           manger.beginTransaction().addToBackStack(null).add(fragmentId,show).commit();
       }
        showFragment(show,true);
        hidden(hidden);
    }



    /**
     * 判断fragment是否添加过
     *
     * @param fragment
     * @return
     */
    public boolean isHave(Fragment fragment) {
        if (manger.getFragments() == null) {
            //manger.getFragments() 为空说明没有添加过,返回false
            return false;
        }
        //getFragments()返回是所有已经添加过的Fragment的List集合
        return manger.getFragments().contains(fragment);
    }

}
