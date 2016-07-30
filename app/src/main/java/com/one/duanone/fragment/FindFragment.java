package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.adapter.MyFindAdapter;
import com.one.duanone.adapter.RollVPAdapter;
import com.one.duanone.view.RollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:10.
 */
public class FindFragment extends CenterFragment {
    private View mView;
    private View mViewVP;
    //声明listView的控件
    private ListView mListView;
    private List<String> mList;
    private MyFindAdapter mAdapter;
    //声明顶部轮播图片的控件
    private RollViewPager mViewPager;
    private List<ImageView> mImageList;
    private RollVPAdapter mRollVPAdapter;
    //声明轮播图标题的控件
    private TextView mImageTitle;
    private List<String> mTitleList;
    //声明轮播图右下角小圆点的控件
    private LinearLayout mLayoutDoc;
    private List<ImageView> mImageDocList;

    private static final String TAG = FindFragment.class.getSimpleName();
    private ImageView leftImage;
    private ImageView rightImage;
    private RadioGroup centerRadioGroup;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        leftImage = (ImageView) View.inflate(getContext(), R.layout.find_left_image, null);
        rightImage = (ImageView) View.inflate(getContext(), R.layout.find_right_image, null);
        centerRadioGroup = (RadioGroup) View.inflate(getContext(), R.layout.find_center_radiogroup, null);
        //实例化ListView的控件
        mView = View.inflate(getContext(), R.layout.fragment_find, null);
        mListView = $(R.id.fragment_find_listview);
        mList = new ArrayList<>();
        mAdapter = new MyFindAdapter(mList, getContext());
        //实例化顶部轮播图ViewPager的控件
        mViewVP = View.inflate(getContext(), R.layout.top_roll_viewpager, null);
        mViewPager = (RollViewPager) mViewVP.findViewById(R.id.top_roll_viewpager);
        mImageList = new ArrayList<>();
        mRollVPAdapter = new RollVPAdapter(mImageList, getContext());
        //实例化轮播图标题的控件
        mImageTitle = (TextView) mViewVP.findViewById(R.id.top_roll_textview_title);
        mTitleList = new ArrayList<>();
        //实例化轮播图右下角小圆点的控件
        mLayoutDoc = (LinearLayout) mViewVP.findViewById(R.id.top_roll_layout_dot);
        mImageDocList = new ArrayList<>();


        return mView;
    }

    @Override
    public void initFragmentData() {
        //ListView的假数据
        for (int i = 0; i < 20; i++) {
            mList.add("我是第" + i + "条假数据");
        }
        mListView.addHeaderView(mViewVP);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        //顶部轮播图ViewPager的假数据
        ImageView[] imageViews = new ImageView[5];//应该是mImageList.size()
        for (int i = 0; i < 5; i++) {
            imageViews[i] = new ImageView(getContext());
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews[i].setImageResource(R.drawable.splash_slogan);
            mImageList.add(imageViews[i]);
        }
        //设置轮播图片标题
        setTopImageTitle(0);
        //设置轮播图片小圆点
        setDoc();
        //轮播图ViewPager的监听事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //调用设置轮播图标题的方法
                setTopImageTitle(position);
                //调用改变小圆点颜色的方法
                switchDoc(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(mRollVPAdapter);
    }

    /**
     * 设置轮播图标题的方法
     */
    private void setTopImageTitle(int position){
        for (int i = 0; i < mImageList.size(); i++) {
            mTitleList.add("我是假数据的标题" + i);
        }
        mImageTitle.setText(mTitleList.get(position));
    }

    /**
     * 设置轮播图片右下角小圆点的方法
     */
    private void setDoc(){
        for (int i = 0; i < mImageList.size(); i++) {
            ImageView imageDoc = new ImageView(getContext());
            mLayoutDoc.addView(imageDoc);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageDoc.getLayoutParams();
            params.width = 15;
            params.height = 15;
            params.setMargins(0, 0, 10, 0);
            imageDoc.setLayoutParams(params);
            imageDoc.setImageResource(R.color.white);
            mImageDocList.add(imageDoc);
        }
        //设置小圆点初始颜色
        if (mImageList != null && mImageList.size() > 0){
            mImageDocList.get(0).setImageResource(R.color.coffee);
        }
    }

    /**
     * 切换轮播图小圆点的方法
     * @param position
     */
    private void switchDoc(int position){
        for (int i = 0; i < mImageList.size(); i++) {
            if (position == i){
                mImageDocList.get(i).setImageResource(R.color.coffee);
            } else {
                mImageDocList.get(i).setImageResource(R.color.white);
            }
        }
    }

    @Override
    public View getLeftView() {

        return leftImage;
    }

    @Override
    public View getRightView() {
        return rightImage;
    }

    @Override
    public View getCenterView() {
        return centerRadioGroup;
    }

    /**
     * findViewBy id
     *
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T $(int resId) {
        return (T) mView.findViewById(resId);
    }

}
