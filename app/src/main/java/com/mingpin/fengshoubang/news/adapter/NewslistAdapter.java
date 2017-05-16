package com.mingpin.fengshoubang.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.common.utils.ImageLoaderUtils;
import com.mingpin.fengshoubang.config.Urls;
import com.mingpin.fengshoubang.news.bean.NewsListItem;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class NewslistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "NewslistAdapter";

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private boolean mShowFooter = true;
    private Context mContext;
    private List<NewsListItem> mData;

    private OnItemClickListener mOnItemClickListener;

    public NewslistAdapter(Context context) {
        this.mContext = context;
    }
    public void setmDate(List<NewsListItem> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if(!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news,parent,false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            NewsListItem news = mData.get(position);
            if(news == null) {
                return;
            }
            ((ItemViewHolder) holder).mTitle.setText(news.getNewstitle());
            ((ItemViewHolder) holder).mHits.setText(news.getHits());
            ((ItemViewHolder) holder).mC_num.setText(news.getC_num()+"");
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).mNewsImg, Urls.IMG_URL+news.getImg());
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter?1:0;
        if(mData == null) {
            return begin;
        }
        return mData.size() + begin;
    }
    public NewsListItem getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public TextView mHits;
        public TextView mC_num;
        public ImageView mNewsImg;

        public ItemViewHolder(View v) {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mHits = (TextView) v.findViewById(R.id.tvHits);
            mC_num = (TextView) v.findViewById(R.id.tvC_num);
            mNewsImg = (ImageView) v.findViewById(R.id.ivNews);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view,this.getPosition());
            }
        }
    }
}