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
import com.one.duanone.adapter.ContributeAdapter;
import com.one.duanone.bean.News;

import java.util.ArrayList;
import java.util.List;

public class MessageCtributeFragment extends NotableFragment {

    private static final String TAG=BlackListFragment.class.getSimpleName();

    private ImageView leftImage;
    private TextView centerText;
    private TextView rightText;
    private View view;

    List<News.User> list=new ArrayList<>();
    ListView listView;
    ContributeAdapter mContributeAdapter;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getContext(),R.layout.message_contribute_fragment,null);
        initView();
        return view;
    }

    private void initView() {
        mContributeAdapter=new ContributeAdapter(list,context);
        listView= (ListView) view.findViewById(R.id.conribute_listview);
        for (int i = 0; i <10 ; i++) {
            News.User user=new News.User();
            user.setName("heiweigou");
            list.add(user);
        }
        listView.setAdapter(mContributeAdapter);
        mContributeAdapter.notifyDataSetChanged();
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
        centerText= (TextView) View.inflate(getContext(),R.layout.contribute_centertext,null);
        rightText= (TextView) View.inflate(getContext(),R.layout.contribute_righttext,null);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                Log.i(TAG, "onClick: -=-=-=--=-----=-----=---=-=-=-=");
                mContributeAdapter.notifyDataSetChanged();
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
