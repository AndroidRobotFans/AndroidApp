package com.one.duanone.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.duanone.R;
import com.one.duanone.adapter.MyRecyclerAdapter;
import com.one.duanone.bean.News;
import com.one.duanone.utils.JsonUtils;
import com.one.duanone.utils.NetUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/24  20:33.
 */
public class InnerFragment extends BaseFragment {

    private static final String TAG = InnerFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private String url;
    private MyRecyclerAdapter recyclerAdapter;
    private List<News> listData;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.i(TAG, "onResponse: 请求失败");
            e.printStackTrace();
            resetPullRefresh();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String json = response.body().string();
            Log.i(TAG, "onResponse===: JSON请求成功");

            listData.addAll(0, JsonUtils.getJsonNews(json));
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerAdapter.setListData(listData);
                }
            });
            resetPullRefresh();
        }
    };
    private SwipeRefreshLayout.OnRefreshListener onrefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //请求数据
            getData(RequestType.REFRESH);
        }
    };


    private void resetPullRefresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }

    @Override
    public View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycel_view, null);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_inner);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_inner);
        swipeRefreshLayout.setOnRefreshListener(onrefreshListener);

        url = getArguments().getString("url");

        listData = new ArrayList<>();
        recyclerAdapter = new MyRecyclerAdapter(listData, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        getData(RequestType.REFRESH);
        return view;
    }

    /**
     * 根据不同的type值去请求不同的数据, 并加载
     *
     * @param type
     */
    public void getData(RequestType type) {
        switch (type) {
            case REFRESH:
                NetUtils.getUrlStr(url, callback);
                break;
            case LOAD:
                //请求数据库, 并加载到最后
                break;
        }

    }

    /**
     * 枚举类, 标记请求方式
     */
    public enum RequestType {
        REFRESH, LOAD
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        resetPullRefresh();
    }
}
