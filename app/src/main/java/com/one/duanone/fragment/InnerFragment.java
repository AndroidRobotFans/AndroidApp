package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/24  20:33.
 */
public class InnerFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private String url;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycel_view, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_inner);
        url = getArguments().getString("url");

        return view;
    }


}
