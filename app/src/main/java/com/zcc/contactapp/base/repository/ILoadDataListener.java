package com.zcc.contactapp.base.repository;

public interface ILoadDataListener<T> {

    public void onSuccess(T data);

    public void onFailed(int code, String msg);
}
