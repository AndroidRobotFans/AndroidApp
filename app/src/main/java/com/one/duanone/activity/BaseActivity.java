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
    private Fragment currentShowFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manger = getSupportFragmentManager();
    }

    /**
     * 添加整个Fragment数组
     *
     * @param fragments
     */
    public void addFragmentArray(BaseFragment[] fragments, int fragmentId) {
        for (BaseFragment baseFragment : fragments) {
            //遍历每一个,然后添加
            Log.i(TAG, "addFragmentArray: 添加");
            addFragment(fragmentId, baseFragment);
        }
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
     * @param isAdd    是否标记为当前显示的Fragment, 值用于Content中的Fragment
     */
    public void showFragment(Fragment fragment, boolean isAdd) {
        //如果fragment为空
        if (fragment == null) {
            Log.i(TAG, "showFragment:fragment为空");
            return;
        }
        if (currentShowFragment == fragment) {
            //已经显示, 就直接return
            return;
        }
        if (currentShowFragment != null && isAdd) {
            //不为空就直接隐藏当前的
            hidden(currentShowFragment);
        }
        Log.i(TAG, "showFragment: 显示成功");
        manger.beginTransaction().show(fragment).commit();
        if (isAdd) {
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

    public void showCenterAndHidt(Fragment show,Fragment hidden,int fragmentId){
        hidden(hidden);
        addFragment(fragmentId,show);
        showFragment(show,true);
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
