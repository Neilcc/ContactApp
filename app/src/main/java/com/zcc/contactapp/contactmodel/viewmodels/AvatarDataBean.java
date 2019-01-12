package com.zcc.contactapp.contactmodel.viewmodels;

import java.net.URI;

public class AvatarDataBean {
    private URI mAvatarUri;
    private boolean isSelected;

    public void setAvatarUri(URI mAvatarUri) {
        this.mAvatarUri = mAvatarUri;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public URI getAvatarUri() {
        return mAvatarUri;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
