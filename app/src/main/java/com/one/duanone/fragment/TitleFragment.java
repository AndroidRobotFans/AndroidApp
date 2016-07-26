package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/24  20:26.
 */
public class TitleFragment extends BaseFragment {

    public View rightView,centerView;
    private View view;
    private RelativeLayout leftView;
    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.title_view,null);
        leftView= (RelativeLayout) view.findViewById(R.id.title_left_iv);
        rightView=view.findViewById(R.id.title_right_iv);
        centerView=view.findViewById(R.id.title_pick_rb);
        return view;
    }


    @Override
    public void initFragmentData() {

    }

    public void setLeftView(View view){
        if (leftView!=null && view!=null){
            leftView.removeAllViews();
            leftView.addView(view);
        }
        boolean is=leftView==null;
    }
    public void setRightView(View view){
        rightView=view;
    }
    public void setCenterView(View view){
       centerView=view;
    }
}
