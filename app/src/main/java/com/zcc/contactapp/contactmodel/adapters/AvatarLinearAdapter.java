package com.zcc.contactapp.contactmodel.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zcc.contactapp.R;
import com.zcc.contactapp.base.recyclerviewbase.BaseLinearAdapter;
import com.zcc.contactapp.base.recyclerviewbase.BaseViewHolder;
import com.zcc.contactapp.contactmodel.viewmodels.AvatarDataBean;

import java.util.List;

public class AvatarLinearAdapter extends BaseLinearAdapter<AvatarDataBean, AvatarLinearAdapter.AvatarViewHolder> {
    private int mLastSelectedIndex = 0;

    @Override
    public void setDataList(List<AvatarDataBean> dataList) {
        super.setDataList(dataList);
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).isSelected()) {
                mLastSelectedIndex = i;
                break;
            }
        }
    }

    @NonNull
    @Override
    public AvatarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item_avartars, viewGroup, false);
        return new AvatarViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarViewHolder avatarViewHolder, int i) {
        super.onBindViewHolder(avatarViewHolder, i);
    }

    public void updateSelected(int index) {
        int lastPos = mLastSelectedIndex;
        mDataList.get(lastPos).setSelected(false);
        mDataList.get(index).setSelected(true);
        mLastSelectedIndex = index;
        notifyItemChanged(lastPos);
        notifyItemChanged(index);
    }

    public static class AvatarViewHolder extends BaseViewHolder<AvatarDataBean> {
        private ImageView mAvatarImageView;

        public AvatarViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatarImageView = itemView.findViewById(R.id.iv_avatar);
        }

        @Override
        public void onBind(AvatarDataBean data) {
            mAvatarImageView.setSelected(data.isSelected());
            Picasso.get().load(data.getAvatarUri()).into(mAvatarImageView);
        }
    }
}
