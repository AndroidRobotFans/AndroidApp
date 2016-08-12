package com.one.duanone.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.activity.CommentAcitivyt;
import com.one.duanone.activity.LiveActivity;
import com.one.duanone.bean.News;
import com.one.duanone.manager.MediaManager;
import com.one.duanone.utils.GlideUtils;
import com.one.duanone.utils.ToastUtils;
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
        View view = null;
        if (viewType != 22) {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_item, null);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.live_tiem_recycle, null);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //获取itemType的值, 判断其消息类型
        int itemType = getItemViewType(position);
        final News.User user = null;

        //是否是直播
        boolean isLiveing = list.get(position).isLive();
        String name = null;
        String iconUrl = null;

        //不是直播
        if (!isLiveing) {

            News.Group group = null;
            String content;
            String imageUrl = null;
            int diggCount = -1;
            int buryCount = -1;
            int commentCount = -1;
            int shareCount = -1;

            int playCount = -1;
            int duration = -1;

            group = list.get(position).getGroup();
            content = group.getContent();
            //设置用户信息
            setUserContent(holder, group.getUser());
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
                setTextDuration(duration, holder.vdieo_duration);
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

            initImage(group, holder);

        } else {
            //直播类型
            News.LiveNew liveNew = null;
            liveNew = list.get(position).getLiveNew();
            setUserContent(holder, liveNew.getUser());//设置用户信息
            setImage(holder.image, liveNew.getImgUrl());//设置图片
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.image.getLayoutParams();
            lp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            lp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            holder.image.setLayoutParams(lp);

            setTextCount(liveNew.getUser_count(), holder.userCount); //观看人数
            String city = liveNew.getCity();
            city = TextUtils.isEmpty(city) ? "未知星球" : city;
            setTextView(city, holder.city);
        }

        //点击用户头像,跳转用户信息
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News.Group group = list.get(position).getGroup();
                Intent intent = new Intent(context, CommentAcitivyt.class);
                int groupId = group.getGroup_id();
                int userId = group.getUser().getUser_id();
                intent.putExtra("groupId", groupId);
                intent.putExtra("userId", userId);
                context.startActivity(intent);
            }
        });
        if (!isLiveing) {
            //非直播
            if (holder.buryImg != null)
                holder.buryImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        News.Group group = list.get(position).getGroup();
                        if (isNotLick(group)) {
                            int count = group.getBury_count();
                            count++;
                            ToastUtils.showMoveToast(holder.bury_count, count + "", context);
                            group.setBury_count(count);
                            setTextCount(count, holder.bury_count);
                            notifyDataSetChanged();
                            group.setBury(true);
                            holder.buryImg.setImageResource(R.drawable.ic_bury_pressed);
                        }
                    }
                });
            if (holder.diggImg != null)
                holder.diggImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        News.Group group = list.get(position).getGroup();
                        if (isNotLick(group)) {
                            int count = group.getDigg_count();
                            count++;
                            setTextCount(count, holder.digg_count);
                            group.setDigg_count(count);
                            notifyDataSetChanged();
                            group.setDigg(true);
                            holder.diggImg.setImageResource(R.drawable.ss_comment_digg_highlight);
                        }
                    }
                });
            if (holder.commentImg != null)
                holder.commentImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        News.Group group = list.get(position).getGroup();
                        Intent intent = new Intent(context, CommentAcitivyt.class);
                        int groupId = group.getGroup_id();
                        int userId = group.getUser().getUser_id();
                        intent.putExtra("groupId", groupId);
                        intent.putExtra("userId", userId);
                        context.startActivity(intent);
                    }
                });
            if (holder.shareImg != null)
                holder.shareImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("随机分享完成", context);
                    }
                });
            //播放按钮
            if (itemType == 3) {
                final ProgressDialog pd = new ProgressDialog(context);
                final String url = list.get(position).getGroup().getOriginVideo().getOrigin_video_url();
                final MediaManager.PlayListener listener = new MediaManager.PlayListener() {
                    @Override
                    public void notPlay() {
                        holder.surfaceView.setVisibility(View.GONE);
                        holder.vdieo_layout_bg.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void prepared() {
                        pd.dismiss();
                        holder.vdieo_layout_bg.setVisibility(View.GONE);
                    }

                    @Override
                    public void playing(int current, int duration) {

                    }
                };

                holder.imagePlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pd.show();
                        holder.surfaceView.setVisibility(View.VISIBLE);
                        MediaManager.getInstance(holder.surfaceView).paly(url, listener);
                    }
                });
            }
        } else {
            //直播
            if (holder.imagePlay != null)
                holder.imagePlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        News.LiveNew liveNew = list.get(position).getLiveNew();
                        int groupId = liveNew.getId();
                        String url = liveNew.getLiveUrl();
                        String icon = liveNew.getUser().getAvatar_url();
                        String name = liveNew.getUser().getName();
                        int id = liveNew.getUser().getUser_id();

                        Intent intent = new Intent(context, LiveActivity.class);
                        intent.putExtra("groupId", groupId);
                        intent.putExtra("url", url);
                        intent.putExtra("icon", icon);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);

                        context.startActivity(intent);
                    }
                });
        }
    }

    private void initImage(News.Group group, ViewHolder holder) {
        boolean bury = group.isBury();
        boolean digg = group.isDigg();
        int bugyId = 0;
        int diggId = 0;
        if (bury) {
            bugyId = R.drawable.ic_bury_pressed;
        } else {
            bugyId = R.drawable.ic_bury_normal;
        }
        if (digg) {
            diggId = R.drawable.ss_comment_digg_highlight;
        } else {
            diggId = R.drawable.ss_comment_digg_normal;
        }
        holder.buryImg.setImageResource(bugyId);
        holder.diggImg.setImageResource(diggId);
    }

    /**
     * 是否有过操作
     *
     * @param group
     * @return
     */
    private boolean isNotLick(News.Group group) {
        boolean isBury = group.isBury();
        boolean isDigg = group.isDigg();
        if (isBury) {
            ToastUtils.showToast(context.getString(R.string.youHaveIsBury), context);
        }
        if (isDigg) {
            ToastUtils.showToast(context.getString(R.string.youHaveIsDigg), context);
        }
        return !isBury && !isDigg;
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
     * @param time
     * @param tv
     */
    private void setTextDuration(int time, TextView tv) {
        String str = Utils.formatDurationS(time);
        setTextView(str, tv);
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
     * 设置用户信息
     *
     * @param holder
     * @param user
     */
    private void setUserContent(ViewHolder holder, News.User user) {
        holder.name.setText(user.getName());
        setUserIcon(user.getAvatar_url(), holder.icon);
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
        if (!list.get(position).isLive()) {
            return list.get(position).getGroup().getMedia_type();
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
        TextView userCount;
        TextView city;
        SurfaceView surfaceView;


        ImageView diggImg, buryImg, commentImg, shareImg;

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
            userCount = $(R.id.user_count_item_recycler);
            city = $(R.id.city_item_recycle);

            surfaceView = $(R.id.surface_item_inner);

            diggImg = $(R.id.digg_img_item_recycle);
            buryImg = $(R.id.bury_img_item_recycle);
            commentImg = $(R.id.comment_img_item_recycle);
            shareImg = $(R.id.share_img_item_recycle);
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
