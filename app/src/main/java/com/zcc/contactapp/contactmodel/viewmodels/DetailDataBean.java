package com.zcc.contactapp.contactmodel.viewmodels;

import android.text.TextUtils;

public class DetailDataBean {
    private String mFirstName;
    private String mLastName;
    private String mTitle;
    private String mDetail;

    public DetailDataBean() {
    }

    public DetailDataBean(String mFirstName, String mLastName, String mTitle, String mDetail) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mTitle = mTitle;
        this.mDetail = mDetail;
    }

    public void setDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDetail() {
        return TextUtils.isEmpty(mDetail) ? "" : mDetail;
    }

    public String getFirstName() {
        return TextUtils.isEmpty(mFirstName) ? "" : mFirstName;
    }

    public String getLastName() {
        return TextUtils.isEmpty(mLastName) ? "" : mLastName;
    }

    public String getTitle() {
        return TextUtils.isEmpty(mTitle) ? "" : mTitle;
    }
}
