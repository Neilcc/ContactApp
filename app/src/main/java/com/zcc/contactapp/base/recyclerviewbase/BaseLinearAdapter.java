package com.zcc.contactapp.base.recyclerviewbase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseLinearAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {

    protected List<T> mDataList;

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.onBind(mDataList.get(i));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

}
