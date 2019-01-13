package com.zcc.contactapp.contactmodel.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zcc.contactapp.R;
import com.zcc.contactapp.base.recyclerviewbase.BaseLinearAdapter;
import com.zcc.contactapp.base.recyclerviewbase.BaseViewHolder;
import com.zcc.contactapp.contactmodel.viewmodels.DetailDataBean;

public class DetailLinearAdapter extends BaseLinearAdapter<DetailDataBean, DetailLinearAdapter.DetailViewHolder> {

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item_desc, viewGroup, false);
        return new DetailViewHolder(root);
    }


    public static class DetailViewHolder extends BaseViewHolder<DetailDataBean> {

        private TextView mFirstName;
        private TextView mLastName;
        private TextView mTitle;
        private TextView mDetail;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            mFirstName = itemView.findViewById(R.id.tv_first_name);
            mLastName = itemView.findViewById(R.id.tv_last_name);
            mTitle = itemView.findViewById(R.id.tv_title);
            mDetail = itemView.findViewById(R.id.tv_detail);
        }

        @Override
        public void onBind(DetailDataBean data) {
            mFirstName.setText(data.getFirstName());
            mLastName.setText(data.getLastName());
            mTitle.setText(data.getTitle());
            mDetail.setText(data.getDetail());
        }
    }
}
