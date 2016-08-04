package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends NotableFragment implements View.OnClickListener {

    private static final String TAG = PersonalFragment.class.getSimpleName();

    private ImageView leftImage;
    private TextView centerView;
    private ImageView rightSetting;
    private View view;
    //   三个要点击的tv
    private TextView contributestv;
    private TextView collectstv;
    private TextView commentstv;
    private TextView currentTextView;
    private LinearLayout bottomlinear;

    private List<Fragment>mFragmentList=new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPagerVp;

    private ContributeFragment mContributeFragment;
    private CollectFragment mCollectFragment;
    private CommentFragment mCommentFragment;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_persona, null);
        initView();
        return view;
    }

    private void initView() {

        mContributeFragment=new ContributeFragment();
        mCollectFragment=new CollectFragment();
        mCommentFragment=new CommentFragment();
        mPagerVp= (ViewPager) view.findViewById(R.id.personal_viewpager);

        mFragmentList.add(mContributeFragment);
        mFragmentList.add(mCollectFragment);
        mFragmentList.add(mCommentFragment);

        mFragmentAdapter=new FragmentAdapter(this.getChildFragmentManager(),mFragmentList);
        mPagerVp.setAdapter(mFragmentAdapter);



        mPagerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setTextPager(contributestv);
                        break;
                    case 1:
                        setTextPager(collectstv);
                        break;
                    case 2:
                        setTextPager(commentstv);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        contributestv = (TextView) view.findViewById(R.id.personal_contributes);
        collectstv = (TextView) view.findViewById(R.id.personal_collect);
        commentstv = (TextView) view.findViewById(R.id.personal_comment);
        mPagerVp= (ViewPager) view.findViewById(R.id.personal_viewpager);
        contributestv.setOnClickListener(this);
        collectstv.setOnClickListener(this);
        commentstv.setOnClickListener(this);
//        设置默认选中的为contributestv;
        setTextPager(contributestv);

    }


    @Override
    public View getLeftView() {
        return leftImage;
    }

    @Override
    public View getRightView() {
        return rightSetting;
    }

    @Override
    public View getCenterView() {
        return centerView;
    }

    @Override
    protected void initFragmentData() {
        if (leftImage == null) {
            leftImage = new ImageView(getContext());
        }
        leftImage.setImageResource(R.drawable.back_white_arrow);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
        centerView = new TextView(getContext());
        centerView.setText("一月又一月");
        rightSetting = new ImageView(getContext());
        rightSetting.setImageResource(R.drawable.ic_setting_normal);
    }


    @Override
    public void onClick(View v) {
        int pager=0;
       setTextPager((TextView) v);
        switch (v.getId()) {
            case R.id.personal_contributes:
                pager=0;
                break;
            case R.id.personal_collect:
                pager=1;
                break;
            case R.id.personal_comment:
                pager=2;
                break;
            default:
                break;
        }
        mPagerVp.setCurrentItem(pager);
        Log.i(TAG, "onClick: -------------------------------");
    }

    public void setTextPager(TextView current) {

        if (currentTextView != null)
            currentTextView.setTextColor(getContext().getResources().getColor(R.color.gray));
        current.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
        currentTextView=current;
    }
}
