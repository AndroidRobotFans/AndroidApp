package com.one.duanone.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:20.
 */
public class MessageFragment extends CenterFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();


    private ImageView leftImage;
    private TextView rightText;
    private TextView centerText;
    private TextView centerText1;
    private BlackListFragment blackListFragment;
    private RelativeLayout contributeRelativeLayout,systemRelativeLayout,fansRelativeLayout;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.message_view, null);
        contributeRelativeLayout= (RelativeLayout) view.findViewById(R.id.message_contribute_rl);
        contributeRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    showContributeFragment(new MessageCtributeFragment());
                }
            }
        });
        systemRelativeLayout= (RelativeLayout) view.findViewById(R.id.message_system_rl);
        systemRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                showContributeFragment(new MessageSystemFragment());
                }
            }
        });
        fansRelativeLayout= (RelativeLayout) view.findViewById(R.id.message_fans_rl);
        fansRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                showContributeFragment(new MessageFansFragment());
                }
            }
        });
        return view;
    }


    @Override
    public void initFragmentData() {

        leftImage = (ImageView) View.inflate(getContext(), R.layout.message_left_image, null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    showUserFragment();
                }
            }
        });

        boolean image = leftImage == null;
        Log.i(TAG, "setLeftView: 初始化View: image : " + image);
        centerText = (TextView) View.inflate(getContext(), R.layout.message_center_text, null);
        rightText = (TextView) View.inflate(getContext(), R.layout.message_right_text, null);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (blackListFragment == null)
                        blackListFragment = new BlackListFragment();
                    listener.showFragment(blackListFragment);
                }

            }
        });

    }


    @Override
    public View getLeftView() {
        Log.i(TAG, "setLeftView: LeftView");
        return leftImage;
    }

    @Override
    public View getRightView() {
        return rightText;
    }

    @Override
    public View getCenterView() {
        return centerText;
    }



}

