package com.one.duanone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.bean.News;
import com.one.duanone.utils.GlideUtils;

import java.util.List;

/**
 * Created by jj on 2016/8/1.
 */
public class SystemAdapter extends BaseAdapter {

    List<News.User> list;
    Context context;

    public SystemAdapter(List<News.User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.system_adapter_item, null);
            holder.inform = (TextView) convertView.findViewById(R.id.system_inform_textview);
            holder.comment = (TextView) convertView.findViewById(R.id.system_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.inform.setText(list.get(position).getInform());
        holder.comment.setText(list.get(position).getPersonal_comment());
        return convertView;
    }

    class ViewHolder {
        TextView comment, inform;
    }
}
