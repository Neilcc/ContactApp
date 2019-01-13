package com.zcc.contactapp.contactmodel.presenter;

import com.zcc.contactapp.contactmodel.viewmodels.AvatarDataBean;
import com.zcc.contactapp.contactmodel.viewmodels.DetailDataBean;

import java.util.List;

public interface IContactContractView {

    public void updateContactDetail(List<DetailDataBean> detailDataBeans);

    public void updateContactAvatar(List<AvatarDataBean> avatarDataBeans);

}
