package com.mingpin.fengshoubang.common.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.mingpin.fengshoubang.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ShareDialog extends BottomSheetDialog{
    private Context context;
    public ShareDialog(@NonNull Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View contentView = inflater.inflate(R.layout.dialog_share_main,null,false);
        RecyclerView shareRecycle = (RecyclerView)contentView.findViewById(R.id.rv_share_recycler);

    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

/*
    private class ShareActionAdapter extends RecyclerView.Adapter<ShareItem>{


        @Override
        public void onBindViewHolder(ShareItem holder, int position, List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public ShareItem onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ShareItem holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
*/

    private static class ShareItem{
        int iconId;
        int nameId;

        ShareItem(int iconId, int nameId) {
            this.iconId = iconId;
            this.nameId = nameId;
        }
    }

}
