package com.one.duanone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;

import java.util.List;


/**
 * This document is created in 2016/7/26
 * Author: Chengquan
 */
public class MyFindAdapter extends BaseAdapter {

    List<String> list;
    Context context;


    public MyFindAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mHolder = new ViewHolder();
        if (view == null){
            view = View.inflate(context, R.layout.item_fragment_find, null);
            mHolder.thumbnail = (ImageView) view.findViewById(R.id.item_fragment_find_image_thumbnail);
            mHolder.special = (TextView) view.findViewById(R.id.item_fragment_find_textview_special);
            mHolder.describe = (TextView) view.findViewById(R.id.item_fragment_find_textview_describe);
            mHolder.info = (TextView) view.findViewById(R.id.item_fragment_find_textview_info);
            mHolder.subscriber = (Button) view.findViewById(R.id.item_fragment_find_button_subscriber);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.thumbnail.setImageResource(R.drawable.ic_tab_msg_pressed);
        mHolder.special.setText(list.get(i).toString());
        mHolder.describe.setText("这里是描述");
        mHolder.info.setText("这里是信息");
        mHolder.subscriber.setText("订阅");
        return view;
    }

    class ViewHolder {
        ImageView thumbnail;    //缩略图
        TextView special;   //专题
        TextView describe;  //描述
        TextView info;  //信息
        Button subscriber;  //订阅
    }
}
