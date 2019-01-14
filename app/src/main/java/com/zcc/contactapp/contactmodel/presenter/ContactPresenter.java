package com.zcc.contactapp.contactmodel.presenter;

import com.zcc.contactapp.base.repository.ILoadDataListener;
import com.zcc.contactapp.contactmodel.repository.ContactDataBean;
import com.zcc.contactapp.contactmodel.repository.ContactDataRepository;
import com.zcc.contactapp.contactmodel.viewmodels.AvatarDataBean;
import com.zcc.contactapp.contactmodel.viewmodels.DetailDataBean;
import com.zcc.contactapp.utils.UrlUtil;

import java.util.ArrayList;
import java.util.List;

public class ContactPresenter {

    private ContactDataRepository mDataRepository;
    private IContactContractView mContactContractView;

    public ContactPresenter(IContactContractView contactContractView) {
        mDataRepository = new ContactDataRepository();
        mContactContractView = contactContractView;
    }

    public void loadContactData() {
        mDataRepository.loadDataAsync(new ILoadDataListener<List<ContactDataBean>>() {
            @Override
            public void onSuccess(List<ContactDataBean> contactDataBeans) {
                List<AvatarDataBean> avatarDataBeans = new ArrayList<>();
                List<DetailDataBean> detailDataBeans = new ArrayList<>();
                for (ContactDataBean contactDataBean : contactDataBeans) {
                    avatarDataBeans.add(new AvatarDataBean(UrlUtil
                            .generateAssetUrl(contactDataBean.getAvatar_filename(),
                                    ContactDataRepository.IMAGE_FOLDER)));
                    detailDataBeans.add(new DetailDataBean(contactDataBean.getFirst_name(),
                            contactDataBean.getLast_name(),
                            contactDataBean.getTitle(),
                            contactDataBean.getIntroduction()));
                }
                if (avatarDataBeans.size() > 0) {
                    avatarDataBeans.get(0).setSelected(true);
                }
                mContactContractView.updateContactAvatar(avatarDataBeans);
                mContactContractView.updateContactDetail(detailDataBeans);
            }

            @Override
            public void onFailed(int code, String msg) {

            }
        });

    }

}
