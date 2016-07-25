package com.one.duanone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.one.duanone.R;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  22:07.
 */
public class ShowInnerFragment extends BaseFragment {


    private View view;
    private ListView listView;

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inner_list_view, null);
        listView = (ListView) view.findViewById(R.id.inner_listView);
        return view;
    }

    @Override
    public void initFragmentData() {

    }

}
