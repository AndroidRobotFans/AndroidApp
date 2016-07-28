package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.adapter.RollVPAdapter;
import com.one.duanone.view.AuditViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:18.
 */
public class AuditFragment extends CenterFragment {

    private View mView;
    //声明ViewPager的控件
    private AuditViewPager mViewPager;
    private List<ImageView> mImageList;
    private RollVPAdapter mRollVPAdapter;

    private static final String TAG = AuditFragment.class.getSimpleName();
    private ImageView leftImage;
    private ImageView rightImage;
    private TextView centerTextView;
    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        leftImage = (ImageView) View.inflate(getContext(), R.layout.audit_left_image,null);
        rightImage = (ImageView) View.inflate(getContext(), R.layout.home_right_image,null);
        centerTextView = (TextView) View.inflate(getContext(), R.layout.audit_center_text,null);
        mView = View.inflate(getContext(), R.layout.fragment_audit, null);
        mViewPager = (AuditViewPager) mView.findViewById(R.id.fragment_audit_viewpager);
        mImageList = new ArrayList<>();
        mRollVPAdapter = new RollVPAdapter(mImageList, getContext());
        return mView;
    }

    @Override
    public void initFragmentData() {
        //设置ViewPager的假数据
        ImageView[] imageViews = new ImageView[5];//应该是mImageList.size()
        for (int i = 0; i < 5; i++) {
            imageViews[i] = new ImageView(getContext());
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews[i].setImageResource(R.drawable.splash_slogan);
            mImageList.add(imageViews[i]);
        }
        mViewPager.setAdapter(mRollVPAdapter);
        mRollVPAdapter.notifyDataSetChanged();
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
        return centerTextView;
    }
}
