package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;

public class PersonalFragment extends NotableFragment {

    private ImageView leftImage;
    private TextView centerView;
    private ImageView rightSetting;

    private RecyclerView recyclerView;
    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.persona_activityl, null);
        return view;
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


}
