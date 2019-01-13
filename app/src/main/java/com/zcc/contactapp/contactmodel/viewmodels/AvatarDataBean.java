package com.zcc.contactapp.contactmodel.viewmodels;


public class AvatarDataBean {
    private String mAvatarUri;
    private boolean isSelected;

    public AvatarDataBean() {
    }

    public AvatarDataBean(String mAvatarUri) {
        this.mAvatarUri = mAvatarUri;
    }

    public void setAvatarUri(String mAvatarUri) {
        this.mAvatarUri = mAvatarUri;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAvatarUri() {
        return mAvatarUri;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
