package com.one.duanone.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.one.duanone.R;
import com.one.duanone.activity.PersonalActivity;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:20.
 */
public class MessageFragment extends CenterFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();


    private ImageView leftImage;
    private TextView rightText;
    private TextView centerText;


    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getContext(),R.layout.message_view,null);


        return view;
    }

    @Override
    public void initFragmentData() {

        leftImage = (ImageView) View.inflate(getActivity(), R.layout.message_left_image, null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
            }
        });

        boolean image = leftImage ==null;
        Log.i(TAG, "setLeftView: 初始化View: image : "+ image);
        centerText= (TextView) View.inflate(getActivity(),R.layout.message_center_text,null);
        rightText= (TextView) View.inflate(getActivity(),R.layout.message_right_text,null);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.showFragment();
                }

            }
        });

        Log.i(TAG, "getFragmentView: ");
    }

    @Override
    public View getLeftView() {
        Log.i(TAG, "setLeftView: LeftView");
        if (leftImage == null){
            initFragmentData();
        }
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


    public interface ShowCenterFragment{
        void showFragment();
    }


}
