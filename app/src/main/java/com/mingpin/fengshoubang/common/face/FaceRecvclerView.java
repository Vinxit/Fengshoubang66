package com.mingpin.fengshoubang.common.face;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.common.emoji.Emojicon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class FaceRecvclerView extends RecyclerView{
    private FaceAdapter mAdapter;
    private OnFaceClickListener mListener;
    private final List<Emojicon> mData = new ArrayList<>();

    public FaceRecvclerView(Context context, OnFaceClickListener listener) {
        super(context);
        this.mListener = listener;

        // 自动计算显示的列数
/*        int autoCount = (int) (TDevice.getScreenWidth() / TDevice.dipToPx(getResources(), 48));*/
        int autoCount = 10;
        setLayoutManager(new GridLayoutManager(context, autoCount));
        setAdapter(mAdapter = new FaceAdapter());
    }

    public void setData(List<Emojicon> icons) {
        mData.addAll(icons);
        mAdapter.notifyDataSetChanged();
    }

    private void onFaceClick(FaceViewHolder holder) {
        OnFaceClickListener listener = mListener;
        if (listener != null && holder.getTagData() != -1) {
            Emojicon emojicon = mData.get(holder.getTagData());
            listener.onFaceClick(emojicon);
        }
    }

    private class FaceAdapter extends RecyclerView.Adapter<FaceViewHolder> {

        @Override
        public FaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View root = inflater.inflate(R.layout.lay_face_icon, parent, false);
            final FaceViewHolder holder = new FaceViewHolder(root);
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFaceClick(holder);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(FaceViewHolder holder, int position) {
            holder.bindData(mData.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private static class FaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;

        FaceViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.iv_face);
        }

        void bindData(Emojicon emojicon, int position) {
            itemView.setTag(1,position);
            Glide.with(itemView.getContext())
                    .load(emojicon.getResId())
                    .into(mImage);
        }

        int getTagData() {
            Object obj = itemView.getTag(1);
            if (obj != null && obj instanceof Integer) {
                return (int) obj;
            }
            return -1;
        }
    }

    public interface OnFaceClickListener {
        void onFaceClick(Emojicon v);
    }
}
