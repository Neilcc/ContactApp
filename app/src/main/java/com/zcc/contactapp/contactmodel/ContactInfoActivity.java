package com.zcc.contactapp.contactmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zcc.contactapp.R;
import com.zcc.contactapp.contactmodel.adapters.AvatarLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.DetailLinearAdapter;
import com.zcc.contactapp.contactmodel.presenter.ContactPresenter;
import com.zcc.contactapp.contactmodel.presenter.IContactContractView;
import com.zcc.contactapp.contactmodel.viewmodels.AvatarDataBean;
import com.zcc.contactapp.contactmodel.viewmodels.DetailDataBean;

import java.util.List;

public class ContactInfoActivity extends AppCompatActivity implements IContactContractView {

    private ProgressBar mContactActivityProgress;
    private RecyclerView mAvatarRecyclerView;
    private RecyclerView mDetailRecyclerView;
    private AvatarLinearAdapter mAvatarAdapter;
    private DetailLinearAdapter mDetailAdapter;

    private ContactPresenter mContactPresenter;
    private ContactRecyclerViewUtil ContactRecyclerViewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initData();
    }

    private void initData() {
        mContactPresenter = new ContactPresenter(this);
        mContactPresenter.loadContactData();
        mContactActivityProgress.setVisibility(View.VISIBLE);
    }

    private void initWidget() {
        mContactActivityProgress = findViewById(R.id.pb_contact_progress);
        mAvatarRecyclerView = findViewById(R.id.rv_avatars);
        mDetailRecyclerView = findViewById(R.id.rv_desc);
        mAvatarAdapter = new AvatarLinearAdapter();
        mDetailAdapter = new DetailLinearAdapter();
        ContactRecyclerViewUtil = new ContactRecyclerViewUtil(mAvatarRecyclerView, mDetailRecyclerView);
        ContactRecyclerViewUtil.init(mAvatarAdapter, mDetailAdapter);
        ContactRecyclerViewUtil.bindScroll();
    }

    @Override
    public void updateContactDetail(List<DetailDataBean> detailDataBeans) {
        mDetailAdapter.setDataList(detailDataBeans);
        mContactActivityProgress.setVisibility(View.GONE);
    }

    @Override
    public void updateContactAvatar(List<AvatarDataBean> avatarDataBeans) {
        mAvatarAdapter.setDataList(avatarDataBeans);
        mContactActivityProgress.setVisibility(View.GONE);
    }

    @Override
    public void onError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
