package com.one.duanone.activity;

import android.os.Bundle;

import com.one.duanone.R;
import com.one.duanone.fragment.HomeFragment;
import com.one.duanone.fragment.TabBottomFragment;
import com.one.duanone.fragment.TitleFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabBottomFragment tabFragment;
    private HomeFragment contentFragment;
    private TitleFragment titleFragment;

    private int titleFragmentId;
    private int contentFragmentId;
    private int tabBottomFragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }


    private void initFragment() {
        tabFragment = new TabBottomFragment();
        contentFragment = new HomeFragment();
        titleFragment = new TitleFragment();

        //找到Fragment的ID
        tabBottomFragmentId = R.id.main_bottom_tab;
        contentFragmentId = R.id.main_fragment_content;
        titleFragmentId = R.id.main_fragment_title;
        //向TabFragment中添加
        addFragment(tabBottomFragmentId, tabFragment);
        //显示出tabFragment
        showFragment(tabFragment);

        addFragment(titleFragmentId, titleFragment);
        showFragment(titleFragment);

        addFragment(contentFragmentId,contentFragment);
        showFragment(contentFragment);

    }

}
