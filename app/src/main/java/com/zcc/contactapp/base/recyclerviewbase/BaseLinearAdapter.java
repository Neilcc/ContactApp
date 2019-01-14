package com.zcc.contactapp.base.recyclerviewbase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseLinearAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {
    protected List<T> mDataList;
    protected ItemClickListener mItemClickListener;

    @Override
    public void onBindViewHolder(@NonNull final VH vh, final int i) {
        vh.onBind(mDataList.get(i));
        if (mItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(view, vh.getAdapterPosition());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setDataList(List<T> dataList) {
        if(dataList == null) return;
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface ItemClickListener {
        public void onItemClick(View v, int pos);
    }

}
