package com.one.duanone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.activity.PersonalActivity;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  15:20.
 */
public class MessageFragment extends CenterFragment {

    private static final String TAG = MessageFragment.class.getSimpleName();
    private View view;
    private ImageView leftImage;
    private TextView rightText;
    private TextView centerText;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getContext(),R.layout.message_view,null);
        leftImage = (ImageView) View.inflate(getContext(), R.layout.message_left_image, null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
            }
        });
        centerText= (TextView) View.inflate(getContext(),R.layout.message_center_text,null);
        rightText= (TextView) View.inflate(getContext(),R.layout.message_right_text,null);

        return view;
    }

    @Override
    public void initFragmentData() {

    }

    @Override
    public View getLeftView() {
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
