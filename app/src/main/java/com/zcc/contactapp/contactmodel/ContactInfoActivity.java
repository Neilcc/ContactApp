package com.zcc.contactapp.contactmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.zcc.contactapp.R;
import com.zcc.contactapp.contactmodel.adapters.AvatarLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.DetailLinearAdapter;
import com.zcc.contactapp.contactmodel.presenter.ContactPresenter;
import com.zcc.contactapp.contactmodel.presenter.IContactContractView;
import com.zcc.contactapp.contactmodel.viewmodels.AvatarDataBean;
import com.zcc.contactapp.contactmodel.viewmodels.DetailDataBean;

import java.util.List;

public class ContactInfoActivity extends AppCompatActivity implements IContactContractView {

    private RecyclerView mAvatarRecyclerView;
    private AvatarLinearAdapter mAvatarAdapter;
    private RecyclerView mDetailRecyclerView;
    private DetailLinearAdapter mDetailAdapter;
    private PagerSnapHelper pagerSnapHelper;
    private LinearSnapHelper linearSnapHelper;
    private ContactPresenter mContactPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initData();
    }

    private void initData() {
        mContactPresenter = new ContactPresenter(this);
        mContactPresenter.loadContactDatas();
    }


    private void initWidget() {
        mAvatarRecyclerView = findViewById(R.id.rv_avatars);
        LinearLayoutManager avatarLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mAvatarAdapter = new AvatarLinearAdapter();
        mAvatarRecyclerView.setLayoutManager(avatarLayoutManager);
        mAvatarRecyclerView.setAdapter(mAvatarAdapter);
        mDetailRecyclerView = findViewById(R.id.rv_desc);
        LinearLayoutManager detailLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mDetailAdapter = new DetailLinearAdapter();
        mDetailRecyclerView.setLayoutManager(detailLayoutManager);
        mDetailRecyclerView.setAdapter(mDetailAdapter);

        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(mDetailRecyclerView);
        PagerSnapHelper linearSnapHelper1 = new PagerSnapHelper();
        linearSnapHelper1.attachToRecyclerView(mAvatarRecyclerView);
    }

    @Override
    public void updateContactDetail(List<DetailDataBean> detailDataBeans) {
        mDetailAdapter.setDataList(detailDataBeans);
    }

    @Override
    public void updateContactAvatar(List<AvatarDataBean> avatarDataBeans) {
        mAvatarAdapter.setDataList(avatarDataBeans);
    }
}
