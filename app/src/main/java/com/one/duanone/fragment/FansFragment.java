package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.one.duanone.R;
import com.one.duanone.adapter.AttentionAdapter;
import com.one.duanone.bean.News;

import java.util.ArrayList;
import java.util.List;

public class FansFragment extends Fragment {

    private View view;
    List<News.User> list=new ArrayList<>();
    ListView listView;
    AttentionAdapter mAttentionAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view=inflater.inflate(R.layout.activity_blacklist,container,false);
        initView();
        return view;
    }

    private void initView() {
        list.clear();
        listView= (ListView) view.findViewById(R.id.attention_listview);
        mAttentionAdapter=new AttentionAdapter(list,getContext());
        for (int i = 0; i <10; i++) {
            News.User user=new News.User();
            user.setName("lalala");
            list.add(user);
        }
        listView.setAdapter(mAttentionAdapter);
        mAttentionAdapter.notifyDataSetChanged();
    }


}
