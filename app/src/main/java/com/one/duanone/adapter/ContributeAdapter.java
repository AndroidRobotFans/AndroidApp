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
public class ContributeAdapter extends BaseAdapter {

    List<News.User> list;
    Context context;

    public ContributeAdapter(List<News.User> list, Context context) {
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
            convertView = View.inflate(context, R.layout.contribute_adapter_item, null);
            holder.userImage = (ImageView) convertView.findViewById(R.id.contribute_image);
            holder.userName = (TextView) convertView.findViewById(R.id.contribute_name_textview);
            holder.comment = (TextView) convertView.findViewById(R.id.contribute_comment);
            holder.time = (TextView) convertView.findViewById(R.id.contribute_time_textview);
            holder.praise = (LinearLayout) convertView.findViewById(R.id.contribute_praise);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.userName.setText(list.get(position).getName());
//        holder.time.setText(list.get(position).getTime()+"");
        holder.comment.setText(list.get(position).getPersonal_comment());
        GlideUtils.urlCircleImage(context, list.get(position).getAvatar_url(), holder.userImage);
        return convertView;
    }

    class ViewHolder {
        ImageView userImage;
        TextView userName, time;
        TextView comment;
        LinearLayout praise;
    }
}
