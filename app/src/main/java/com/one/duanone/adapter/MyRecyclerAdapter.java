package com.one.duanone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.one.duanone.R;
import com.one.duanone.bean.News;
import com.one.duanone.utils.GlideUtils;
import com.one.duanone.utils.Utils;

import java.util.List;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/30  19:26.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private static final String TAG = MyRecyclerAdapter.class.getSimpleName();
    private List<News> list;
    private Context context;
    //圆角图片的圆角半径
    private static final int ROUND = 5;

    public MyRecyclerAdapter(List<News> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //获取itemType的值, 判断其消息类型
        int itemType = getItemViewType(position);
        News.User user = null;
        News.Group group = list.get(position).getGroup();
        News.LiveNew liveNew = list.get(position).getLiveNew();

        //是否是直播
        boolean isLiveing = list.get(position).isLive();
        String name = null;
        String iconUrl = null;

        String content;
        String imageUrl = null;
        int diggCount = -1;
        int buryCount = -1;
        int commentCount = -1;
        int shareCount = -1;

        int playCount = -1;
        int duration = -1;

        //不是直播
        if (!isLiveing) {

            content = group.getContent();
            user = group.getUser();
            //设置用户信息

            holder.name.setText(name);
            GlideUtils.urlCircleImage(context, iconUrl, holder.image);
            name = user.getName();
            iconUrl = user.getAvatar_url();
            diggCount = group.getDigg_count();
            buryCount = group.getBury_count();
            commentCount = group.getComment_count();
            shareCount = group.getShare_count();
            News.ImageNew imageNew = group.getImageNew();
            if (imageNew != null) {
                imageUrl = imageNew.getImgUrl();
            }
            //视频类型的
            if (itemType == 3) {
                playCount = group.getOriginVideo().getPlay_count();
                duration = (int) group.getOriginVideo().getDuration();
                imageUrl = group.getOriginVideo().getMedium_cover_url();
                holder.vdieo_layout_bg.setVisibility(View.VISIBLE);
                holder.imagePlay.setVisibility(View.VISIBLE);
                setTextCount(playCount, holder.vdieo_play_count);
                setTextCount(duration, holder.vdieo_duration);
            } else {
                holder.vdieo_layout_bg.setVisibility(View.GONE);
                holder.imagePlay.setVisibility(View.GONE);
            }

            setTextView(content, holder.content);
            setTextView(name, holder.name);
            setUserIcon(iconUrl, holder.icon);
            setImage(holder.image, imageUrl);
            Log.i(TAG, "onBindViewHolder: " + imageUrl);

            setTextCount(buryCount, holder.bury_count);
            setTextCount(diggCount, holder.digg_count);
            setTextCount(shareCount, holder.share_count);
            setTextCount(commentCount, holder.comment_count);
        }
    }

    /**
     * 设置图片
     *
     * @param iv
     * @param url
     */
    private void setImage(ImageView iv, String url) {
        GlideUtils.urlImage(context, url, iv);
    }

    private void setRoundImage(ImageView iv, String url) {
        GlideUtils.urlRoundImage(context, url, ROUND, iv);
    }

    /**
     * 设置用户头像
     *
     * @param iv
     * @param url
     */
    private void setUserIcon(String url, ImageView iv) {
        GlideUtils.urlCircleImage(context, url, iv);
    }


    /**
     * 设置TextView的显示时间
     *
     * @param count
     * @param tv
     */
    private void setTextCount(int count, TextView tv) {
        if (count == -1) {
            return;
        }
        String str = Utils.formatCount(count);
        setTextView(str, tv);
    }

    /***
     * 设置TextView的值
     *
     * @param str
     * @param tv
     */
    private void setTextView(String str, TextView tv) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        tv.setText(str);
    }

    /**
     * 设置文字颜色和文字,颜色为 暗红色
     *
     * @param str
     * @param tv
     */
    private void setTextViewColor(String str, TextView tv) {
        tv.setTextColor(Color.rgb(223, 111, 95));
        setTextView(str, tv);
    }

    @Override
    public int getItemViewType(int position) {
        /**
         * 消息类型 0 - 4
         * 0: 纯文本
         * 1: 只有图片
         * 2: git 图
         * 3: 视频
         * 4: 图片
         * 0-4都是json里面自带的,
         * 22: 当是22时,说明没有Group, 有Live, 是直播的类型的
         */
        News.Group group = list.get(position).getGroup();
        if (group != null) {

            return group.getMedia_type();
        }
        return 22;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;//名字
        TextView content;//文本内容
        ImageView icon;//用户图像
        ImageView image;//用户发的图片
        ImageView imagePlay;//显示类型的图片,播放按钮
        TextView digg_count;//赞次数
        TextView share_count;//分享次数
        TextView comment_count;//评论次数
        TextView bury_count;//踩次数
        TextView vdieo_duration;//视频长度
        TextView vdieo_play_count;//视频播放次数
        RelativeLayout vdieo_layout_bg;//视频显示详情的布局, 默认是gone的

        View item;

        public ViewHolder(View itemView) {
            super(itemView);
            this.item = itemView;

            name = $(R.id.name_item_recycler);
            icon = $(R.id.icon_item_recycler);
            content = $(R.id.content_item_recycler);
            image = $(R.id.image_item_recycler);
            imagePlay = $(R.id.play_item_recycler);
            digg_count = $(R.id.digg_count_item_recycler);
            bury_count = $(R.id.bury_item_recycler);
            share_count = $(R.id.share_item_recycler);
            comment_count = $(R.id.comment_item_recycler);
            vdieo_duration = $(R.id.video_duration_item_recycler);
            vdieo_layout_bg = $(R.id.bottom_rl_item_recycler);
            vdieo_play_count = $(R.id.play_item_count_recycler);

        }

        /**
         * findViewById
         *
         * @param resID
         * @param <T>
         * @return
         */
        private <T extends View> T $(int resID) {
            return (T) item.findViewById(resID);
        }
    }

    /**
     * 设置list数据, 更新集合
     *
     * @param list
     */
    public void setListData(List<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
