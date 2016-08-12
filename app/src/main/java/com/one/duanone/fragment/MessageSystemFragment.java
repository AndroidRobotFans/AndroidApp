package com.one.duanone.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.adapter.SystemAdapter;
import com.one.duanone.bean.News;

import java.util.ArrayList;
import java.util.List;

public class MessageSystemFragment extends NotableFragment {

    private ImageView leftImage;
    private TextView centerText;
    private TextView rightText;
    private View view;
    ListView listView;
    List<News.User> list=new ArrayList<>();
    SystemAdapter mSystemAdapter;
    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(context,R.layout.message_contribute_fragment,null);
        initView();
        return view;
    }

    private void initView() {
        listView= (ListView) view.findViewById(R.id.conribute_listview);
        mSystemAdapter=new SystemAdapter(list,context);
        for (int i = 0; i <20; i++) {
            News.User user=new News.User();
            user.setPersonal_comment("一剪梅");
            list.add(user);
        }
        listView.setAdapter(mSystemAdapter);
        mSystemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initFragmentData() {
        leftImage= (ImageView) View.inflate(getContext(), R.layout.contribute_leftimage,null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
        centerText= (TextView) View.inflate(getContext(),R.layout.system_centertext,null);
        rightText= (TextView) View.inflate(getContext(),R.layout.contribute_righttext,null);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                mSystemAdapter.notifyDataSetChanged();
            }
        });
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
