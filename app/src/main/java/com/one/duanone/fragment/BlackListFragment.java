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
import com.one.duanone.adapter.BlackListAdapter;
import com.one.duanone.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jj on 2016/7/30.
 */
public class BlackListFragment extends NotableFragment {
    private static final String TAG = BlackListFragment.class.getSimpleName();

    private ImageView leftImage;
    private TextView centerText;
    private TextView rightText;
    private View view;
    private ListView listView;
    private List<News.User> list;
    private BlackListAdapter mAdapter;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_blacklist, null);
        initView();
        return view;
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.attention_listview);
        list = new ArrayList<>();
        mAdapter = new BlackListAdapter(list, getContext());
        for (int i = 0; i < 10; i++) {
            News.User user=new News.User();
            user.setName("一一");
            list.add(user);
        }
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initFragmentData() {

        leftImage = (ImageView) View.inflate(getContext(), R.layout.blacklist_left_image, null);
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
        centerText = (TextView) View.inflate(getContext(), R.layout.blacklist_center_text, null);
        rightText = (TextView) View.inflate(getContext(), R.layout.blacklist_right_text, null);
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
