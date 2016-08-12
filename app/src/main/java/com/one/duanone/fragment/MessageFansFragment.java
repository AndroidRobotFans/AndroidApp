package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.adapter.SystemAdapter;
import com.one.duanone.bean.News;

import java.util.ArrayList;
import java.util.List;

public class MessageFansFragment extends NotableFragment {

    private ImageView leftImage;
    private TextView centerText;
    private ImageView rightImage;
    private View view;
    private ListView listView;
    private List<Fragment> list = new ArrayList<>();

    private AttentionFragment mAttentionFragment;
    private FansFragment mFansFragment;

    private Fragment curretFragment;

    private RadioButton attentionRB, fansRB;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(context, R.layout.message_fans_fragment, null);
        initView();
        return view;
    }

    private void initView() {
        mAttentionFragment = new AttentionFragment();
        mFansFragment = new FansFragment();

//        list.add(mAttentionFragment);
//        list.add(mFansFragment);

        addFragment(mAttentionFragment);
        addFragment(mFansFragment);

        selectorFragment(mAttentionFragment);
        attentionRB = (RadioButton) view.findViewById(R.id.fans_attention);
        attentionRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectorFragment(mAttentionFragment);
            }
        });
        fansRB = (RadioButton) view.findViewById(R.id.fans_fans);
        fansRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    selectorFragment(mFansFragment);
            }
        });


    }

    private void addFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().add(R.id.fans_frame, fragment);
    }

    private void selectorFragment(Fragment fragment) {
//        if (curretFragment != null) {
//            getChildFragmentManager().beginTransaction().hide(curretFragment).commit();
//        }
        getChildFragmentManager().beginTransaction().replace(R.id.fans_frame,fragment).commit();
//        curretFragment = fragment;
    }

    @Override
    protected void initFragmentData() {
        leftImage = (ImageView) View.inflate(getContext(), R.layout.contribute_leftimage, null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
        centerText = (TextView) View.inflate(getContext(), R.layout.fans_centertext, null);
        rightImage = (ImageView) View.inflate(getContext(), R.layout.fans_rightimage, null);
        rightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        return centerText;
    }

}
