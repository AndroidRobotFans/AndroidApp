package com.one.duanone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.one.duanone.R;
import com.one.duanone.interfaces.OnItemClickListener;
import com.one.duanone.view.ArrowRefreshHeader;

import java.util.ArrayList;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/8/3  9:55.
 */
public class LRecyclerViewAdapter extends RecyclerView.Adapter<LRecyclerViewAdapter.MyViewHolder> {


    private static final int TYPE_REFRESH_HEADER = 10000;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER_VIEW = 10001;
    private static final int HEADER_INIT_INDEX = 10002;

    private boolean pullRefreshEnabled = true;
    private int mRefreshProgressStyle = -1;
    private ArrowRefreshHeader mRefreshHeader;

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();

    /**
     * RecyclerView使用的，真正的Adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mInnerAdapter;
    private Context mContext;
    private OnItemClickListener mOnItemClickLitener;

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemChanged(positionStart + getHeaderViewsCount() + 1, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart + getHeaderViewsCount() + 1, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            int headerViewsCountCount = getHeaderViewsCount();
            notifyItemRangeChanged(fromPosition + headerViewsCountCount + 1, toPosition);
        }
    };

    public LRecyclerViewAdapter(Context mContext, RecyclerView.Adapter<RecyclerView.ViewHolder> mInnerAdapter) {
        this.mContext = mContext;
        setRefreshHeader();
        setAdapter(mInnerAdapter);
    }

    public void setRefreshHeader() {
        if (pullRefreshEnabled) {
            ArrowRefreshHeader refreshHeader = new ArrowRefreshHeader(mContext);
            refreshHeader.setProgressStyle(mRefreshProgressStyle);
            mRefreshHeader = refreshHeader;
        }
    }

    public ArrowRefreshHeader getRefreshHeader() {
        return mRefreshHeader;
    }

    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        if (adapter != null) {
            if (!(adapter instanceof RecyclerView.Adapter))
                throw new RuntimeException("adapter 不是RecycleView.Adapter 的子类");
        }
        if (mInnerAdapter != null) {
            notifyItemRangeRemoved(getHeaderViewsCount(), mInnerAdapter.getItemCount());
            mInnerAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        this.mInnerAdapter = adapter;
        mInnerAdapter.registerAdapterDataObserver(mDataObserver);
        notifyItemRangeInserted(getHeaderViewsCount(), mInnerAdapter.getItemCount());
    }

    public RecyclerView.Adapter<RecyclerView.ViewHolder> getInnerAdapter() {
        return mInnerAdapter;
    }
    public void addHeaderView(View view){
        if (view == null){
            throw new RuntimeException("header is not");
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

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

        ImageView diggImg, buryImg, commentImg, shareImg;

        View item;

        public MyViewHolder(View itemView) {
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
}

