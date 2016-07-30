package com.one.duanone.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.one.duanone.R;
import com.one.duanone.fragment.AuditFragment;
import com.one.duanone.fragment.CenterFragment;
import com.one.duanone.fragment.FindFragment;
import com.one.duanone.fragment.HomeFragment;
import com.one.duanone.fragment.MessageFragment;
import com.one.duanone.fragment.TabBottomFragment;
import com.one.duanone.fragment.TitleFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabBottomFragment tabFragment;
    private TitleFragment titleFragment;
    private CenterFragment[] fragmentArray = new CenterFragment[4];

    private int titleFragmentId;
    private int contentFragmentId;
    private int tabBottomFragmentId;

    private CenterFragment.ChangeFrage changeFrage = new CenterFragment.ChangeFrage() {
        @Override
        public void showFragment(Fragment fragment) {
            addFragment(contentFragmentId, fragment);
            showFragment(fragment);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private TabBottomFragment.OnChangPagerListener tabListener = new TabBottomFragment.OnChangPagerListener() {
        @Override
        public void onChangPager(int pager) {
            Log.i(TAG, "onChangPager: " + pager);
            //从数组中那到当前点击所对应的Fragment
            CenterFragment fragment = fragmentArray[pager];
                Log.i(TAG, "onChangPager: 添加" + pager);
                addFragment(contentFragmentId, fragment);
            //设置Title的三个View
            fragment.setListener(titleFragment.getListener());
            showFragment(fragment, true);
            Log.i(TAG, "onChangPager: pager完了" + pager);
        }
    };

    //初始化
    private void initFragment() {


        //初始化Fragment数组, 一定要和下面Tab的顺序一致
        fragmentArray[0] = new HomeFragment();
        fragmentArray[1] = new FindFragment();
        fragmentArray[2] = new AuditFragment();
        fragmentArray[3] = new MessageFragment();

        tabFragment = new TabBottomFragment();
        titleFragment = new TitleFragment();

        //找到Fragment的ID
        tabBottomFragmentId = R.id.main_bottom_tab;
        contentFragmentId = R.id.main_fragment_content;
        titleFragmentId = R.id.main_fragment_title;

        //向TabFragment中添加
        addFragment(tabBottomFragmentId, tabFragment);
        //显示出tabFragment
        showFragment(tabFragment, false);

        addFragment(titleFragmentId, titleFragment);

        showFragment(titleFragment, false);


        for (int i = 0; i < fragmentArray.length; i++) {
            fragmentArray[i].setChangFragment(changeFrage);
        }

        //设置Tab的点击监听事件
        tabFragment.setListener(tabListener);

//       tabFragment.setPager(0);
    }

}
