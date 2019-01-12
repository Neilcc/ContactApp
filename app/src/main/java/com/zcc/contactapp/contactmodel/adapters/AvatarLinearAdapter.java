package com.zcc.contactapp.contactmodel.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zcc.contactapp.R;
import com.zcc.contactapp.base.recyclerviewbase.BaseLinearAdapter;
import com.zcc.contactapp.base.recyclerviewbase.BaseViewHolder;
import com.zcc.contactapp.contactmodel.viewmodels.AvatarDataBean;

public class AvatarLinearAdapter extends BaseLinearAdapter<AvatarDataBean, AvatarLinearAdapter.AvatarViewHolder> {

    @NonNull
    @Override
    public AvatarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item_avartars, viewGroup, false);
        return new AvatarViewHolder(root);
    }

    public static class AvatarViewHolder extends BaseViewHolder<AvatarDataBean> {

        private ImageView mAvatarImageView;

        public AvatarViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatarImageView = itemView.findViewById(R.id.iv_avatar);
        }

        @Override
        public void onBind(AvatarDataBean data) {
            mAvatarImageView.setImageURI(null);
            mAvatarImageView.setSelected(true);
        }

    }
}
