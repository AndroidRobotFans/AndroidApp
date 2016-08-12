package com.one.duanone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.bean.News;
import com.one.duanone.utils.GlideUtils;

import java.util.List;

/**
 * Created by jj on 2016/8/1.
 */
public class BlackListAdapter extends BaseAdapter {

    List<News.User> list;
    Context context;

    public BlackListAdapter(List<News.User> list, Context context) {
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
            convertView = View.inflate(context, R.layout.blacklist_adapter_item, null);
            holder.userImage= (ImageView) convertView.findViewById(R.id.black_image);
            holder.userName= (TextView) convertView.findViewById(R.id.black_name_textview);
            holder.removetv= (TextView) convertView.findViewById(R.id.black_remove);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();

        }
        holder.userName.setText(list.get(position).getName());
        holder.removetv.setText("移除.");
        GlideUtils.urlCircleImage(context,list.get(position).getAvatar_url(),holder.userImage);
        return convertView;
    }

    class ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView removetv;
    }
}
