package com.one.duanone.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.activity.MainActivity;

/**
 * Created by jj on 2016/7/30.
 */
public class BlackListFragment extends CenterFragment{
    private static final String TAG = BlackListFragment.class.getSimpleName();

    private ImageView leftImage;
    private TextView centerText;
    private TextView rightText;
    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_blacklist,null);

        return view;
    }

    @Override
    protected void initFragmentData() {

        leftImage= (ImageView) View.inflate(getContext(),R.layout.blacklist_left_image,null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
        centerText= (TextView) View.inflate(getContext(),R.layout.blacklist_center_text,null);
        rightText= (TextView) View.inflate(getContext(),R.layout.blacklist_right_text,null);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (backLisener!=null){
            backLisener.onBack();
            Log.i(TAG, "onHiddenChanged:----------- ");
        }
    }

    private OnBackListener backLisener;

    public void setBackLisener(OnBackListener backLisener) {
        this.backLisener = backLisener;
    }

    public interface OnBackListener{
        void onBack();
    }
}
